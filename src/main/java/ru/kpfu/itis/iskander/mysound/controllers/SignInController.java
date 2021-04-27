package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.iskander.mysound.services.interfaces.VkOauthService;

@Controller
public class SignInController {

    @Autowired
    private VkOauthService vkOauthService;

    @GetMapping("/signin")
    public String showForm(@Param("error") String error, ModelMap map) {
        if (error != null && error.equals("true"))
            map.addAttribute("invalid_credentials", true);
        map.addAttribute("vkOauthLink", vkOauthService.getLink());
        return "signIn";
    }

}
