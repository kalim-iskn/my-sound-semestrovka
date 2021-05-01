package ru.kpfu.itis.iskander.mysound.config.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.iskander.mysound.repositories.UsersRepository;

@Component("customUserDetailsManager")
public class UserDetailsManagerImpl implements UserDetailsManager {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void createUser(UserDetails user) {
    }

    @Override
    public void updateUser(UserDetails user) {
    }

    @Override
    public void deleteUser(String username) {
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"))
        );
    }
}
