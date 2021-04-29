package ru.kpfu.itis.iskander.mysound.helpers;

import com.google.gson.*;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.JsonHelper;

@Component
public class GsonJsonHelper implements JsonHelper {

    public <T> T convertFromJson(String json, Class<T> valueType, String rootKey) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        JsonParser parser = new JsonParser();
        if (rootKey != null) {
            JsonElement jsonElement = new JsonParser().parse(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            json = jsonObject.get(rootKey).toString();
            json = json.substring(1, json.length() - 1);
        }
        return gson.fromJson(parser.parse(json), valueType);
    }

}
