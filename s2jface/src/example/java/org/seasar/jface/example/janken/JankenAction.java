/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.example.janken;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.eclipse.common.util.SWTUtil;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.example.janken.impl.PlayerImpl;

/**
 * @author y-komori
 * 
 */
@Component(name = "jankenAction")
public class JankenAction {
    private Label playerHandImage;

    private Label computerHandImage;

    private Label messageLabel;

    private Label computerWinLabel;

    private Label playerWinLabel;

    private Button guButton;

    private Button paButton;

    private Button cyokiButton;

    private Player computer;

    private Player player = new PlayerImpl();

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

    private void call(int hand) {
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

    private void sleep(int time) {
        Display.getCurrent().update();
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isWinner(int playerHand, int enemyHand) {
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

    private void showImage(int hand, Label label) {
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

    private void setEnableButtons(boolean enabled) {
        guButton.setEnabled(enabled);
        cyokiButton.setEnabled(enabled);
        paButton.setEnabled(enabled);
    }

    public void setComputer(Player computer) {
        this.computer = computer;
    }
}
