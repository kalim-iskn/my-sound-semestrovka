package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.dto.EditProfileForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AvatarInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.CoverInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.exceptions.UsernameNotUniqueException;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.RedirectHelper;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.AuthenticationService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.EditProfileService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/edit-profile")
@PreAuthorize("isAuthenticated()")
public class EditProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RedirectHelper redirectHelper;

    @Autowired
    private EditProfileService editProfileService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public String showForm(@AuthenticationPrincipal UserDetails userDetails, ModelMap map) {
        if (!map.containsAttribute("editProfileForm")) {
            User user = usersService.getUser(userDetails.getUsername());
            EditProfileForm form = EditProfileForm.builder()
                    .username(user.getUsername())
                    .bio(user.getBio())
                    .pseudonym(user.getPseudonym())
                    .build();
            map.addAttribute("editProfileForm", form);
        }
        return "edit_profile";
    }

    @PostMapping
    public String edit(
            @Valid EditProfileForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        redirectHelper.init("editProfileForm", "edit-profile");
        if (!result.hasErrors()) {
            try {
                editProfileService.editProfile(form, userDetails.getUsername());
                redirectAttributes.addFlashAttribute("status", true);
                if (!userDetails.getUsername().equals(form.getUsername())) {
                    authenticationService.authenticate(form.getUsername());
                }
                return "redirect:/edit-profile";
            } catch (UsernameNotUniqueException e) {
                result.rejectValue("username", "validation.username.not_unique");
            } catch (UndefinedServerProblemException e) {
                redirectAttributes.addFlashAttribute("status", false);
                return "redirect:/edit-profile";
            } catch (AvatarInvalidException e) {
                result.rejectValue("avatar", "errors.avatar.invalid");
            } catch (CoverInvalidException e) {
                result.rejectValue("cover", "errors.cover.invalid");
            }
        }
        return redirectHelper.redirectBackWithErrors(redirectAttributes, form, result);
    }

}
