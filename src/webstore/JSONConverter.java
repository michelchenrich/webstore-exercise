package webstore;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JSONConverter implements ResponseTransformer {
    private Gson gson = new Gson();

    public String render(Object model) {
        return gson.toJson(model);
    }

    public <T> T fromJson(String json, Class<T> toClass) {
        return gson.fromJson(json, toClass);
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }
}
