package org.example.service.form;


import org.example.model.User;

public class TransferForm {

    private User toUser;
    private Double amountBeforeFee;
    private Double amountAfterFee;
    private User fromUser;

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Double getAmountBeforeFee() {
        return amountBeforeFee;
    }

    public void setAmountBeforeFee(Double amountBeforeFee) {
        this.amountBeforeFee = amountBeforeFee;
    }

    public Double getAmountAfterFee() {
        return amountAfterFee;
    }

    public void setAmountAfterFee(Double amountAfterFee) {
        this.amountAfterFee = amountAfterFee;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
