package org.example.repository;


import org.example.model.Transfer;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    List<Transfer> findByFromUserEmail(String email);

    List<Transfer> findByToUserEmail(String email);
}