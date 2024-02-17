package com.slfl.portfolio_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "albums")
@EnableAutoConfiguration
@JsonIgnoreProperties("owner")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_id")
    private Integer albumId;

    @Column(unique = true)
    private String title;

    @Column
    private String description;

    @Column
    private Date creationDate;

    @OneToMany(mappedBy = "album")
    @JsonIgnoreProperties
    private List<Picture> pictures;

    @ManyToMany(mappedBy = "likedAlbums")
    private List<User> usersWhoLikes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User owner;

    public Album(String title, String description, Date creationDate, List<Picture> pictures, List<User> usersWhoLikes, User user) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.pictures = pictures;
        this.usersWhoLikes = usersWhoLikes;
        this.owner = user;
    }
}
