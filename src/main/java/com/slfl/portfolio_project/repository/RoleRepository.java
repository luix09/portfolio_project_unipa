package com.slfl.portfolio_project.repository;

import java.util.Optional;

import com.slfl.portfolio_project.model.Role;
import com.slfl.portfolio_project.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByRoleName(RoleType role);
}