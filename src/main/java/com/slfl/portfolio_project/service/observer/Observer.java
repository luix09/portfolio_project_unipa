package com.slfl.portfolio_project.service.observer;
import jakarta.mail.SendFailedException;

public interface Observer {
    void update(String photographer) throws SendFailedException;
}
