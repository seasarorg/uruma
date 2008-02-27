/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.example.janken;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.eclipse.common.util.SWTUtil;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.example.janken.impl.PlayerImpl;

/**
 * @author y-komori
 * 
 */
@Component(name = "jankenAction")
public class JankenAction {
	public Label playerHandImage;

	public Label computerHandImage;

	public Label messageLabel;

	public Label computerWinLabel;

	public Label playerWinLabel;

	public Button guButton;

	public Button paButton;

	public Button cyokiButton;

	public Player computer;

	public Player player = new PlayerImpl();

	@EventListener(id = "guButton")
	public void goo() {
		call(Janken.STONE);
	}

	@EventListener(id = "paButton")
	public void pa() {
		call(Janken.PAPER);
	}

	@EventListener(id = "cyokiButton")
	public void cyoki() {
		call(Janken.SCISSORS);
	}

	private void call(final int hand) {
		setEnableButtons(false);
		showImage(hand, playerHandImage);

		int computerHand = computer.showHand();
		showImage(computerHand, computerHandImage);

		messageLabel.setText("ぽん！");
		sleep(1000);

		if (isWinner(hand, computerHand)) {
			messageLabel.setText("あなたの勝ち！");
			messageLabel.setForeground(SWTUtil.getColor("red"));
			player.win();
			playerWinLabel.setText(player.getWinCount() + " 勝");

		} else if (isWinner(computerHand, hand)) {
			messageLabel.setText("あなたの負け・・・");
			messageLabel.setForeground(SWTUtil.getColor("blue"));
			computer.win();
			computerWinLabel.setText(computer.getWinCount() + " 勝");
		} else {
			messageLabel.setText("引き分け");
		}
		sleep(1000);

		setEnableButtons(true);
		clearImage();
		messageLabel.setForeground(SWTUtil.getColor("black"));
		messageLabel.setText("じゃんけん・・・");
	}

	private void sleep(final int time) {
		Display.getCurrent().update();
		try {
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private boolean isWinner(final int playerHand, final int enemyHand) {
		if ((playerHand == Janken.STONE && enemyHand == Janken.SCISSORS)
				|| (playerHand == Janken.SCISSORS && enemyHand == Janken.PAPER)
				|| (playerHand == Janken.PAPER && enemyHand == Janken.STONE)) {
			return true;
		} else {
			return false;
		}
	}

	private void clearImage() {
		Image image = ImageManager.getImage("blank");
		playerHandImage.setImage(image);
		computerHandImage.setImage(image);
	}

	private void showImage(final int hand, final Label label) {
		Image image = null;
		switch (hand) {
		case Janken.STONE:
			image = ImageManager.getImage("gu");
			break;

		case Janken.SCISSORS:
			image = ImageManager.getImage("cyoki");
			break;

		case Janken.PAPER:
			image = ImageManager.getImage("pa");
			break;

		default:
			break;
		}
		label.setImage(image);
	}

	private void setEnableButtons(final boolean enabled) {
		guButton.setEnabled(enabled);
		cyokiButton.setEnabled(enabled);
		paButton.setEnabled(enabled);
	}

	public void setComputer(final Player computer) {
		this.computer = computer;
	}
}
