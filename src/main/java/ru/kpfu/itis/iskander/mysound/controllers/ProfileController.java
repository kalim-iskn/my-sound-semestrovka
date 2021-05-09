package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.iskander.mysound.models.User;
import ru.kpfu.itis.iskander.mysound.services.interfaces.PostsService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

import java.util.Map;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private PostsService postsService;

    @RequestMapping(value = "/artist/{username}", method = RequestMethod.GET)
    public String showProfile(
            @PathVariable String username,
            ModelMap map,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = usersService.getUser(username);

        map.addAttribute("info", user);
        map.addAttribute("tracks", trackService.getList(username));

        Map<String, Long> statistic = trackService.getStatistic(user.getId());
        map.addAttribute("statistic", statistic);

        map.addAttribute("posts", postsService.getSorted(user));

        boolean isAuthUserProfile = userDetails != null && userDetails.getUsername().equals(username);
        map.addAttribute("is_auth_user_profile", isAuthUserProfile);

        return "profile";
    }

}
