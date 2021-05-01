package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.dto.AddTrackForm;
import ru.kpfu.itis.iskander.mysound.dto.EditTrackForm;
import ru.kpfu.itis.iskander.mysound.dto.TrackForm;
import ru.kpfu.itis.iskander.mysound.exceptions.AudioInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.PosterInvalidException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFound;
import ru.kpfu.itis.iskander.mysound.exceptions.UndefinedServerProblemException;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.RedirectHelper;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class ManageTracksController {

    @Autowired
    private RedirectHelper redirectHelper;

    @Autowired
    private TrackService trackService;

    @GetMapping("/my_tracks")
    public String list(@AuthenticationPrincipal UserDetails userDetails, ModelMap map) {
        List<Track> tracks = trackService.getList(userDetails.getUsername());
        map.addAttribute("tracks", tracks);
        return "tracks/my_tracks";
    }

    @GetMapping("/new_track")
    public String add(ModelMap map) {
        return getForm(map, new TrackForm(), true, null);
    }

    @RequestMapping(value = "/edit-track/{trackId}", method = RequestMethod.GET)
    public String edit(ModelMap map, @PathVariable Long trackId, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Track track = trackService.getMyTrack(trackId, userDetails.getUsername());
            TrackForm trackForm = TrackForm.builder()
                    .name(track.getName())
                    .description(track.getDescription())
                    .build();
            return getForm(map, trackForm, false, trackId);
        } catch (TrackNotFound trackNotFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Track not found"
            );
        }
    }

    @RequestMapping(value = "/edit-track/{trackId}", method = RequestMethod.POST)
    public String update(
            @Validated(EditTrackForm.class) TrackForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long trackId
    ) {
        if (!trackService.isUserAuthor(trackId, userDetails.getUsername()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author of this track");

        return save(false, userDetails, redirectAttributes, form, result, trackId);
    }

    @PostMapping("/new_track")
    public String create(
            @Validated(AddTrackForm.class) TrackForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return save(true, userDetails, redirectAttributes, form, result, null);
    }

    @RequestMapping(value = "/delete-track/{trackId}", method = RequestMethod.GET)
    public String delete(
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long trackId
    ) {
        redirectAttributes.addFlashAttribute("isDeleted", trackService.delete(trackId, userDetails.getUsername()));
        return "redirect:/my_tracks";
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

    private String save(
            boolean isAdding,
            UserDetails userDetails,
            RedirectAttributes redirectAttributes,
            TrackForm form,
            BindingResult result,
            Long trackId
    ) {
        String url = isAdding ? "new_track" : "edit-track/" + trackId;

        redirectHelper.init("form", url);

        if (!result.hasErrors()) {
            try {
                if (isAdding)
                    trackService.create(form, userDetails.getUsername());
                else
                    trackService.update(form, userDetails.getUsername(), trackId);
                redirectAttributes.addFlashAttribute("status", true);
                return "redirect:/" + url;
            } catch (UndefinedServerProblemException e) {
                redirectAttributes.addFlashAttribute("status", false);
                return "redirect:/" + url;
            } catch (AudioInvalidException e) {
                result.rejectValue("audio", "errors.audio.invalid");
            } catch (PosterInvalidException e) {
                result.rejectValue("poster", "errors.poster.invalid");
            }
        }

        return redirectHelper.redirectBackWithErrors(redirectAttributes, form, result);
    }

}
