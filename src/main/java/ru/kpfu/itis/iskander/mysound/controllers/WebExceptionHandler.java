package ru.kpfu.itis.iskander.mysound.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.iskander.mysound.exceptions.AccessForbiddenException;
import ru.kpfu.itis.iskander.mysound.exceptions.NotFoundException;

@ControllerAdvice("ru.kpfu.itis.iskander.mysound.controllers")
public class WebExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(NotFoundException ex) {
        logger.warn(ex.getMessage());
        return showErrorPage(404);
    }

    @ExceptionHandler(value = {AccessForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleForbidden(AccessForbiddenException ex) {
        logger.warn(ex.getMessage());
        return showErrorPage(403);
    }

    private ModelAndView showErrorPage(int status) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("status", status);
        mav.addObject("is_pjax", false);
        mav.setViewName("error");
        return mav;
    }

}
