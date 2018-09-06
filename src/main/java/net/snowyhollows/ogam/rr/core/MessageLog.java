package net.snowyhollows.ogam.rr.core;

import net.snowyhollows.bento2.annotation.WithFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessageLog {

    private List<String> messages = new ArrayList<>();

    @WithFactory
    public MessageLog() {
    }

    public List<String> getLastNMessages(int n) {
        if (messages.size() == 0) {
            return Collections.emptyList();
        }
        int fromIndex = Math.max(0, messages.size() - n);
        return messages.subList(fromIndex, messages.size());
    }

    public void info(String s) {
        messages.add(s);
    }
}
