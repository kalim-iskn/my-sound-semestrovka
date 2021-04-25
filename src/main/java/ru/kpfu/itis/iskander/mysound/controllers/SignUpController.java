package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.dto.SignUpForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AvatarInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.CoverInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PasswordsNotMatchException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.SignUpService;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String signUpForm(ModelMap map) {
        if (!map.containsAttribute("signUpForm"))
            map.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping
    public String signUp(
            @Valid SignUpForm signUpForm,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @RequestParam("avatar") MultipartFile avatarMultipartFile,
            @RequestParam("cover") MultipartFile coverMultipartFile
    ) {
        if (result.hasErrors()) {
            return redirectBackWithErrors(redirectAttributes, signUpForm, result);
        } else {
            try {
                if (avatarMultipartFile.isEmpty()) {
                    result.rejectValue("avatar", "validation.field.required");
                    return redirectBackWithErrors(redirectAttributes, signUpForm, result);
                }
                signUpForm.setAvatar(avatarMultipartFile);
                signUpForm.setCover(coverMultipartFile);
                signUpService.signUp(signUpForm);
                redirectAttributes.addFlashAttribute("success", true);
                return "redirect:/signup";
            } catch (PasswordsNotMatchException e) {
                result.rejectValue("password", "errors.passwords_mismatch");
                return redirectBackWithErrors(redirectAttributes, signUpForm, result);
            } catch (AvatarInvalidException e) {
                result.rejectValue("avatar", "errors.avatar.invalid");
                return redirectBackWithErrors(redirectAttributes, signUpForm, result);
            } catch (CoverInvalidException e) {
                result.rejectValue("cover", "errors.cover.invalid");
                return redirectBackWithErrors(redirectAttributes, signUpForm, result);
            }
        }
    }

    private String redirectBackWithErrors(
            RedirectAttributes redirectAttributes,
            SignUpForm signUpForm,
            BindingResult result
    ) {
        redirectAttributes.addFlashAttribute(
                "org.springframework.validation.BindingResult.signUpForm",
                result
        );
        redirectAttributes.addFlashAttribute("signUpForm", signUpForm);
        return "redirect:/signup";
    }

}