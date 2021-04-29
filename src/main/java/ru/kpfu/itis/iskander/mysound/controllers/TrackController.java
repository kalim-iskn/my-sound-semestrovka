package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.dto.AddTrackForm;
import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.RedirectHelper;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

@Controller
@PreAuthorize("isAuthenticated()")
public class TrackController {

    @Autowired
    private RedirectHelper redirectHelper;

    @Autowired
    private TrackService trackService;

    @GetMapping("/new_track")
    public String add(ModelMap map) {
        return getForm(map, new TrackForm(), true, null);
    }

    @PostMapping("/new_track")
    public String create(
            @Validated(AddTrackForm.class) TrackForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        redirectHelper.init("form", "new_track");
        if (!result.hasErrors()) {
            try {
                trackService.create(form, userDetails.getUsername());
                redirectAttributes.addFlashAttribute("status", true);
                return "redirect:/new_track";
            } catch (UndefinedServerProblemException e) {
                redirectAttributes.addFlashAttribute("status", false);
                return "redirect:/new_track";
            } catch (AudioInvalidException e) {
                result.rejectValue("audio", "errors.audio.invalid");
            } catch (PosterInvalidException e) {
                result.rejectValue("poster", "errors.poster.invalid");
            }
        }
        return redirectHelper.redirectBackWithErrors(redirectAttributes, form, result);
    }

    private String getForm(ModelMap map, TrackForm form, boolean isAdding, Long trackId) {
        String actionUrl = isAdding ? "/new_track" : "/edit-track/" + trackId;
        String title = isAdding ? "Add new track" : "Edit track";

        map.addAttribute("title", title);
        map.addAttribute("action_url", actionUrl);
        if (!map.containsAttribute("form"))
            map.addAttribute("form", form);
        map.addAttribute("isAdding", isAdding);

        return "tracks/track_form";
    }

}
