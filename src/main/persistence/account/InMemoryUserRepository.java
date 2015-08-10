package main.persistence.account;

import main.domain.account.Email;
import main.domain.account.User;
import main.persistence.EntityNotFoundException;
import main.persistence.InMemoryRepository;

public class InMemoryUserRepository extends InMemoryRepository<User> implements UserRepository {
    public boolean hasWithEmail(Email email) {
        return findByEmail(email) != null;
    }

    public User getByEmail(Email email) {
        User match = findByEmail(email);
        if (match == null)
            throw new EntityNotFoundException();
        return match.copy();
    }

    private User findByEmail(Email email) {
        for (User user : getEntities())
            if (user.getEmail().equals(email))
                return user;
        return null;
    }
}
