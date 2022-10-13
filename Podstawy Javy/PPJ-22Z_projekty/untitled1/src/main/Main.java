package main;

import dog.Dog;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nadaj psu imie");
        String name = scanner.nextLine();
        Dog dog = new Dog();
        dog.setName(name);
        System.out.println("Twoj piesek to: "+dog.getName());

    }
}
