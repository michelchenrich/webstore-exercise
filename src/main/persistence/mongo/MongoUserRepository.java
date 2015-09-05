package main.persistence.mongo;

import main.domain.account.Email;
import main.domain.account.Password;
import main.domain.account.User;
import main.domain.account.UserRepository;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Map;

public class MongoUserRepository extends MongoRepository<User> implements UserRepository {
    public MongoUserRepository() {
        super("users");
    }

    protected void putSetters(Map<String, main.persistence.mongo.Setter<User>> setters) {
        setters.put("email", (p, v) -> p.setEmail(new Email(v.toString())));
        setters.put("password", (p, v) -> p.setPassword(new Password(v.toString())));
    }

    protected void putGetters(Map<String, Getter<User>> getters) {
        getters.put("email", User::getEmail);
        getters.put("password", User::getPassword);
    }

    protected User makeNew() {
        return new User();
    }

    public boolean hasWithEmail(Email email) {
        return hasWith(makeEmailQuery(email));
    }

    public User getByEmail(Email email) {
        return getBy(makeEmailQuery(email));
    }

    private Bson makeEmailQuery(Email email) {
        Document query = new Document();
        query.put("email", email.toString());
        return query;
    }
}
