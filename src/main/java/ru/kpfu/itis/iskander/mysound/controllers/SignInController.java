package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/signin")
    public String showForm(@Param("error") String error, ModelMap map) {
        if (error != null && error.equals("true"))
            map.addAttribute("invalid_credentials", true);
        return "signIn";
    }

}
