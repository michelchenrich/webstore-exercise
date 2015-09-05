package main.persistence.mongo.converters;

import main.domain.account.Password;
import main.persistence.mongo.Converter;

public class PasswordConverter implements Converter<Password, String> {
    public String to(Password entity) {
        return entity.toString();
    }

    public Password fromPersisted(String document) {
        return new Password(document);
    }
}
