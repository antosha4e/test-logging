package com.antosha4e.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeService {

    private final static Logger log = LoggerFactory.getLogger(CoffeeService.class);

    public void orderCoffee(String baristaName, String coffeeName) {

        log.info("Barista [{}] making a coffee [{}]", baristaName, coffeeName);

        // doing real logic here

        // ...
    }
}
