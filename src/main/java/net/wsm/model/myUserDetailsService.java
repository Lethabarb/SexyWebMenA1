package net.wsm.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.wsm.repository.UserRepository;

@Service
public class myUserDetailsService implements UserDetailsService {

    private UserRepository users = new UserRepository();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.getByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User 404");
        }
        return new userPrincipal(user);
    }
    
}
