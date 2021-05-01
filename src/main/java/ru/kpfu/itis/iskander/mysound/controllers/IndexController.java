package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.iskander.mysound.services.interfaces.TrackService;

@Controller
public class IndexController {

    @Autowired
    private TrackService trackService;

    @GetMapping("/")
    public String showIndexPage(ModelMap map) {
        map.addAttribute("tracks", trackService.getPopular());
        return "index";
    }

}
