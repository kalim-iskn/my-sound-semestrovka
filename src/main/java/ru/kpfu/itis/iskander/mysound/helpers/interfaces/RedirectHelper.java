package ru.kpfu.itis.iskander.mysound.helpers.interfaces;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface RedirectHelper {

    void init(String formName, String page);

    String redirectBackWithErrors(RedirectAttributes redirectAttributes, Object form, BindingResult result);

}
