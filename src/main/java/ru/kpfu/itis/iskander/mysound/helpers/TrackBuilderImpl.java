package ru.kpfu.itis.iskander.mysound.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.TrackBuilder;
import ru.kpfu.itis.iskander.mysound.models.Track;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackBuilderImpl implements TrackBuilder {

    @Autowired
    private ProjectProperties projectProperties;

    public Track build(Track track) {
        String mediaDir = projectProperties.getMediaDirectory() + "/";
        if (track != null) {
            track.setAudioUrl(mediaDir + projectProperties.getAudiosDirectory() + "/" + track.getAudio());
            track.setPosterUrl(mediaDir + projectProperties.getPostersDirectory() + "/" + track.getPoster());
        }
        return track;
    }

    public List<Track> build(List<Track> tracks) {
        return tracks.stream()
                .map(this::build)
                .collect(Collectors.toList());
    }

}
