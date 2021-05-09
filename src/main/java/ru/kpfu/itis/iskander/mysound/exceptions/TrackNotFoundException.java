package ru.kpfu.itis.iskander.mysound.exceptions;


public class TrackNotFoundException extends NotFoundException {

    public TrackNotFoundException(Long id) {
        super("Track with id " + id + " not found");
    }

    public TrackNotFoundException() {
        super("Track not found");
    }

}
