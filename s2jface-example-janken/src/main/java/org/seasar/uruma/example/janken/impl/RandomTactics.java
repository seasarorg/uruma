/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.uruma.example.janken.impl;

import org.seasar.uruma.example.janken.Janken;
import org.seasar.uruma.example.janken.Tactics;

/**
 * @author y-komori
 * 
 */
public class RandomTactics implements Tactics {
	public int readTactics() {
		int hand = 0;

		double randomNum = Math.random() * 3;
		if (randomNum < 1) {
			hand = Janken.STONE;
		} else if (randomNum < 2) {
			hand = Janken.SCISSORS;
		} else if (randomNum < 3) {
			hand = Janken.PAPER;
		}

		return hand;
	}
}
