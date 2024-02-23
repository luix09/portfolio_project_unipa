package com.slfl.portfolio_project.service;

import com.slfl.portfolio_project.model.user.Customer;
import com.slfl.portfolio_project.model.user.Photographer;
import com.slfl.portfolio_project.model.user.User;
import com.slfl.portfolio_project.repository.CustomerRepository;
import com.slfl.portfolio_project.repository.PhotographerRepository;
import com.slfl.portfolio_project.service.observer.CustomerObserver;
import com.slfl.portfolio_project.service.observer.Observer;
import com.slfl.portfolio_project.service.observer.PictureAddedManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhotographerService {
    private final CustomerRepository customerRepository;
    private final PhotographerRepository photographerRepository;
    private final UserService userService;

    private final PictureAddedManager pictureManager;

    @Autowired
    PhotographerService(CustomerRepository customerRepository, UserService userService, PhotographerRepository photographerRepository) {
        this.customerRepository = customerRepository;
        this.userService = userService;
        this.photographerRepository = photographerRepository;
        pictureManager = new PictureAddedManager();
    }

    public List<Customer> getFollowersOf(String username) {
        return customerRepository.findFollowersOf(username);
    }

    public void notifyFollowers(String token) throws Exception {
        User photographer = userService.getUserFromToken(token);
        List<Observer> observers = new ArrayList<>();
        for (Customer customer : this.getFollowersOf(photographer.getUsername())) {
            CustomerObserver observer = new CustomerObserver(customer);
            observers.add(observer);
        }

        pictureManager.setCustomers(observers);
        pictureManager.notifyCustomers(photographer.getUsername());
    }

    public void followPhotographer(String token, String username) throws Exception {
        User customerUser = userService.getUserFromToken(token);
        Optional<Customer> customer = customerRepository.findCustomerByUsername(customerUser.getUsername());
        if(customer.isEmpty()) {
            throw new Exception("Customer non trovato.");
        }
        Optional<Photographer> photographer = photographerRepository.findPhotographerByUsername(username);
        if(photographer.isEmpty()) {
            throw new Exception("Photographer non trovato.");
        }
        Photographer followedPhotographer = photographer.get();
        Customer newFollower = customer.get();

        // Updating customer followings
        List<Photographer> following = newFollower.getFollowing();
        following.add(followedPhotographer);
        newFollower.setFollowing(following);
        customerRepository.save(newFollower);

        // Updating photographer followers
        List<Customer> followers = followedPhotographer.getFollowers();
        followers.add(newFollower);
        followedPhotographer.setFollowers(followers);
        photographerRepository.save(followedPhotographer);

        pictureManager.followPhotographer(new CustomerObserver(customer.get()));
    }

    public void unfollowPhotographer(String token, String username) throws Exception {
        User customerUser = userService.getUserFromToken(token);
        Optional<Customer> customer = customerRepository.findCustomerByUsername(customerUser.getUsername());
        if(customer.isEmpty()) {
            throw new Exception("Customer non trovato.");
        }
        Optional<Photographer> photographer = photographerRepository.findPhotographerByUsername(username);
        if(photographer.isEmpty()) {
            throw new Exception("Photographer non trovato.");
        }
        Photographer unfollowedPhotographer = photographer.get();
        Customer oldFollower = customer.get();

        // Updating customer followings
        List<Photographer> following = oldFollower.getFollowing();
        following.remove(unfollowedPhotographer);
        oldFollower.setFollowing(following);
        customerRepository.save(oldFollower);

        // Updating photographer followers
        List<Customer> followers = unfollowedPhotographer.getFollowers();
        followers.remove(oldFollower);
        unfollowedPhotographer.setFollowers(followers);
        photographerRepository.save(unfollowedPhotographer);

        pictureManager.unfollowPhotographer(new CustomerObserver(customer.get()));
    }
}
