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

        coffeeService.makeCoffee(baristaName, coffeeName);

        assertTrue("Wrong formatted message", getEventMessage(0).getFormattedMessage().equals(format("Requested coffee [%s] from barista [%s]", coffeeName, baristaName)));
        assertTrue("Wrong formatted message", getEventMessage(1).getFormattedMessage().equals(format("Barista [%s] made a coffee [%s]", baristaName, coffeeName)));
    }

    @Test
    public void testLogOutputFull() {

        final String baristaName = "Stavros Domatiotis";
        final String coffeeName = "Flat White";

        coffeeService.makeCoffee(baristaName, coffeeName);

        final Message logMessage1 = getEventMessage(0);
        final String expectedFormatedMessage1 = format("Requested coffee [%s] from barista [%s]", coffeeName, baristaName);

        assertNotNull("Log message is null", logMessage1);
        assertTrue("Wrong formatted message", logMessage1.getFormattedMessage().equals(expectedFormatedMessage1));
        assertTrue("Wrong parameters size", logMessage1.getParameters().length == 2);
        assertTrue("Wrong 1 param", logMessage1.getParameters()[0].equals(coffeeName));
        assertTrue("Wrong 2 param", logMessage1.getParameters()[1].equals(baristaName));

        final Message logMessage2 = getEventMessage(1);
        final String expectedFormatedMessage2 = format("Barista [%s] made a coffee [%s]", baristaName, coffeeName);

        assertNotNull("Log message is null", logMessage2);
        assertTrue("Wrong formatted message", logMessage2.getFormattedMessage().equals(expectedFormatedMessage2));
        assertTrue("Wrong parameters size", logMessage2.getParameters().length == 2);
        assertTrue("Wrong 1 param", logMessage2.getParameters()[0].equals(baristaName));
        assertTrue("Wrong 2 param", logMessage2.getParameters()[1].equals(coffeeName));
    }

    private Message getEventMessage(int eventIndex) {

        return TestLogAppender.getInstance().getEvent(CoffeeService.class.getName(), eventIndex).getMessage();
    }
}
