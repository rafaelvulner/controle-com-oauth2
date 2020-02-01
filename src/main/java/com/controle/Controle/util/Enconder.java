package com.controle.Controle.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Enconder {

    public static void main(String[] args){

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

        System.out.println(bc.encode("admin"));
    }
}
