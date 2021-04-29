package ru.kpfu.itis.iskander.mysound.helpers;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.helpers.interfaces.RedirectHelper;

@Component
public class RedirectHelperImpl implements RedirectHelper {

    private String formName = "";
    private String page = "";

    public void init(String formName, String page) {
        this.formName = formName;
        this.page = page;
    }

    public String redirectBackWithErrors(
            RedirectAttributes redirectAttributes,
            Object form,
            BindingResult result
    ) {
        redirectAttributes.addFlashAttribute(
                "org.springframework.validation.BindingResult." + formName,
                result
        );
        redirectAttributes.addFlashAttribute(formName, form);
        return "redirect:/" + page;
    }

}
