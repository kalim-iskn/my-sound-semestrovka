package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.exceptions.EmailAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.ProblemWithConnectionToUrl;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.VkOauthService;

@Controller
@RequestMapping("/oauth/vk")
public class VKOauthController {

    @Autowired
    @Qualifier("customUserDetailsManager")
    private UserDetailsManager userDetailsManager;

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
            UserDetails userDetails = userDetailsManager.loadUserByUsername(user.getUsername());
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/my_profile";
        } catch (ProblemWithConnectionToUrl | UndefinedServerProblemException problemWithConnectionToUrl) {
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
