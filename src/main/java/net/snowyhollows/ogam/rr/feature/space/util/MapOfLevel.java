package net.snowyhollows.ogam.rr.feature.space.util;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.space.Coords;
import net.snowyhollows.ogam.rr.feature.space.SomethingThatOccupiesSpace;

import java.util.*;

public class MapOfLevel implements SomethingThatOccupiesSpace {

    private final List<String> rows = new ArrayList<>();
    private final Map<Character, Entity> mapping = new HashMap<>();

    public void addMapping(char c, Entity e) {
        mapping.put(c, e);
    }

    public void addRow(String row) {
        rows.add(row);
    }

    @Override
    public Optional<Entity> presentAt(Coords coords) {
        if (rows.size() > coords.row && rows.get(coords.row).length() > coords.col) {
            char target = rows.get(coords.row).charAt(coords.col);
            if (mapping.containsKey(target)) {
                return Optional.of(mapping.get(target));
            }
        }
        return Optional.empty();
    }
}
