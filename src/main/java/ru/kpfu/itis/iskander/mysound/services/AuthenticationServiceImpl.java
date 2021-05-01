package ru.kpfu.itis.iskander.mysound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.iskander.mysound.services.interfaces.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    @Qualifier("customUserDetailsManager")
    private UserDetailsManager userDetailsManager;

    @Override
    public void authenticate(String username) {
        UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
        Authentication auth = new RememberMeAuthenticationToken(
                username,
                userDetails,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
