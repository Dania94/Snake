package game.tools;

import game.gui.SnakeGame;
import game.gui.settings.Settings;

/**
 * The {@link UiUpdateThread update thread} will continuously cause the {@link GameEnvironment} to be redrawn.
 */
public class UiUpdateThread extends Thread {
    private final GameEnvironment drawingEnv;

    public UiUpdateThread(GameEnvironment drawingEnvironment) {
        drawingEnv = drawingEnvironment;

        // Terminate thread once the application's main thread has terminated. Otherwise the java application would keep running after the dialog/window has been closed.
        setDaemon(true);
    }

    @Override
    public void run() {
        while(drawingEnv.isVisible()) {
            drawingEnv.repaint();

            try {

                int threadMilis = 5000;
                if(drawingEnv instanceof SnakeGame){
                    switch (Settings.Instance().getGameSpeed()) {
                        case SPEED1:
                            Thread.sleep(threadMilis/10); break;
                        case SPEED2:
                            Thread.sleep(threadMilis/15); break;
                        case SPEED3:
                            Thread.sleep(threadMilis/20); break;
                        case SPEED4:
                            Thread.sleep(threadMilis/25); break;
                        case SPEED5:
                            Thread.sleep(threadMilis/35); break;
                        case SPEED6:
                            Thread.sleep(threadMilis/40); break;
                        case SPEED7:
                            Thread.sleep(threadMilis/45); break;
                        case SPEED8:
                            Thread.sleep(threadMilis/50); break;
                        case SPEED9:
                            Thread.sleep(threadMilis/55); break;
                        case SPEED10:
                            Thread.sleep(threadMilis/60); break;
                    }
                }


            } catch (InterruptedException e) {
                // Exception won't happen unless someone invokes "updateThread.interrupt()" - and I won't be that someone. ^^
                e.printStackTrace();
            }
        }
    }
}
