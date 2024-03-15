package com.epam.jmp.app;

import com.epam.jmp.api.Bank;
import com.epam.jmp.cloud.bank.api.BankImpl;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;
import com.epam.jmp.service.api.impl.ServiceImpl;
import com.epam.jmp.service.api.impl.SubscriptionNotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        Bank bank = new BankImpl();
        var user = new User("Lika", "Glonti", LocalDate.of(2001, Month.AUGUST, 17));

        var card = bank.createBankCard(user, BankCardType.DEBIT);
        System.out.println(card.getUser());
        Service service = new ServiceImpl();
        Subscription subscription = service.subscribe(card);

        var cardNumber = subscription.bankCard();
        System.out.println(cardNumber);
        Optional<Subscription> subs = service.getSubscriptionByBankCardNumber(cardNumber);

        service.getAllUsers().stream().forEach(System.out::println);

        var nonExistentCardNumber = UUID.randomUUID().toString();
        Optional<Subscription> subsNull = service.getSubscriptionByBankCardNumber(nonExistentCardNumber);

        try {
            System.out.println(subs.orElseThrow(() -> new SubscriptionNotFoundException("Couldn't find subscription for the card number " + cardNumber)));
            System.out.println(subsNull.orElseThrow(() -> new SubscriptionNotFoundException("Couldn't find subscription for the card number " + nonExistentCardNumber)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(service.getAverageUsersAge());
        System.out.println("is Payable:  " + Service.isPayableUser(new User("Lika", "Glonti", LocalDate.of(2001, Month.JANUARY, 1))));

        System.out.println("is Payable:  " + Service.isPayableUser(new User("Lika", "Glonti", LocalDate.of(2009, Month.JANUARY, 1))));

        service.getAllSubscriptionsByCondition(
                        subscription1 -> subscription1.startDate()
                                .isBefore(LocalDate.now().plusDays(10)))
                .forEach(System.out::println);

    }
}
