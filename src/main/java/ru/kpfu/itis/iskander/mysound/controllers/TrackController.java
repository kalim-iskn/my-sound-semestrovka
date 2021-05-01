package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFound;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

@Controller
public class TrackController {

    @Autowired
    private TrackService trackService;

    @RequestMapping(value = "/track/{trackId}", method = RequestMethod.GET)
    public String show(@AuthenticationPrincipal UserDetails userDetails, ModelMap map, @PathVariable Long trackId) {
        try {
            Track track = trackService.getTrack(trackId);
            map.addAttribute("track", track);
            map.addAttribute("isAuthenticated", userDetails != null);
            //TODO set is liked from database
            map.addAttribute("isLiked", false);
            return "tracks/track_page";
        } catch (TrackNotFound trackNotFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Track not found"
            );
        }
    }

}
