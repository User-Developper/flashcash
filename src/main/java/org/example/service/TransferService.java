package org.example.service;


import org.example.model.Transfer;
import org.example.repository.TransferRepository;
import org.example.repository.UserAccountRepository;
import org.example.repository.UserRepository;
import org.example.service.form.AddToFlashCashForm;
import org.example.service.form.TransferForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Transfer> findTransactions() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Collections.emptyList();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User springUser =
                    (org.springframework.security.core.userdetails.User) principal;

            String username = springUser.getUsername();

            // Adapter selon ton besoin (sender, receiver, etc.)
            return transferRepository.findByFromUserEmail(username);
        }

        return Collections.emptyList();
    }


    public Transfer sendMoney(TransferForm transferForm){


        if (transferForm.getFromUser().getAccount().getAmount() < transferForm.getAmountBeforeFee()) {

            throw new RuntimeException("pas assez de solde disponible");
        }

        double amountToSend = transferForm.getAmountBeforeFee();
        double fee = transferForm.getAmountBeforeFee() * 0.05;
        double amountAfterFee = amountToSend - fee;


            transferForm.getFromUser().getAccount().setAmount(transferForm.getFromUser().getAccount().getAmount() - amountToSend);

            transferForm.getToUser().getAccount().setAmount(transferForm.getToUser().getAccount().getAmount() + amountAfterFee);



            userRepository.save(transferForm.getFromUser());
            userRepository.save(transferForm.getToUser());



        Transfer money = new Transfer();

        money.setAmountBeforeFee(transferForm.getAmountBeforeFee());
        money.setAmountAfterFee(amountAfterFee);
        money.setFromUser(transferForm.getFromUser());
        money.setToUser(transferForm.getToUser());


        return money;


    }

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    SessionService sessionService;


    public void transferToAccount(AddToFlashCashForm form){


        if (form != null) {

            userAccountRepository.save(sessionService.sessionUser().getAccount().plus(form.getAmount()));



        } else{

            throw  new RuntimeException("erreur");
        }


    }
}