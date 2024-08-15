package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// @RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HelloLombok {
    private final String hello;
    private final int lombok;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("김사남", 96);
        // helloLombok.setHello("김이남");
        // helloLombok.setLombok(98);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}
