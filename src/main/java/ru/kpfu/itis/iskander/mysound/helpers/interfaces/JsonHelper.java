package ru.kpfu.itis.iskander.mysound.helpers.interfaces;

public interface JsonHelper {

    <T> T convertFromJson(String json, Class<T> valueType, String rootKey);

}
