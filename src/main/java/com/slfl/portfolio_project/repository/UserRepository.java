package com.slfl.portfolio_project.repository;

import java.util.Optional;

import com.slfl.portfolio_project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByAuthority(String authority);
}