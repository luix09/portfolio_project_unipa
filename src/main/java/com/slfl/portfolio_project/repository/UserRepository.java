package com.slfl.portfolio_project.repository;

import java.util.List;
import java.util.Optional;

import com.slfl.portfolio_project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    List<User> findByEmail(String email);

    @Query("SELECT p FROM User p JOIN p.role r WHERE r.roleName = 'PHOTOGRAPHER'")
    List<User> findAllPhotographer();

    @Query("SELECT us from User us JOIN us.albums a WHERE a.albumId = :albumId")
    Optional<User> findByAlbumId(@Param("albumId") Integer albumId);
}