package com.slfl.portfolio_project.repository;
import com.slfl.portfolio_project.model.user.Photographer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PhotographerRepository extends UserRepository {
    @Query("SELECT p FROM Photographer p WHERE p.username = :photographerUsername")
    Optional<Photographer> findPhotographerByUsername(@Param("photographerUsername") String photographerUsername);
}
