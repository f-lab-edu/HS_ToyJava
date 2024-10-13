package com.example.sortproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomListProjectApplication {

    public static void main(String[] args) {
        CustomList<String> customList = new CustomList<>();
        customList.add("a");
        customList.add("b");

        System.out.println(customList.size());
        System.out.println(customList.get(0));
        System.out.println(customList.get(1));
        System.out.println(customList.get(2));
    }

}
