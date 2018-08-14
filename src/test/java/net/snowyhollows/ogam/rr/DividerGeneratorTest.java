package net.snowyhollows.ogam.rr;

import net.snowyhollows.ogam.rr.core.Entity;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentation;
import net.snowyhollows.ogam.rr.feature.ascii.component.AsciiRepresentationImpl;
import net.snowyhollows.ogam.rr.util.ObjectArray2D;

import org.junit.Test;

/**
 * @author efildre
 */
public class DividerGeneratorTest {


    @Test
    public void shouldNotThrow() {
        Entity wall = new Entity();
        Entity nothing = new Entity();

        wall.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.CYAN, '#');
        nothing.asciiRepresentation = new AsciiRepresentationImpl(AsciiRepresentation.Color.CYAN, ' ');

        DividerGenerator dg = new DividerGenerator(25, 80, wall, nothing);
        ObjectArray2D entityArray2D = new ObjectArray2D(30, 85, nothing, null);
        dg.render(entityArray2D, () -> new Entity());

        System.out.println(entityArray2D.toString(e -> e.asciiRepresentation));
    }
}
