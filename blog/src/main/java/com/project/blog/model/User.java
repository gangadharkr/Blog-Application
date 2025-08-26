package com.project.blog.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

@Table(name="users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="user_name",nullable=false, length =100)
    private String name;
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy ="user",cascade =CascadeType.ALL)
    private Set<Comment> comments=new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns=@JoinColumn(name="user",referencedColumnName = "id"),
    inverseJoinColumns =@JoinColumn(name = "role",referencedColumnName = "id")
    )
    private Set<Role> roles=new HashSet<>();

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).toList();
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
