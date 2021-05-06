package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.dto.PostForm;
import ru.kpfu.itis.iskander.mysound.services.interfaces.PostsService;

@Controller
public class PostController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private PostsService postsService;

    @PostMapping("/post/add")
    public String add(
            @RequestParam("text") String text,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (text.trim().length() == 0) {
            redirectAttributes.addFlashAttribute(
                    "text_invalid",
                    messageSource.getMessage("validation.post.text", null, projectProperties.getLocale())
            );
        } else {
            PostForm postForm = new PostForm();
            postForm.setText(text);
            postForm.setUsername(userDetails.getUsername());
            postsService.add(postForm);
            redirectAttributes.addFlashAttribute("post_status", true);
        }
        return "redirect:/artist/" + userDetails.getUsername();
    }

}
