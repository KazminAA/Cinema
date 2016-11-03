package models;

/**
 * Created by Alexandr on 21.09.2016.
 */
public class UserRole extends Entity {
    String name;

    public UserRole(String name) {
        this.name = name;
    }

    public UserRole() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "name='" + name + '\'' +
                '}';
    }
}
