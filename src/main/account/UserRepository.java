package main.account;

public interface UserRepository {
    boolean hasWithEmail(String email);
    User getByEmail(String email);
    void save(User user);
    User getById(String id);
    boolean hasWithId(String id);
}
