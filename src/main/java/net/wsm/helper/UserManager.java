package net.wsm.helper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.google.common.hash.Hashing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.bcrypt.BCrypt;

import net.wsm.model.*;
import net.wsm.repository.UserRepository;

public class UserManager implements InitializingBean{
    private User user = null;
    private boolean isSignedIn = false;
    private UserRepository repository = new UserRepository();

    public boolean SignIn(loginModel login) {
        if (login != null && login.getPassword() != null && login.getEmail() != null) {
            User user = repository.getByEmail(login.getEmail()); // check if password matches
            if (user != null && BCrypt.checkpw(login.getPassword(), user.getPasswordHash())) {
                String AuthToken = Hashing.sha256().hashString(LocalDateTime.now().toString() + user.getEmail(), StandardCharsets.UTF_8).toString();
                user.setAuthToken(AuthToken);
                user.setTokenExp(LocalDateTime.now().plusDays(1));
                repository.updateUser(user);
                this.user = user;
                isSignedIn = true;
                System.out.println("Logged in, welcome " + login.getEmail());
                return true;
                
            }
        }
        return false;
    }
    public void SignOut() {
        user = null;
        isSignedIn = false;
    }
    public User getUser() {
        return user;
    }
    public boolean isSignedIn() {
        return isSignedIn;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }
}
