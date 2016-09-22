package dto;


import models.Entity;

import java.time.LocalDate;

/**
 * Created by Alex on 16.08.2016.
 */
public class UserDTO extends Entity {
    private String login;
    private String pwd;
    private String email;
    private String userName;
    private String userSurname;
    private LocalDate Birthday;
    private UserRoleDTO role;
    private Boolean sex;

    public UserDTO(String login, String pwd, String email, String userName, String userSurname, UserRoleDTO role, Boolean sex) {
        setLogin(login);
        setEmail(email);
        setUserName(userName);
        setUserSurname(userSurname);
        setRole(role);
        setPwd(pwd);
        setSex(sex);
    }

    public UserDTO() {
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.matches(".+@.+\\..+")) throw new IllegalArgumentException("Email adress not valid.");
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public UserRoleDTO getRole() {
        return this.role;
    }

    public void setRole(UserRoleDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", '" + userName + " " + userSurname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        if (!super.equals(o)) return false;

        UserDTO user = (UserDTO) o;

        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        return getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
