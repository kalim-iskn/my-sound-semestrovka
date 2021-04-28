package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.dto.SignUpForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AvatarInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.CoverInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PasswordsNotMatchException;
import ru.kpfu.itis.iskander.mysound.helpers.RedirectHelper;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.AuthenticationService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.SignUpService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.VkOauthService;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    @Qualifier("vkOauth")
    private VkOauthService vkOauthService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RedirectHelper redirectHelper;

    @GetMapping
    public String signUpForm(ModelMap map) {
        if (!map.containsAttribute("signUpForm"))
            map.addAttribute("signUpForm", new SignUpForm());
        map.addAttribute("vkOauthLink", vkOauthService.getLink());
        return "signUp";
    }

    @PostMapping
    public String signUp(@Valid SignUpForm signUpForm, BindingResult result, RedirectAttributes redirectAttributes) {
        redirectHelper.init("signUpForm", "signup");
        if (result.hasErrors()) {
            return redirectHelper.redirectBackWithErrors(redirectAttributes, signUpForm, result);
        } else {
            try {
                User user = signUpService.signUp(signUpForm);
                authenticationService.authenticate(user.getUsername());
                return "redirect:/my_profile";
            } catch (PasswordsNotMatchException e) {
                result.rejectValue("password", "errors.passwords_mismatch");
                return redirectHelper.redirectBackWithErrors(redirectAttributes, signUpForm, result);
            } catch (AvatarInvalidException e) {
                result.rejectValue("avatar", "errors.avatar.invalid");
                return redirectHelper.redirectBackWithErrors(redirectAttributes, signUpForm, result);
            } catch (CoverInvalidException e) {
                result.rejectValue("cover", "errors.cover.invalid");
                return redirectHelper.redirectBackWithErrors(redirectAttributes, signUpForm, result);
            }
        }
    }

}
