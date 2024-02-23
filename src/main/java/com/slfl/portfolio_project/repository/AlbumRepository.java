package com.slfl.portfolio_project.repository;

import com.slfl.portfolio_project.model.Album;
import com.slfl.portfolio_project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<Album> findAlbumByTitle(String title);

    Optional<List<Album>> findAllByOwner(User owner);

    Optional<Album> findByOwnerAndAlbumId(User owner, Integer albumId);

    @Query("SELECT a FROM Album a JOIN a.pictures p WHERE p.pictureId = :pictureId")
    Optional<Album> findByPictureId(Integer pictureId);

    @Query("SELECT a FROM Album a ORDER BY SIZE(a.usersWhoLikes) DESC")
    List<Album> findMostLikedAlbums();
}
