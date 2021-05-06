package ru.kpfu.itis.iskander.mysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.iskander.mysound.config.ProjectProperties;
import ru.kpfu.itis.iskander.mysound.dto.CommentForm;
import ru.kpfu.itis.iskander.mysound.exceptions.TrackNotFoundException;
import ru.kpfu.itis.iskander.mysound.services.interfaces.CommentsService;

@Controller
@PreAuthorize("isAuthenticated()")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProjectProperties projectProperties;

    @PostMapping("/comment/add")
    public String add(
            @RequestParam("text") String text,
            @RequestParam("track_id") Long trackId,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (trackId == null)
            return "redirect:/";

        String trackPage = "/track/" + trackId;

        if (text.trim().length() == 0) {
            redirectAttributes.addFlashAttribute(
                    "text_invalid",
                    messageSource.getMessage("validation.comment.text", null, projectProperties.getLocale())
            );
        } else {
            CommentForm commentForm = new CommentForm();
            commentForm.setText(text);
            commentForm.setTrackId(trackId);
            commentForm.setUsername(userDetails.getUsername());
            try {
                commentsService.add(commentForm);
                redirectAttributes.addFlashAttribute("comment_status", true);
            } catch (TrackNotFoundException e) {
                redirectAttributes.addFlashAttribute("comment_status", false);
            }
        }
        return "redirect:" + trackPage;
    }

}
