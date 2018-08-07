package net.snowyhollows.ogam.rr;

import net.bajobongo.beach.engine.Engine;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum EngineFactory implements BentoFactory<Engine> {
	IT;

	@Override
	public Engine createInContext(Bento bento) {
		return new Engine();
	}
}
