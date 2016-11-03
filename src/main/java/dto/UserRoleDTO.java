package dto;


import models.Entity;

/**
 * Created by Alexandr on 21.09.2016.
 */
public class UserRoleDTO extends Entity {
    String name;

    public UserRoleDTO(String name) {
        this.name = name;
    }

    public UserRoleDTO() {
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
