package ru.kpfu.itis.iskander.mysound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentForm {

    private Long trackId;
    private String username;
    private String text;

}
