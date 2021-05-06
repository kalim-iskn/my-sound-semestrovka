package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.exceptions.ConnectionToUrlException;
import ru.kpfu.itis.iskander.mysound.exceptions.EmailAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.AuthenticationService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.VkOauthService;

@Controller
@RequestMapping("/oauth/vk")
public class VKOauthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    @Qualifier("vkOauth")
    private VkOauthService vkOauthService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProjectProperties projectProperties;

    @GetMapping
    public String getResponse(
            @RequestParam(value = "code", required = false) String code,
            RedirectAttributes redirectAttributes
    ) {
        if (code == null)
            return "redirect:/";

        String errorCode;

        try {
            User user = vkOauthService.authorize(code);
            authenticationService.authenticate(user.getUsername());
            return "redirect:/my_profile";
        } catch (ConnectionToUrlException | UndefinedServerProblemException connectionToUrlException) {
            errorCode = "errors.oauth.undefined";
        } catch (EmailAlreadyExistException e) {
            errorCode = "errors.oauth.email";
        }

        redirectAttributes.addFlashAttribute(
                "oauth_status",
                messageSource.getMessage(errorCode, null, projectProperties.getLocale())
        );

        return "redirect:/signup";
    }

}
