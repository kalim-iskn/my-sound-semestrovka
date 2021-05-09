package ru.kpfu.itis.iskander.mysound.exceptions;

public class ListenerAlreadyExistException extends RuntimeException {

    public ListenerAlreadyExistException() {
        super("This listener already exist in track");
    }

}
