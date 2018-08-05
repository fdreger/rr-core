package net.snowyhollows.ogam.rr.core;

public interface Action {
    ActionLabel getLabel();
    void perform();
}
