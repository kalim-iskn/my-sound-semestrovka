package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
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
    }

    @GetMapping("/tracks")
    public String allTracks(@RequestParam(required = false, value = "q") String query, ModelMap map) {
        map.addAttribute(
                "tracks",
                query == null ? trackService.getAll() : trackService.getAllWithQuery(query)
        );
        return "tracks/tracks";
    }

}
