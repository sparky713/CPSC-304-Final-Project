package model;

public class Player {
    private String userName;
    private String email;
    private String password;
    private String displayName;

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

    // setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String email) {
        this.password = password;
    }

    public void setDisplayName(String email) {
        this.displayName = displayName;
    }

    public void consumes(Food food, int amount) {
    }
}
