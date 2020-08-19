package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogParsingResult {
    List<String> messages = new ArrayList<>();
    List<String> renames = new ArrayList<>();
    Map<String, List<String>> userMessages = new HashMap<>();

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getRenames() {
        return renames;
    }

    public Map<String, List<String>> getUserMessages() {
        return userMessages;
    }

    public void addRenames(String rename) {
        renames.add(rename);
        final String[] split = rename.split(" ");
        String userBeingRenamed = split[3];
        String userRenamedTo = split[5];
        final List<String> oldUserMessages = userMessages.getOrDefault(userBeingRenamed, new ArrayList<>());
        final List<String> retainedUserMessages = userMessages.getOrDefault(userRenamedTo, new ArrayList<>());
        retainedUserMessages.addAll(oldUserMessages);
        userMessages.put(userRenamedTo, retainedUserMessages);
        userMessages.remove(userBeingRenamed);
    }

    public void addMessage(String message) {
        messages.add(message);
        final String[] split = message.split(" ");
        final String user = split[1].replace("<", "").replace(">", "");
        final List<String> existingMessages = userMessages.getOrDefault(user, new ArrayList<>());
        existingMessages.add(message);
        userMessages.put(user, existingMessages);
    }
}
