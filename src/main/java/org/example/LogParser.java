package org.example;

import org.example.model.LogParsingResult;

import java.util.Arrays;

public class LogParser {

    public LogParsingResult parse(String logContent) {

        LogParsingResult result = new LogParsingResult();

        final String[] lines = logContent.split("\n");

        Arrays.stream(lines)
                .forEach(
                        l -> {
                            if (l.contains("<")) {
                                result.addMessage(l);
                            } else if (l.contains("---") && l.contains("->")) {
                                result.addRenames(l);
                            }
                        });

        return result;
    }
}
