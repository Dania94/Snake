package game.gui;

import game.gui.settings.Settings;
import game.gui.view.FoodView;
import game.gui.view.GameIndicatorBar;
import game.gui.view.GameLevelView;
import game.gui.view.SnakeView;
import game.model.SnakeMove;
import game.tools.GameEnvironment;
import game.tools.GuiContainer;
import game.tools.StaticDefinitions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Random;

import static game.model.GameDifficulty.HARD;
import static game.model.GameDifficulty.NORMAL;


public class SnakeGameEnvironment extends GameEnvironment {

    public SnakeGameEnvironment() {
        super(StaticDefinitions.Instance().getWidth(), StaticDefinitions.Instance().getHeight());
    }


    @Override
    protected void handleEscapePress() {

    }

    @Override
    protected void handleReturnPress() {

    }

    @Override
    protected void handleSpacePress() {

    }

    @Override
    protected void handleKeypressUp() {

    }

    @Override
    protected void handleKeypressDown() {

    }

    @Override
    protected void handleKeypressLeft() {

    }

    @Override
    protected void handleKeypressRight() {

    }

    @Override
    public void drawSnakeEnvironment(Graphics2D graphics) {

    }

}

