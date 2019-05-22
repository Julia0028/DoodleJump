import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Platform {

    private int x;
    private int y;
    public static int platformWeight = 300;
    public static int platformHeight = 35;
    private int dy = 4;

    public int getPlatformX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPlatformY() {
        return y;
    }



    public Platform() {
        x = (int) (Math.random() * (Panel.wight - Platform.platformWeight));
        y = Panel.height;
        enemyOnPlatform();
    }

    public void enemyOnPlatform() {
        Random r = new Random();
        int rn = r.nextInt(5);
        if (y < Panel.height / 2 && rn == 1)
            Panel.enemies.add(new Enemy(x + platformWeight / 2 , y - 120 - 1));
    }



    public void playerJump(Player player) {
        int plX = player.getPlayerX();
        int plY = player.getPlayerY();
        int plH = player.getPlayerHeight();
        if (plY + plH > y && plY + plH < y + platformHeight) {
            if (plX > x && plX < x + platformWeight) {
                double newDy = player.getGameDY();
                player.setPlayerY(y - plH);
                player.setDy(newDy);
            }
        }
    }

    public void update() {
        y += dy;
        if (y + platformHeight > Panel.height) {
                x = (int) (Math.random() * (Panel.wight - platformWeight));
                y = 0 ;
                enemyOnPlatform();

            }
        }


    public void draw(Graphics2D g) {
        Image img = new ImageIcon("ImagePlay/platform.png").getImage();
        g.drawImage(img, x, y, platformWeight, platformHeight,  null);

    }
}
