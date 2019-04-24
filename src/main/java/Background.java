import javax.swing.*;
import java.awt.*;

public class Background {
    Image img = new ImageIcon("back.png").getImage();

    public void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, Panel.wight, Panel.height, null);
    }

}
