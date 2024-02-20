package com.slfl.portfolio_project.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.slfl.portfolio_project.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "customers")
@EnableAutoConfiguration
@JsonIgnoreProperties("following")
public class Customer extends User {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "customer_follows_photographer",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "photographer_id", referencedColumnName = "user_id")
    )
    private List<Photographer> following;

    public Customer(String username, String email, String password, Role type) {
        super(username, email, password, type);
    }
}
