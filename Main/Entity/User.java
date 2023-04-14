package Main.Entity;

import Database.DB;

import java.util.*;

public class User {
    private static DB db = new DB();
    private String fname;
    private String lname;
    private String email;
    private String dob;
    private String password;
    private String role;

    public User() {
        this.fname = "";
        this.lname = "";
        this.email = "";
        this.dob = "";
        this.password = "";
        this.role = "";
    }

    public User(String fname, String lname, String email, String dob, String password, String role) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.role = role;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
   
    public boolean createUser(ArrayList<String> information, String role) {
        return db.insertUser(information, role);
    }

    public boolean updateUser(ArrayList<String> information, String role) {
        return db.updateUser(information, role);
    }

    public boolean deleteUser(ArrayList<String> information, String role) {
        return db.deleteUser(information, role);
    }
}
