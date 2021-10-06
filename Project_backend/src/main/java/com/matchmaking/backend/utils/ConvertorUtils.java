package com.matchmaking.backend.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ConvertorUtils {

    public static LocalDate stringToLocalDate(String date){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date,fmt);
    }
}
