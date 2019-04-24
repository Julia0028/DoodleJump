import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements ActionListener {

    public static int wight = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private Graphics2D g;
    private BufferedImage images;
    private Player player = new Player();
    private Background back = new Background();
    private Platform[] platforms = new Platform().Platforms();





    public Panel() {
        super();
        setFocusable(true);
        requestFocus();
        images = new BufferedImage(wight, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) images.getGraphics();
        Timer mainTimer = new Timer(1, this);
        mainTimer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });
    }


    public void actionPerformed(ActionEvent e) {
        gameRender();
        gameDraw();
        gameUpdate();


    }


    public void gameRender() {
        back.draw(g);
        player.draw(g);
        for (int i = 0; i < platforms.length; i++) {
            platforms[i].draw(g);
        }

    }

    public void gameUpdate() {
        player.move(this);
        for (int i = 0; i < platforms.length; i++) {
            platforms[i].update(player);
        }



    }

    public void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(images, 0, 0, null);
        g2.dispose();




    }

}
