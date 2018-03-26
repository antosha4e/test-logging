package com.antosha4e.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeService {

    private final static Logger log = LoggerFactory.getLogger(CoffeeService.class);

    public void makeCoffee(String baristaName, String coffeeName) {

        log.info("Requested coffee [{}] from barista [{}]", coffeeName, baristaName);

        // do a real coffee magic here

        log.info("Barista [{}] made a coffee [{}]", baristaName, coffeeName);
    }
}
