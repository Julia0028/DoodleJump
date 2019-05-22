import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {

    public static int wight = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    private Graphics2D g;
    private BufferedImage images;
    private Player player;
    private Background back;
    private Platform[] platforms;
    enum STATES{MENUE, PLAY}
    static STATES states = STATES.PLAY;




    public Panel() {
        super();
        images = new BufferedImage(wight, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) images.getGraphics();
        back = new Background();
        platforms = new Platform[6];
        int a = 6;
        for (int i = 0; i < platforms.length; i++) {
            platforms[i] = new Platform();
            platforms[i].setY(platforms[i].getPlatformY() / 6 *a);
            a--;
        }
        player = new Player(platforms[2].getPlatformX() + Platform.platformWeight / 2,
                platforms[2].getPlatformY() - 100);

        setFocusable(true);
        requestFocus();
        Timer mainTimer = new Timer(10, this);

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
        if (states == STATES.PLAY) {
            gameRender();
            gameDraw();
            gameUpdate();
        }
        if (states == STATES.MENUE) {}

    }


    public void gameRender() {
        back.draw(g);
        for (int i = 0; i < bullets.size(); i++) bullets.get(i).draw(g);
        for (int i = 0; i < platforms.length; i++) platforms[i].draw(g);
        for (int i = 0; i < enemies.size(); i++) enemies.get(i).draw(g);
        player.draw(g);
        }


    public void gameUpdate() {
        player.move(this);
        for (int i = 0; i < bullets.size(); i++) {
            boolean remove = bullets.get(i).update();
            if (remove) {
                bullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update();
            boolean isCollision = false;
            boolean isFall = enemy.isFall();
            boolean gameOver = player.deathFromEnemy(enemy);
            if (gameOver) player.setGameOver(true);
            for (int j = 0; j < bullets.size(); j++) {
                isCollision = enemy.deathEnemy(bullets.get(j));
                if (isCollision) break;
            }
            if (isCollision || isFall) enemies.remove(i);
        }

        for (int i = 0; i < platforms.length; i++) {
            platforms[i].update();
            platforms[i].playerJump(player);
        }
    }

    public void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(images, 0, 0, null);
        g2.dispose();




    }

}
