package model;

public class Player {
    private final String userName;
    private final String email;
    private final String password;
    private final String displayName;

    public Player(String userName, String email, String password, String displayName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    // getters
    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void consumes(Food food, int amount) {
    }
}
