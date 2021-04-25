package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@PreAuthorize("isAuthenticated()")
@Controller
public class MyProfileController {

    @GetMapping("/my_profile")
    public String redirectToMyProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return "redirect:/artist/" + username;
    }

}
