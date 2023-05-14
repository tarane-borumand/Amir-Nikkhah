import java.awt.*;
import javax.swing.*;
public class imagee5 extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("downdsadsadlssoad.png");
        g2.drawImage(i, 0, 0, this);
    }
}
