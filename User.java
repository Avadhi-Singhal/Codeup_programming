/*
 * The User class defines a blueprint for user objects with common properties (name and email) and an abstract method for performing role-specific actions in the quiz application.
 * Created By : Sachin-Gupta
 * Date of Creation : 08/10/2024
 */

public abstract class User {
    private String name;

    // Constructor to initialize name and email
    public User(String name) {
        this.name = name;
    }

    // Get the name
    public String getName() {
        return name;
    }

    // Set the name
    public void setName(String name) {
        this.name = name;
    }
}
