package ru.kpfu.itis.iskander.mysound.services;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.exceptions.ConnectionToUrlException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.HttpRequestSender;

import java.io.IOException;
import java.util.Map;

@Service
public class HttpRequestSenderImpl implements HttpRequestSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getContent(String url, Map<String, String> params) throws ConnectionToUrlException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            logger.error("Error connection to url", e);
            throw new ConnectionToUrlException();
        }
    }

}
