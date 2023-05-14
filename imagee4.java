import java.awt.*;
import javax.swing.*;
public class imagee4 extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("Waterdeep_ The Riddle Murderer - A side-quest for Dragon Heist where players must stop a serial kssssiller.png");
        g2.drawImage(i, 0, 0, this);
    }

}
