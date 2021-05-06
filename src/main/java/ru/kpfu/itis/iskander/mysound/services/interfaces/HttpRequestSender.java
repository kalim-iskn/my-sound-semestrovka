package ru.kpfu.itis.iskander.mysound.services.interfaces;

import ru.kpfu.itis.iskander.mysound.exceptions.ConnectionToUrlException;

import java.util.Map;

public interface HttpRequestSender {

    String getContent(String url, Map<String, String> params) throws ConnectionToUrlException;

}
