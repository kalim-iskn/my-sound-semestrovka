package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/artist/{username}", method = RequestMethod.GET)
    public String showProfile(
            @PathVariable String username,
            ModelMap map,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        try {
            User user = usersService.getUser(username);
            map.addAttribute("info", user);

            boolean isAuthUserProfile = userDetails != null && userDetails.getUsername().equals(username);
            map.addAttribute("is_auth_user_profile", isAuthUserProfile);

            return "profile";
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
    }

}
