package com.slfl.portfolio_project.repository;

import com.slfl.portfolio_project.model.user.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends UserRepository {

    @Query("SELECT c FROM Customer c WHERE c.username = :customerUsername")
    Optional<Customer> findCustomerByUsername(@Param("customerUsername") String customerUsername);

    @Query("SELECT c FROM Customer c JOIN c.following p WHERE p.username = :photographerUsername")
    List<Customer> findFollowersOf(String photographerUsername);
}
