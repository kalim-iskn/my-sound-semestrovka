package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.iskander.mysound.dto.LikeDto;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

@Controller
@PreAuthorize("isAuthenticated()")
public class LikeController {

    @Autowired
    private TrackService trackService;

    @GetMapping("/add-like")
    @ResponseBody
    public String likeTrack(@RequestParam("track_id") Long trackId, @AuthenticationPrincipal UserDetails userDetails) {
        LikeDto likeDto = LikeDto.builder()
                .trackId(trackId)
                .username(userDetails.getUsername())
                .build();
        trackService.likeTrack(likeDto);
        return "success";
    }
}
