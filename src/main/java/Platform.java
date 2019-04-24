import javax.swing.*;
import java.awt.*;

public class Platform {

    private int x;
    private int y;
    private int platformWeight = 300;
    private int platformHeight = 35;
    private int dy = 2;
    private Platform[] platforms;

    public int getPlatformX() {
        return x;
    }

    public int getPlatformY() {
        return y;
    }

    public int getPlatformHeight() {
        return platformHeight;
    }

    public int getPlatformWeight() {
        return platformWeight;
    }

    public Platform(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Platform() {}


    public Platform[] Platforms() {
        int a = 4;
        platforms = new Platform[4];
        for (int i = 0; i < platforms.length; i++) {
          //int x = (int) (Math.random() * (Panel.wight / 4 * 3 - Panel.wight / 4)) + Panel.wight / 4;
            int x = (int) (Math.random() * (Panel.wight - platformWeight));
            int y = (Panel.height / 5) * a;
            platforms[i] = new Platform(x, y);
            a--;
        }
        return platforms;
    }

    public void collision(Player player) {
        double plX = player.getPlayerX();
        double plY = player.getPlayerY();
        double plH = player.getPlayerHeight();
        double plW = player.getPlayerWidth();
        if  (plY + plH > y && plY + plH < y + platformHeight && plX > x && plX < platformWeight + x) {
            double newDy = player.getGameDY();
            player.setPlayerY(y - plH);
            player.setDy(newDy);
        }
    }



    public void update(Player player) {
        y += dy;
        collision(player);
        if (y + platformHeight > Panel.height) {
               x = (int) (Math.random() * (Panel.wight / 4 * 3 - Panel.wight / 4)) + Panel.wight / 4;
                //platformY = (int) (Math.random() * (Panel.wight - platformWeight));
                y = 0 ;
            }
        }



    public void draw(Graphics2D g) {
        Image img = new ImageIcon("platform.png").getImage();
        //Image img1 = img.getScaledInstance(platformWeight, platformHeight, Image.SCALE_SMOOTH);
        //Image img2 = new ImageIcon(img1).getImage();

        g.drawImage(img, x, y, platformWeight, platformHeight,  null);

    }
}
