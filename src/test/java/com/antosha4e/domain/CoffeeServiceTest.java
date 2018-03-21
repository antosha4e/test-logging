package com.antosha4e.domain;

import com.antosha4e.TestLogAppender;
import org.apache.logging.log4j.message.Message;
import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CoffeeServiceTest {

    private CoffeeService coffeeService = new CoffeeService();

    @Test
    public void testLogOutputNaive() {

        final String baristaName = "Stavros Domatiotis";
        final String coffeeName = "Flat White";

        coffeeService.orderCoffee(baristaName, coffeeName);

        assertTrue("Wrong formatted message", getEventMessage(0).getFormattedMessage().equals(format("Barista [%s] making a coffee [%s]", baristaName, coffeeName)));
    }

    @Test
    public void testLogOutputFull() {

        final String baristaName = "Stavros Domatiotis";
        final String coffeeName = "Flat White";
        final String expectedFormatedMessage = format("Barista [%s] making a coffee [%s]", baristaName, coffeeName);

        coffeeService.orderCoffee(baristaName, coffeeName);

        final Message logMessage = getEventMessage(0);

        assertNotNull("Log message is null", logMessage);
        assertTrue("Wrong formatted message", logMessage.getFormattedMessage().equals(expectedFormatedMessage));
        assertTrue("Wrong parameters size", logMessage.getParameters().length == 2);
        assertTrue("Wrong 1 param", logMessage.getParameters()[0].equals(baristaName));
        assertTrue("Wrong 2 param", logMessage.getParameters()[1].equals(coffeeName));
    }

    private Message getEventMessage(int eventIndex) {

        return TestLogAppender.getInstance().getEvent(CoffeeService.class.getName(), eventIndex).getMessage();
    }
}
