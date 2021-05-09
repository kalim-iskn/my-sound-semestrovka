package ru.kpfu.itis.iskander.mysound.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;
import ru.kpfu.itis.iskander.mysound.models.Track;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

import java.util.List;

@RestController
public class TrackRestController {

    @Autowired
    private TrackService trackService;

    @RequestMapping(value = "api/track/{id}", method = RequestMethod.GET)
    public ResponseEntity<Track> getTrack(@PathVariable Long id) throws TrackNotFoundException {
        return ResponseEntity.ok(trackService.getTrack(id));
    }

    @RequestMapping(value = "/api/tracks", method = RequestMethod.GET)
    public ResponseEntity<List<Track>> getAll() {
        return ResponseEntity.ok(trackService.getAll());
    }

}
