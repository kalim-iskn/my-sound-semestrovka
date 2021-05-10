package ru.kpfu.itis.iskander.mysound.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.converters.JsonToUserAccessDataDtoConverter;
import ru.kpfu.itis.iskander.mysound.converters.JsonToUserInfoVkDto;
import ru.kpfu.itis.iskander.mysound.dto.SignUpForm;
import ru.kpfu.itis.iskander.mysound.dto.oauth.vk.UserAccessDataDto;
import ru.kpfu.itis.iskander.mysound.dto.oauth.vk.UserInfoVk;
import ru.kpfu.itis.iskander.mysound.exceptions.*;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;
import ru.kpfu.itis.iskander.mysound.services.interfaces.HttpRequestSender;
import ru.kpfu.itis.iskander.mysound.services.interfaces.SignUpService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.VkOauthService;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Service("vkOauth")
public class VKOauthServiceImpl implements VkOauthService {

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private HttpRequestSender httpRequestSender;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JsonToUserAccessDataDtoConverter userAccessDataDtoConverter;

    @Autowired
    private JsonToUserInfoVkDto userInfoVkDtoConverter;

    @Autowired
    private SignUpService signUpService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String responseType = "code";
    private final String accessTokenUrl = "https://oauth.vk.com/access_token";
    private final String getUsersMethodUrl = "https://api.vk.com/method/users.get";

    @Override
    public String getLink() {
        return "https://oauth.vk.com/authorize?client_id=" + projectProperties.getVkApiClientId() + "&display=page" +
                "&redirect_uri=" + projectProperties.getVkRedirectUri() + "&scope=offline,email" +
                "&response_type=" + responseType + "&v=" + projectProperties.getVkApiVersion();
    }

    @Override
    public User authorize(String code)
            throws ConnectionToUrlException, UndefinedServerProblemException, EmailAlreadyExistException {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", String.valueOf(projectProperties.getVkApiClientId()));
        params.put("client_secret", projectProperties.getVkApiClientSecret());
        params.put("code", code);
        params.put("redirect_uri", projectProperties.getVkRedirectUri());
        String responseJson = httpRequestSender.getContent(accessTokenUrl, params);
        UserAccessDataDto userAccessData = userAccessDataDtoConverter.convert(responseJson);

        if (userAccessData == null || userAccessData.isNull()) {
            logger.error(String.valueOf(userAccessData));
            throw new UndefinedServerProblemException();
        }

        User user = usersRepository.findByVkUserIdOrEmail(
                userAccessData.getUserId(), userAccessData.getEmail()
        ).orElse(null);

        if (user == null)
            user = registerNewUser(userAccessData);
        else if (!userAccessData.getUserId().equals(user.getVkUserId()))
            throw new EmailAlreadyExistException();

        return user;
    }

    private User registerNewUser(UserAccessDataDto userAccessData)
            throws ConnectionToUrlException, UndefinedServerProblemException {
        Map<String, String> params = new HashMap<>();
        params.put("uids", String.valueOf(userAccessData.getUserId()));
        params.put("fields", "uid,first_name,last_name,screen_name,sex,bdate,photo_big,about");
        params.put("access_token", userAccessData.getAccessToken());
        params.put("v", projectProperties.getVkApiVersion());
        String responseJson = httpRequestSender.getContent(getUsersMethodUrl, params);
        UserInfoVk userInfoVk = userInfoVkDtoConverter.convert(responseJson);

        if (userInfoVk != null)
            userInfoVk.setEmail(userAccessData.getEmail());

        if (userInfoVk == null || userInfoVk.isNull()) {
            logger.error(String.valueOf(userInfoVk));
            throw new UndefinedServerProblemException();
        }

        String username = userInfoVk.getScreenName();

        if (usersRepository.existsByUsername(username))
            username = ZonedDateTime.now().toInstant().toEpochMilli() + "_" + username;

        SignUpForm signUpForm = SignUpForm.builder()
                .bio(userInfoVk.getAbout())
                .email(userInfoVk.getEmail())
                .pseudonym(userInfoVk.getPseudonym())
                .username(username)
                .build();

        try {
            signUpService.setVkOptions(userInfoVk.getId());
            return signUpService.signUp(signUpForm);
        } catch (AvatarInvalidException | CoverInvalidException | PasswordsNotMatchException e) {
            throw new UndefinedServerProblemException();
        }
    }

}
