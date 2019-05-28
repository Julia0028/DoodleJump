import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {

    public static int wight = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    private Graphics2D g;
    private BufferedImage images;
    private Player player;
    private Background back;
    private Platform[] platforms;
    enum STATES{MENUE, PLAY, GAMEOVER}
    static STATES states = STATES.MENUE;
    private Menue menue;
    private GameOver gameOver;
    private int score;


    public Panel() {
        super();
        images = new BufferedImage(wight, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) images.getGraphics();
        back = new Background();
        menue = new Menue();
        gameOver = new GameOver();
        start();

        setFocusable(true);
        requestFocus();
        Timer mainTimer = new Timer(1, this);

        mainTimer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (states.equals(STATES.PLAY)) player.keyPressed(e);
                if (states.equals(STATES.GAMEOVER)) gameOver.keyPressed(e);
                menue.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) { if (states.equals(STATES.PLAY)) player.keyReleased(e); }
        });
    }

    public void start() {
        score = 0;
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        platforms = new Platform[6];
        int a = 6;
        for (int i = 0; i < platforms.length; i++) {
            platforms[i] = new Platform();
            platforms[i].setY(platforms[i].getPlatformY() / 6 *a);
            a--;
        }
        player = new Player(platforms[3].getPlatformX() + Platform.platformWeight / 2,
                platforms[3].getPlatformY() - 100);

    }


    public void actionPerformed(ActionEvent e) {
        if (states == STATES.PLAY) {
            gameRender();
            gameDraw();
            gameUpdate();
        }
        if (states == STATES.MENUE) {
            back.draw(g);
            try {
                menue.draw(g, this);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            gameDraw();
        }
        if (states == STATES.GAMEOVER) {
            back.draw(g);
            try {
                gameOver.draw(g);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            gameDraw();
            if (gameOver.isPlayAgain()) {
                start();

            }


        }

    }


    public void gameRender() {
        back.draw(g);
        for (int i = 0; i < platforms.length; i++) platforms[i].draw(g);
        for (int i = 0; i < enemies.size(); i++) enemies.get(i).draw(g);
        for (int i = 0; i < bullets.size(); i++) bullets.get(i).draw(g);
        player.draw(g);
        String s = Integer.toString(score);
        Font font = new Font("Courier New", 10, 120);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(s, wight - 400 + 2, 100 + 2);
        g.setColor(new Color(255, 172, 172));
        g.drawString(s, wight - 400, 100);
        }


    public void gameUpdate() {
        score++;
        fileScore();
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
            if (isCollision || isFall) {
                score += 100;
                enemies.remove(i);
                i--;
            }
        }

        for (int i = 0; i < platforms.length; i++) {
            platforms[i].update();
            platforms[i].playerJump(player);
        }
        if (player.isGameOver()) {
            states = STATES.GAMEOVER;

        }
    }

    public void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(images, 0, 0, null);
        g2.dispose();




    }

    public void fileScore() {
        File file = new File("ImagePlay/score");
        try (FileWriter writer = new FileWriter(file, false)) {
            String s = Integer.toString(score);
            writer.write(s);
            writer.flush();

           } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
