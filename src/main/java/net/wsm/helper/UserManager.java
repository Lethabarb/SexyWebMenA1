package net.wsm.helper;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.google.common.hash.Hashing;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import net.wsm.model.*;
import net.wsm.repository.UserRepository;

@Component
public class UserManager implements Serializable{
    private User user = null;
    private boolean isSignedIn = false;
    private UserRepository repository = new UserRepository();

    public boolean SignIn(loginModel login) {
        if (login != null && login.getPassword() != null && login.getEmail() != null) {
            User user = repository.getByEmail(login.getEmail()); // check if password matches
            if (user != null && BCrypt.checkpw(login.getPassword(), user.getPassword())) {
                String AuthToken = Hashing.sha256().hashString(LocalDateTime.now().toString() + user.getEmail(), StandardCharsets.UTF_8).toString();
                user.setAuthToken(AuthToken);
                user.setTokenExp(LocalDateTime.now().plusDays(1));
                repository.updateUser(user);
                this.user = user;
                isSignedIn = true;
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
    public boolean getIsSignedIn() {
        return isSignedIn;
    }
}
