package game.tools;

import game.gui.SnakeGameEnvironment;
import game.gui.menu.SnakeStartMenu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.*;

/** The empty game environment which draws a box around the graphical environment and reacts to keypresses.
 *  The class @{@link SnakeGameEnvironment} extends from this class and adds logic and additional graphic.
    The class @{@link SnakeStartMenu} extends from this class and adds logic and additional graphic.
    The class @{@link SnakeGameEnvironment} extends from this class and adds logic and additional graphic.
    The class @{@link SnakeGameEnvironment} extends from this class and adds logic and additional graphic.
    The class @{@link SnakeGameEnvironment} extends from this class and adds logic and additional graphic.
 *  The thread @{@link UiUpdateThread} is used to periodically repaint the GUI.
 * */
public abstract class GameEnvironment extends JPanel {
    private UiUpdateThread uiUpdateThread;
    private final int width, height;

    public GameEnvironment(int width, int height) {
        this.width = width;
        this.height = height;

        // --------------------------- Handle Keypress to method calls -----------------------------------

        // as an example see: https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
        // VK_Keys are supported: https://docs.microsoft.com/en-us/windows/desktop/inputdev/virtual-key-codes

        // Map Keys to action names
        getInputMap().put(KeyStroke.getKeyStroke("UP"), "key-up");
        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "key-down");
        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "key-left");
        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "key-right");

        // Map action names to method calls
        getActionMap().put("key-up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleKeypressUp();
            }
        });
        getActionMap().put("key-down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleKeypressDown();
            }
        });
        getActionMap().put("key-left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleKeypressLeft();
            }
        });
        getActionMap().put("key-right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleKeypressRight();
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "key-space");
        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "key-return");
        getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "key-escape");
        getActionMap().put("key-space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSpacePress();
            }
        });
        getActionMap().put("key-return", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleReturnPress();
            }
        });
        getActionMap().put("key-escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEscapePress();
            }
        });
    }
    protected abstract void handleEscapePress();
    protected abstract void handleReturnPress();
    protected abstract void handleSpacePress();
    protected abstract void handleKeypressUp();
    protected abstract void handleKeypressDown();
    protected abstract void handleKeypressLeft();
    protected abstract void handleKeypressRight();

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    protected abstract void drawSnakeEnvironment(Graphics2D graphics);

    private void doDrawing(Graphics g) {
        // Start UI Update Thread, if not initialized (and thus running) yet
        if (uiUpdateThread == null) {

            // initialize the updater thread which will periodically repaint the GUI
            uiUpdateThread = new UiUpdateThread(this);

            // start the UI updater thread
            uiUpdateThread.start();
        }

        // Cast the drawing helper to its actual type, so that we can use it to draw 2D
        Graphics2D graphics = (Graphics2D) g;

        // clear content
        graphics.clearRect(0, 0, this.width-1, this.height-1); // -1 since we draw zero-based (e.g. from 0 to 10 are 11 pixels)

        // Invoke code for drawing snake-specific data
        drawSnakeEnvironment(graphics);

        // draw the form border
        graphics.setColor(Color.BLACK);

        // draw the border
        graphics.drawRect(0, 0, this.width-1, this.height-1); // -1 since we draw zero-based
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

