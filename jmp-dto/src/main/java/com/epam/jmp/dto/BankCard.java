package com.epam.jmp.dto;


import java.util.UUID;

public sealed class BankCard permits CreditBankCard, DebitBankCard {
    private final String cardNumber;
    private final User user;

    BankCard(User user) {
        this.cardNumber = UUID.randomUUID().toString();
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public User getUser() {
        return user;
    }
}
