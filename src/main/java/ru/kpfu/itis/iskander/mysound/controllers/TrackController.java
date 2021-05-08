package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

@Controller
public class TrackController {

    @Autowired
    private TrackService trackService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectProperties projectProperties;

    @RequestMapping(value = "/track/{trackId}", method = RequestMethod.GET)
    public String show(@AuthenticationPrincipal UserDetails userDetails, ModelMap map, @PathVariable Long trackId) {
        try {
            boolean isAuthenticated = userDetails != null;
            boolean isLiked = false;

            Track track = trackService.getTrack(trackId);

            if (isAuthenticated) {
                User user = usersService.getUser(userDetails.getUsername());
                isLiked = track.getLikes().contains(user);
            }

            map.addAttribute("track", track);
            map.addAttribute("isAuthenticated", isAuthenticated);
            map.addAttribute("isLiked", isLiked);
            map.addAttribute("comments", track.getComments());
            map.addAttribute(
                    "avatarsDirectoryUrl",
                    projectProperties.getMediaDirectory() + "/" + projectProperties.getAvatarsDirectory()
            );
            map.addAttribute("noAvatarUrl", projectProperties.getNoAvatarUrl());
            return "tracks/track_page";
        } catch (TrackNotFoundException trackNotFoundException) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Track not found"
            );
        }
    }

    @GetMapping("/tracks")
    public String allTracks(ModelMap map) {
        map.addAttribute("tracks", trackService.getAll());
        return "tracks/tracks";
    }

}
