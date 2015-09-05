package main.persistence.mongo.converters;

import main.domain.account.User;

import java.util.Map;

public class UserConverter extends EntityConverter<User> {
    private EmailConverter emailConverter = new EmailConverter();
    private PasswordConverter passwordConverter = new PasswordConverter();

    protected void defineGetters(Map<String, Getter<User>> getters) {
        getters.put("email", (u) -> emailConverter.to(u.getEmail()));
        getters.put("password", (u) -> passwordConverter.to(u.getPassword()));
    }

    protected void defineSetters(Map<String, Setter<User>> setters) {
        setters.put("_id", (u, v) -> u.setId(v.toString()));
        setters.put("email", (u, v) -> u.setEmail(emailConverter.from(v)));
        setters.put("password", (u, v) -> u.setPassword(passwordConverter.from(v)));
    }

    protected User makeNew() {
        return new User();
    }
}
