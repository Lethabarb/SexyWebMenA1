package net.wsm.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name="User")
public class User{
    @Column(name="email")
    private String email;
    @Column(name="role")
    private int role; //1 for staff, 0 for user.
    @Column(name="firstName")
    private String firstname;
    @Column(name="lastName")
    private String surname;
    @Column(name="contactNumber")
    private String contactNumber;
    @Column(name="authToken")
    private String authToken;
    @Column(name="tokenExp")
    public LocalDateTime tokenExp;

    public User(){

    }

    public User(String email, int role, String authToken, String firstname, String surname, String contactNumber,LocalDateTime tokenExpiry){
        this.email = email;
        this.role = role;
        this.authToken = authToken;
        this.tokenExp = tokenExpiry;
        this.firstname = firstname;
        this.surname = surname;
        this.contactNumber = contactNumber;
    }

    public User(String email, int role, String firstname){
        this.email = email;
        this.role = role;
        this.firstname = firstname;
        this.surname = "";
        this.authToken = "";
        this.contactNumber = "";
        this.tokenExp = LocalDateTime.now();
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
        return tokenExp;
    }

    public void setTokenExpiry(LocalDateTime tE){
        tokenExp = tE;
    }
}