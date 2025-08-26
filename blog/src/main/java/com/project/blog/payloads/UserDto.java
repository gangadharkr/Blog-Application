package com.project.blog.payloads;

//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;


import com.project.blog.model.Comment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 characters")
    private String name;
    @Email(message ="Email address is not valid! ")
    private String email;
    @NotEmpty
    @Size(min =8, max=16,message = "Password must be min of 8 chars and max of 16 chars!")
    private String password;
    @NotEmpty
    private String about;

    private Set<CommentDto> comments=new HashSet<>();

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
