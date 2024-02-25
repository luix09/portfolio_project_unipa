package com.slfl.portfolio_project.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.slfl.portfolio_project.model.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@EnableAutoConfiguration
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String surname;
    @Column
    private Status status;

    @OneToMany(mappedBy = "owner")
    private List<Album> albums;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_albums",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id") }
    )
    private List<Album> likedAlbums;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_pictures",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "picture_id") }
    )
    private List<Picture> likedPictures;


    public User(String username, String email, String password, Role type) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = type;
    }

    public User(Integer userId, String name, String surname, String username, String email, String password, Role type) {
        super();
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = type;
    }

    public User() {
        super();
        role = new Role(RoleType.CUSTOMER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role.getAuthority()));
        return authorities;
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

    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}