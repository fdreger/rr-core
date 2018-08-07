package net.snowyhollows.ogam.rr;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * @author efildre
 */
public enum BentoInstance implements BentoFactory<Bento> {
	IT;

	@Override
	public Bento createInContext(Bento bento) {
		return bento;
	}
}
