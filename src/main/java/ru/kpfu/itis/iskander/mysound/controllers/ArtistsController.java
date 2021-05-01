package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.iskander.mysound.services.interfaces.UsersService;

@Controller
public class ArtistsController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/artists")
    public String getAll(ModelMap map) {
        map.addAttribute("artists", usersService.getAll());
        return "artists";
    }

}
