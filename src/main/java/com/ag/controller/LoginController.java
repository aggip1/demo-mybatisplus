package com.ag.controller;

import org.springframework.beans.factory.annotation.Value;

public class LoginController {

    @Value("${init.app}")
    private static String number;
    public static void main(String[] args) {
        System.out.println(number);
    }
}
