package com.antosha4e;

import com.antosha4e.domain.CoffeeService;

public class Application {
    public static void main(String[] args) {

        CoffeeService coffeeService = new CoffeeService();

        coffeeService.makeCoffee("name", "latte");
    }
}
