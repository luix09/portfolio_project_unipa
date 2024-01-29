package com.slfl.portfolio_project.repository;

import com.slfl.portfolio_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {

    // custom query to search to blog post by title or content
    List<User> findByUsername(String username);
}