package model;

public class Player {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String displayName;

    public Player(String firstName, String lastName, String email, String password, String displayName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

//    // getters
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getDisplayName() {
//        return displayName;
//    }
}
