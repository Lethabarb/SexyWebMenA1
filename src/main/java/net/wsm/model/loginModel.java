package net.wsm.model;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class loginModel {
    private String email;
    private String password;

    public loginModel(String email, String password){
        this.email=email;
        this.password=password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String hash(String pw){
        return BCrypt.hashpw(pw, BCrypt.gensalt());
    }
}
