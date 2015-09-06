package main.domain.account;

public class EncryptedPassword {
    public static final EncryptedPassword EMPTY = new EncryptedPassword("", "");
    private String salt;
    private String hash;

    public EncryptedPassword(String salt, String hash) {
        this.salt = salt;
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public boolean equals(Object other) {
        return other instanceof EncryptedPassword && equalsEncryptedPassword((EncryptedPassword) other);
    }

    private boolean equalsEncryptedPassword(EncryptedPassword other) {
        return salt.equals(other.salt) && hash.equals(other.hash);
    }
}
