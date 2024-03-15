package com.epam.jmp.app;

import com.epam.jmp.api.Bank;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.time.Month;

public class ServiceLoader {
    public static void main(String[] args) {
        Iterable<Service> servicesLoader = java.util.ServiceLoader.load(Service.class);
        Service service = servicesLoader.iterator().next();

        Iterable<Bank> bankLoader = java.util.ServiceLoader.load(Bank.class);
        Bank bank = bankLoader.iterator().next();

        var subscription = service.subscribe(bank.createBankCard(new User("Lika", "Glonti", LocalDate.of(2001, Month.APRIL, 20)), BankCardType.DEBIT));
        System.out.println(service.getAllPayableUsers());
        System.out.println(subscription.bankCard() + subscription.startDate());
        System.out.println(service.getAllSubscriptionsByCondition(subscription1 -> subscription1.user().birthday().isAfter(LocalDate.now().withYear(2000))));
    }

}
