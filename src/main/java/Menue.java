import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Menue {
    private enum MENUE {UP, DOWN, NONE}
    private boolean play = true;
    private boolean exit = false;
    private int score1 = 0;

    private MENUE stateMenue = MENUE.NONE;

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_S) {
            stateMenue = MENUE.DOWN;
                exit = true;
                play = false;
            }
        if (key == KeyEvent.VK_W) {
            stateMenue = MENUE.UP;
            play = true;
            exit = false;
        }
        if (key == KeyEvent.VK_ENTER && play) Panel.states = Panel.STATES.PLAY;
        if (key == KeyEvent.VK_ENTER && exit) System.exit(0);

    }


    public void draw(Graphics2D g, Panel p) throws IOException {
        String res = Files.lines(Paths.get("ImagePlay/score"), StandardCharsets.UTF_8).findFirst().get();
        int score = Integer.parseInt(res);
        Font myFont = new Font("Courier New", 10, 120);
        g.setFont(myFont);
        g.setColor(Color.white);
        if (score > score1) {
           g.drawString("YOUR BEST SCORE: " + res, 200 + 2, 400 + 2);
            g.setColor(new Color(255, 172, 172));
           g.drawString("YOUR BEST SCORE: " + res, 200, 400);
           score1 = score;
       } else {
           String s = Integer.toString(score1);
           g.drawString("YOUR BEST SCORE: " + s, 200 + 2, 400 + 2);
           g.setColor(new Color(255, 172, 172));
           g.drawString("YOUR BEST SCORE: " + s, 200, 400);

       }
        g.setColor(Color.white);
        g.drawString("PLAY?", 680 + 2, 500 + 2);
        g.drawString("HELLO!", 670 + 2, 300 + 2);
        g.drawString("EXIT", 700 + 2, 600 + 2);
        g.setColor(new Color(255, 104, 104));
        g.drawString("PLAY?", 680, 500);
        g.setColor(new Color(255, 172, 172));
        g.drawString("HELLO!", 670, 300);
        g.drawString("EXIT", 700, 600);
        if (stateMenue.equals(MENUE.DOWN)) {
            g.setColor(new Color(255, 104, 104));
            g.drawString("EXIT", 700, 600);
            g.setColor(new Color(255, 172, 172));
            g.drawString("PLAY?", 680, 500);
        }
        if (stateMenue.equals(MENUE.UP)) {
            g.setColor(new Color(255, 104, 104));
            g.drawString("PLAY?", 680, 500);
            g.setColor(new Color(255, 172, 172));
            g.drawString("EXIT", 700, 600);
        }
    }
}

