package main.account;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private int incrementalId;
    private Map<String, User> users = new HashMap<>();

    public boolean hasWithEmail(String email) {
        return findByEmail(email) != null;
    }

    public User getByEmail(String email) {
        User match = findByEmail(email);
        if (match == null)
            throw new EntityNotFoundException();
        return match.copy();
    }

    public void save(User user) {
        ensureId(user);
        users.put(user.getId(), user.copy());
    }

    public User getById(String id) {
        if (hasWithId(id))
            return users.get(id);
        else
            throw new EntityNotFoundException();
    }

    public boolean hasWithId(String id) {
        return users.containsKey(id);
    }

    private User findByEmail(String email) {
        for (User user : users.values())
            if (user.getEmail().equals(email))
                return user;
        return null;
    }

    private void ensureId(User user) {
        if (!user.hasId())
            user.setId(makeNextId());
    }

    private String makeNextId() {
        return String.valueOf(++incrementalId);
    }
}
