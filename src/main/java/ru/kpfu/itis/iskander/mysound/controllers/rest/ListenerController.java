package ru.kpfu.itis.iskander.mysound.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.iskander.mysound.dto.ListenerDto;
import ru.kpfu.itis.iskander.mysound.exceptions.ListenerAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFound;
import ru.kpfu.itis.iskander.mysound.services.interfaces.ListenerService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ListenerController {

    @Autowired
    private ListenerService listenerService;

    @PostMapping("/api/listeners")
    public ResponseEntity<?> addListener(@RequestParam("track_id") Long trackId, HttpServletRequest request) {
        if (trackId == null)
            return ResponseEntity.badRequest().build();

        ListenerDto listenerDto = ListenerDto.builder()
                .trackId(trackId)
                .ip(request.getRemoteAddr())
                .build();

        try {
            listenerService.addToTrack(listenerDto);
            return ResponseEntity.ok().build();
        } catch (TrackNotFound | ListenerAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
