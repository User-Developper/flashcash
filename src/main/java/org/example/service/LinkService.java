package org.example.service;


import org.example.model.Link;
import org.example.model.User;
import org.example.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkService {


    @Autowired
    LinkRepository linkRepository;

    public List<User> findContact(User user){

        List<Link> links = linkRepository.findByUser1(user);

        List<User> contacts = new ArrayList<>();

        for (Link link : links){

            contacts.add(link.getUser2());
        }

        return contacts;



    }


}
