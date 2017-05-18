package project.karhabty.entities;

import java.util.Date;

/**
 * Created by iheb on 05/02/2017.
 */
public class Users {
    private int user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private int mobile_number;
    private int tel_number;
    private String address;
    private Date last_login;
    private Date creation_date;
    private Date password_requested_at;
    private Boolean enabled;

    public Users() {
    }

    public Users(String first_name, String last_name, String email, String password, int mobile_number, int tel_number, String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.tel_number = tel_number;
        this.address = address;
    }

    public Users(int user_id, String first_name, String last_name, String email, int mobile_number, int tel_number, String address, Date last_login, Date creation_date, Boolean enabled) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.tel_number = tel_number;
        this.address = address;
        this.last_login = last_login;
        this.creation_date = creation_date;
        this.enabled = enabled;
    }

    public  int getUser_id() {
        return user_id;
    }

    public  void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getTel_number() {
        return tel_number;
    }

    public void setTel_number(int tel_number) {
        this.tel_number = tel_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
