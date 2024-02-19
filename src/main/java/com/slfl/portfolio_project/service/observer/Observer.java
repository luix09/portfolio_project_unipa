package com.slfl.portfolio_project.service.observer;

import com.slfl.portfolio_project.model.User;

public abstract class Observer {
    protected User user;
    public abstract void update();
}
