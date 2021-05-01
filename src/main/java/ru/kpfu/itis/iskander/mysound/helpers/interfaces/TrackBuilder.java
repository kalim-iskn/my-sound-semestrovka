package ru.kpfu.itis.iskander.mysound.helpers.interfaces;

import ru.kpfu.itis.iskander.mysound.models.Track;

import java.util.List;

public interface TrackBuilder {

    Track build(Track track);

    List<Track> build(List<Track> tracks);

}
