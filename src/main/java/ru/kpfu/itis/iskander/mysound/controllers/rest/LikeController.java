package ru.kpfu.itis.iskander.mysound.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.iskander.mysound.dto.LikeDto;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;
import ru.kpfu.itis.iskander.mysound.exceptions.UserNotFoundException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

@RestController
public class LikeController {

    @Autowired
    private TrackService trackService;

    @PostMapping("/api/likes")
    public ResponseEntity<?> likeTrack(@RequestParam("track_id") Long trackId, @AuthenticationPrincipal UserDetails userDetails) {
        if (trackId == null || userDetails == null)
            return ResponseEntity.badRequest().build();

        LikeDto likeDto = LikeDto.builder()
                .trackId(trackId)
                .username(userDetails.getUsername())
                .build();

        try {
            trackService.likeTrack(likeDto);
            return ResponseEntity.ok().build();
        } catch (TrackNotFoundException | UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
