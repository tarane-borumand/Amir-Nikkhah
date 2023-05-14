import java.awt.*;
import javax.swing.*;
public class imagee3 extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("streaming-sports-o745nline-1B.png");
        g2.drawImage(i, 0, 0, this);
    }
}