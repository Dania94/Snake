package game.tools;

import game.gui.SnakeGameEnvironment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;

/** This container class is used to simply show an empty window, which contains the @{@link SnakeGameEnvironment}.*/
public class GuiContainer extends JDialog {
    private static final int windowHeightOffset = 39;
    private static final int windowWidthOffset = 16;
    private static JFrame frame;
    public GuiContainer(String dialogTitle, GameEnvironment drawingEnvironment) {
        super(createTaskbarFrame(dialogTitle));

        // add the drawing environment to this dialog/window
        add(drawingEnvironment);

        // Configure the dialog (size, title)
        setSize(StaticDefinitions.Instance().getWidth() + windowWidthOffset, StaticDefinitions.Instance().getHeight() + windowHeightOffset); // the offsets compensate for the operating-system dependent border size of the dialog
        setTitle(dialogTitle);
        setLocationRelativeTo(null);
        setResizable(false); // dialog window is not resizable

        // Dispose/Remove object once it has been closed.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Calling thread will only continue after this Dialog has been closed.
        setModal(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                JFrame taskbarFrame = (JFrame) getParent();
                taskbarFrame.setVisible(false);
                taskbarFrame.dispose();
            }
        });
    }

    /** @{@link JDialog JDialogs} do support modal displaying, but don't have a taskbar entry.
     * @{@link JFrame JFrames} do not support modal displaying, but do have a taskbar entry.
     * ==> Using a @{@link JFrame} which hosts the @{@link JDialog} in order to have both.*/
    private static JFrame createTaskbarFrame(String title) {
        frame = new JFrame(title);
       frame.setUndecorated(true);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
        return frame;
    }


    public static Window[] openWindows = GuiContainer.getWindows();

    public static void show(String dialogTitle, GameEnvironment drawingEnvironment) {
        try {
            GuiContainer guiContainer = new GuiContainer(dialogTitle, drawingEnvironment);
            guiContainer.setVisible(true);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
