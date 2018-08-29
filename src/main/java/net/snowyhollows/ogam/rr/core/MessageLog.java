package net.snowyhollows.ogam.rr.core;

import net.snowyhollows.bento2.annotation.WithFactory;

import java.util.ArrayList;
import java.util.List;

public class MessageLog {

    private List<String> messages = new ArrayList<>();

    @WithFactory
    public MessageLog() {
    }

    public List<String> getLastNMessages(int n) {
        int fromIndex = Math.min(0, messages.size() - n);
        int toIndex = Math.max(0, messages.size() - 1);
        return messages.subList(fromIndex, toIndex);
    }

    public void info(String s) {
        messages.add(s);
    }
}
