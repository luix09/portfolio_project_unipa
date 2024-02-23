package com.slfl.portfolio_project.service.observer;

import com.slfl.portfolio_project.model.user.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CustomerObserver implements Observer {
    private Customer customer;

    @Override
    public void update(String photographer) {
        System.out.println(this.customer.getEmail() + "Nuova foto caricata" + "\nUna nuova foto è stata caricata dal fotografo " + photographer);
    }
}
