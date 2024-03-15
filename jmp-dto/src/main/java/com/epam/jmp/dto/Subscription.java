package com.epam.jmp.dto;

import java.time.LocalDate;

public record Subscription(String bankCard, LocalDate startDate, User user) {
}
