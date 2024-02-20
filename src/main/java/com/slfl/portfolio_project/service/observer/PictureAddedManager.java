package com.slfl.portfolio_project.service.observer;

import jakarta.mail.SendFailedException;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
public class PictureAddedManager {
    private List<Observer> customers;

    public PictureAddedManager() {
        customers = new ArrayList<>();
    }

    public void followPhotographer(Observer o) {
        customers.add(o);
    }

    public void unfollowPhotographer(Observer o) {
        customers.remove(o);
    }

    public void notifyCustomers(String photographer) throws SendFailedException {
        for (Observer customer : customers) {
            customer.update(photographer);
        }
    }
}
