package net.wsm.model;


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

    // public String hash(String pw){
    //     int strength = 10;
    //     BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
    //     String encodedPassword = BCryptPasswordEncoder.encode(pw);
    //     return encodedPassword;
        
    // }
}
