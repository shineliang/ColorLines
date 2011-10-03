package com.shine.app.game.other;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class Test1 {

	@Test
	public void test() {
		String a = null;
		if (a instanceof String) {
			Assert.fail();
		}
	}

}
