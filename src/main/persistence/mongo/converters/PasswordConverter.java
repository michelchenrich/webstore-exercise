package main.persistence.mongo.converters;

import main.domain.account.EncryptedPassword;
import main.persistence.mongo.Converter;
import org.bson.Document;

public class PasswordConverter implements Converter<EncryptedPassword, Document> {
    public Document to(EncryptedPassword entity) {
        Document document = new Document();
        document.put("salt", entity.getSalt());
        document.put("hash", entity.getHash());
        return document;
    }

    public EncryptedPassword fromPersisted(Document document) {
        return new EncryptedPassword(document.getString("salt"), document.getString("hash"));
    }
}
