package net.snowyhollows.ogam.rr.core;

import java.util.Random;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum RandomFactory implements BentoFactory<Random> {
	IT;

	@Override
	public Random createInContext(Bento bento) {
		return new Random();
	}
}
