package org.example.repository;



import org.example.model.Link;
import org.example.model.Transfer;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {


     List<Link> findByUser1(User user);

}