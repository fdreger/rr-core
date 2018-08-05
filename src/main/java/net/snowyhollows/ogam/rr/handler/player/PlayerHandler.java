package net.snowyhollows.ogam.rr.handler.player;

import net.snowyhollows.ogam.rr.core.Action;
import net.snowyhollows.ogam.rr.core.Entity;

import java.util.List;
import java.util.Scanner;

public class PlayerHandler {

    public void act(Entity entity) {

        List<Action> actions = entity.enumeratePossibleActions();

        System.out.println("your move:");
        int i = 0;
        for (Action action : actions) {
            System.out.printf(" %d - %s\n", i++, action.getLabel());
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            if (s.matches("\\d+")) {
                int choice = (Integer.parseInt(s)) - 1;
                if (choice >= 0 && choice < actions.size()) {
                    actions.get(choice).perform();
                }
                break;
            }
        }

    }
}
