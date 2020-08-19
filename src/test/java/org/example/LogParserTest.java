package org.example;

import org.example.model.LogParsingResult;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LogParserTest {

    private LogParser subject;

    @Before
    public void setUp() {
        subject = new LogParser();
    }

    @Test
    public void testParse() {
        String SIMPLE_LOG_1 = "07:05:55 <user1> This is the message\n" +
                              "07:05:55 <user2> This is the message\n" +
                              "07:05:55 <supercooluser> This is the message\n" +
                              "07:05:55 --- nick: supercooluser -> user2\n" +
                              "07:05:55 <user3> This is the message\n" +
                              "07:05:55 <user5> This is the message\n" +
                              "07:05:55 <user6> This is the message\n";

        final LogParsingResult result = subject.parse(SIMPLE_LOG_1);
        assertThat(result.getMessages().size(), is(6));
        assertThat(result.getRenames().size(), is(1));
        //count of users with at least one message
        assertThat(result.getUserMessages().keySet().size(), is(5));
    }
}
