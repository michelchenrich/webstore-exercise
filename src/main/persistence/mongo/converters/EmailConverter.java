package main.persistence.mongo.converters;

import main.domain.account.Email;
import main.persistence.mongo.Converter;

public class EmailConverter implements Converter<Email, String> {
    public String to(Email entity) {
        return entity.toString();
    }

    public Email fromPersisted(String document) {
        return new Email(document);
    }
}
