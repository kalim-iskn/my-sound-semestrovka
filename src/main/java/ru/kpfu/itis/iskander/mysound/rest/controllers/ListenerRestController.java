package ru.kpfu.itis.iskander.mysound.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.iskander.mysound.rest.dto.ApiSuccess;
import ru.kpfu.itis.iskander.mysound.dto.ListenerDto;
import ru.kpfu.itis.iskander.mysound.exceptions.ListenerAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.ListenerService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ListenerRestController {

    @Autowired
    private ListenerService listenerService;

    @PostMapping("/api/listeners")
    public ResponseEntity<?> addListener(@RequestParam("track_id") Long trackId, HttpServletRequest request)
            throws TrackNotFoundException, ListenerAlreadyExistException, IllegalArgumentException {

        ListenerDto listenerDto = ListenerDto.builder()
                .trackId(trackId)
                .ip(request.getRemoteAddr())
                .build();

        listenerService.addToTrack(listenerDto);
        return ResponseEntity.ok(new ApiSuccess());
    }

}
