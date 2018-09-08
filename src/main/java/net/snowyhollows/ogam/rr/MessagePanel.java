package net.snowyhollows.ogam.rr;

import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.core.MessageLog;
import net.snowyhollows.ogam.rr.feature.ascii.AsciiPanel;
import net.snowyhollows.ogam.rr.feature.ascii.AsciiWindow;
import net.snowyhollows.ogam.rr.feature.ascii.DecoratedAsciiWindow;
import net.snowyhollows.ogam.rr.feature.ascii.component.Color;

import java.util.List;

public class MessagePanel {
    private final DecoratedAsciiWindow decorated;
    private AsciiPanel myPanel;
    private final MessageLog messageLog;

    @WithFactory
    public MessagePanel(@ByName("asciiPanel")AsciiPanel asciiPanel, MessageLog messageLog) {
        AsciiWindow positioned = new AsciiWindow(asciiPanel);
        this.messageLog = messageLog;
        positioned.setX(4);
        positioned.setY(35 - 4 - 2);
        positioned.setWidth(80 - 8);
        positioned.setHeight(8);
        decorated = new DecoratedAsciiWindow(positioned);
        myPanel = decorated.createInnerWindow();
    }

    public void run() {
        decorated.paint();
        myPanel.clear();
        final List<String> msgs = messageLog.getLastNMessages(myPanel.getHeight());
        for (int i = 0; i < msgs.size(); i++) {
            myPanel.putStr(i + 0, 0, Color.BLACK, Color.WHITE, msgs.get(i));
        }
    }
}
