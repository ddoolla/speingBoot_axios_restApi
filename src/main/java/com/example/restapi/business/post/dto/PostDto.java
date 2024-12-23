package com.example.restapi.business.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDto {

    private final Long id;
    private final String title;
    private final String content;
    private final UserInPostDto user;

    public PostDto(Long id,
                   String title,
                   String content,
                   String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = new UserInPostDto(userName);
    }

}
