package com.slfl.portfolio_project.service.observer;

import com.slfl.portfolio_project.model.User;

import java.util.ArrayList;

public class FollowedPhotographerDecorator extends User {
    private ArrayList<Observer> customers = new ArrayList<>();

    public void followPhotographer(Observer o) {
        customers.add(o);
    }

    public void notifyCustomers() {
       notifyObservers();
    }

    private void notifyObservers() {
        for (Observer customer : customers) {
            customer.update();
        }
    }
}
