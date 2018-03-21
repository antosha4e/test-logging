package com.antosha4e;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// note: class name need not match the @Plugin name.
@Plugin(name = "TestLogAppender", category = "Core", elementType = "appender", printObject = true)
public class TestLogAppender extends AbstractAppender {

    private static TestLogAppender instance;

    public static TestLogAppender getInstance() {
        return instance;
    }

    public static TestLogAppender getInstance(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        if (instance == null) {
            instance = new TestLogAppender(name, filter, layout, true);
        }
        return instance;
    }

    private List<LogEvent> events = new ArrayList<>();

    protected TestLogAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    protected TestLogAppender(String name, Filter filter, Layout<? extends Serializable> layout, final boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @Override
    public void append(LogEvent event) {
        events.add(event);
    }

    public LogEvent getEvent(String loggerName, int index) {

        List<LogEvent> newEvents = events.stream().filter(lg -> loggerName.equals(lg.getLoggerName())).collect(Collectors.toList());

        if(newEvents.size() < index)
            return null;

        return newEvents.get(index);
    }


    // Your custom appender needs to declare a factory method
    // annotated with `@PluginFactory`. Log4j will parse the configuration
    // and call this factory method to construct an appender instance with
    // the configured attributes.
    @PluginFactory
    public static TestLogAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("otherAttribute") String otherAttribute) {

        if (name == null) {
            LOGGER.error("No name provided for TestLogAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }

        return getInstance(name, filter, layout, true);
    }
}
