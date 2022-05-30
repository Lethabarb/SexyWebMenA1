package net.wsm.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name="User")
public class User{
    private int id;
    @Column(name="email")
    private String email;
    @Column(name="passwordHash")
    private String passwordHash;
    @Column(name="role")
    private int role; //1 for staff, 0 for user.
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="contactNumber")
    private String contactNumber;
    @Column(name="authToken")
    private String authToken;
    @Column(name="tokenExp")
    private LocalDateTime tokenExp;

    public User(){

    }

    public User(String email,String passwordHash, int role, String authToken, String firstname, String lastName, String contactNumber,LocalDateTime tokenExpiry, int id){
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.authToken = authToken;
        this.tokenExp = tokenExpiry;
        this.firstName = firstname;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.id = id;
    }

    public User(String email, int role, String firstname){
        this.email = email;
        this.role = role;
        this.firstName = firstname;
        this.lastName = "";
        this.authToken = "";
        this.contactNumber = "";
        this.tokenExp = LocalDateTime.now();
        this.passwordHash = "";
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public void setPasswordHash(String pwHash){
        this.passwordHash = pwHash;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstname(String f){
        firstName = f;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String s){
        lastName = s;
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

    public LocalDateTime getTokenExp(){
        return tokenExp;
    }

    public void setTokenExp(LocalDateTime tE){
        tokenExp = tE;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}