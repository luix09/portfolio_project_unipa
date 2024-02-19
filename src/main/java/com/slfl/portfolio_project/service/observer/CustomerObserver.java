package com.slfl.portfolio_project.service.observer;

public class CustomerObserver extends Observer {
    public CustomerObserver(FollowedPhotographerDecorator userFollowDecorator) {
        this.user = userFollowDecorator;
    }

    @Override
    public void update() {
        // manda email di notifica aggiunta immagine
    }
}
