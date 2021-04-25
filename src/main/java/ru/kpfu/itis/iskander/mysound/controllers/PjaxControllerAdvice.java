package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PjaxControllerAdvice {

    @ModelAttribute("is_pjax")
    public Boolean isPjax(HttpServletRequest request) {
        return request.getHeader("X-Pjax") != null;
    }

}
