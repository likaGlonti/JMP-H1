package com.epam.jmp.cloud.bank.api;

import com.epam.jmp.api.Bank;
import com.epam.jmp.dto.*;

public class BankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        return switch (cardType) {
            case CREDIT -> new CreditBankCard(user);
            case DEBIT -> new DebitBankCard(user);
        };
    }
}
