package com.slfl.portfolio_project.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.slfl.portfolio_project.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "photographers")
@EnableAutoConfiguration
@JsonIgnoreProperties("followers")
public class Photographer extends User {
    @ManyToMany(mappedBy = "following")
    @JsonIgnoreProperties
    private List<Customer> followers;

    public Photographer(String username, String email, String password, Role type) {
        super(username, email, password, type);
    }
}
