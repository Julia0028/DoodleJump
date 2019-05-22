import javax.swing.*;
import java.awt.*;

public class Background {
    Image img = new ImageIcon("ImagePlay/back.png").getImage();

    public void draw(Graphics2D g) {

        if (Panel.states.equals(Panel.STATES.PLAY)) g.drawImage(img, 0, 0, Panel.wight, Panel.height, null);
        if (Panel.states.equals(Panel.STATES.MENUE)) {
        }
    }
}
