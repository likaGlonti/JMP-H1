package com.epam.jmp.service.api.impl;


import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {
    private final ArrayList<Subscription> subscriptions = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();
   private Connection connection = null;

    public ServiceImpl (){
        try {
            String url = "jdbc:sqlite:C:\\Users\\Lika_Glonti\\DB\\subscription\n";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Subscription subscribe(BankCard card) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, card.getCardNumber());
            statement.setLong(2, LocalDate.now().toEpochDay());
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }
        var newSubscription = new Subscription(card.getCardNumber(), LocalDate.now(), card.getUser());
        subscriptions.add(newSubscription);
        users.add(card.getUser());
        return newSubscription;
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return subscriptions.stream()
                .filter((Subscription subs) -> subs.bankCard().equals(number))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public double getAverageUsersAge() {
        var dateToday = LocalDate.now();
        return getAllUsers().stream()
                .map(user -> ChronoUnit.YEARS.between(user.birthday(), dateToday))
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }

    @Override
    public List<User> getAllPayableUsers() {
       return getAllUsers().stream()
                .filter(Service::isPayableUser)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.stream().filter(predicate).collect(Collectors.toList());
    }

    private final String INSERT = "INSERT INTO Subscription (card, start_date) VALUES (?,?)";
}
