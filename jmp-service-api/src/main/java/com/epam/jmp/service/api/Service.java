package com.epam.jmp.service.api;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    default String getName(){
        return "Service" ;
    }
    Subscription subscribe(BankCard card);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);
    List<User> getAllUsers();

    double getAverageUsersAge();

    List<User> getAllPayableUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

    static boolean isPayableUser(User user) {
        return user.birthday()
                .isBefore(LocalDate.now().minus(Period.ofYears(17)));
    }

}
