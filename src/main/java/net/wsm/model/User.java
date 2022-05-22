package net.wsm.model;

import java.time.LocalDateTime;

public class User{
    private String email;
    private int role; //1 for staff, 0 for user.
    private String firstname;
    private String surname;
    private String contactNumber;
    private String authToken;
    private LocalDateTime tokenExpiry;

    public User(){

    }

    public User(String email, int role, String authToken, LocalDateTime tokenExpiry, String firstname, String surname, String contactNumber){
        this.email = email;
        this.role = role;
        this.authToken = authToken;
        this.tokenExpiry = tokenExpiry;
        this.firstname = firstname;
        this.surname = surname;
        this.contactNumber = contactNumber;
    }

    public User(String email, int role, String firstname){
        this.email = email;
        this.role = role;
        this.firstname = firstname;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String f){
        firstname = f;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String s){
        surname = s;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public void setContactNumber(String c){
        contactNumber = c;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String e){
        email = e;
    }

    public int getRole(){
        return role;
    }

    public void setRole(int i){
        if(i == 1 || i == 0){
            role = i;
        }
        else{
            System.out.println("Role does not match");
        }
    }

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String aT){
        authToken = aT;
    }

    public LocalDateTime getTokenExpiry(){
        return tokenExpiry;
    }

    public void setTokenExpiry(LocalDateTime tE){
        tokenExpiry = tE;
    }
}