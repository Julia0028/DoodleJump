import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private Window(String s) {
        super(s);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(0, 0);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen);
        //setUndecorated(true);
        setContentPane(new Panel());
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Window("Game"));

    }
}
