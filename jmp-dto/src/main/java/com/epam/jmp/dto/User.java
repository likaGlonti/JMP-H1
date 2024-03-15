package com.epam.jmp.dto;

import java.time.LocalDate;

public record User(String name, String surname, LocalDate birthday) {
}
