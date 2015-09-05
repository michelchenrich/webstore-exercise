package main.persistence.mongo.converters;

import main.domain.Text;
import main.persistence.mongo.Converter;

public class TextConverter implements Converter<Text, String> {
    public String to(Text entity) {
        return entity.toString();
    }

    public Text fromPersisted(String document) {
        return new Text(document);
    }
}
