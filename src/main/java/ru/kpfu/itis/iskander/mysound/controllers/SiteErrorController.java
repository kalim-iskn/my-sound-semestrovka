package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SiteErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, ModelMap map) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = status != null ? Integer.parseInt(status.toString()) : 500;
        map.addAttribute("status", statusCode);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
