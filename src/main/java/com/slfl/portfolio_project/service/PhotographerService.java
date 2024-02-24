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

        // Recupera i followers di un fotografo dal database
        for (Customer customer : this.getFollowersOf(photographer.getUsername())) {
            // Aggiungi tutti i followers come observers del fotografo
            CustomerObserver observer = new CustomerObserver(customer);
            observers.add(observer);
        }

        pictureManager.setCustomers(observers);
        pictureManager.notifyCustomers(photographer.getUsername());
    }

    public Photographer getPhotographerByUsername(String username) throws Exception {
        Optional<Photographer> photographer = photographerRepository.findPhotographerByUsername(username);
        if(photographer.isEmpty()) {
            throw new Exception("Fotografo non trovato.");
        }
        return photographer.get();
    }

    public Customer getCustomerByToken(String token) throws Exception {
        User customerUser = userService.getUserFromToken(token);
        Optional<Customer> customer = customerRepository.findCustomerByUsername(customerUser.getUsername());
        if(customer.isEmpty()) {
            throw new Exception("Customer non trovato.");
        }
        return customer.get();
    }

    public void followPhotographer(String token, String username) throws Exception {
        // Trova il cliente a partire dal token della richiesta HTTP
        // Trova il fotografo in base allo username passato come parametro
        Photographer followedPhotographer = getPhotographerByUsername(username);
        Customer newFollower = getCustomerByToken(token);

        // Aggiornamento dei fotografi seguiti dal cliente
        List<Photographer> following = newFollower.getFollowing();
        following.add(followedPhotographer);
        newFollower.setFollowing(following);
        customerRepository.save(newFollower);

        // Aggiornamento dei followers del fotografo
        List<Customer> followers = followedPhotographer.getFollowers();
        followers.add(newFollower);
        followedPhotographer.setFollowers(followers);
        photographerRepository.save(followedPhotographer);

        pictureManager.followPhotographer(new CustomerObserver(newFollower));
    }

    public void unfollowPhotographer(String token, String username) throws Exception {
        // Trova il cliente a partire dal token della richiesta HTTP
        // Trova il fotografo in base allo username passato come parametro
        Photographer unfollowedPhotographer = getPhotographerByUsername(username);
        Customer oldFollower = getCustomerByToken(token);

        // Rimuovi fotografo dalla lista dei seguiti del cliente
        List<Photographer> following = oldFollower.getFollowing();
        following.remove(unfollowedPhotographer);
        oldFollower.setFollowing(following);
        customerRepository.save(oldFollower);

        // Rimuovi cliente dalla lista dei followers del fotografo
        List<Customer> followers = unfollowedPhotographer.getFollowers();
        followers.remove(oldFollower);
        unfollowedPhotographer.setFollowers(followers);
        photographerRepository.save(unfollowedPhotographer);

        pictureManager.unfollowPhotographer(new CustomerObserver(oldFollower));
    }
}
