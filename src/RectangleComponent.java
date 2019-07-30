import javax.swing.*;
import java.awt.*;

public class RectangleComponent extends JComponent{
    private Color color;
    private boolean record = false;
    public RectangleComponent(int x,int y){
        super();
        color = Color.WHITE;// Default color
        setLocation(x+9, y+9);
        setSize(100,100 );
        setVisible(false);
    }

    public void closedoff(Color color){ //Make rectangle visible
        if(!record) {
            if (color == Color.BLUE) {
                this.color = new Color(51, 204, 255);
            } else if (color == Color.RED) {
                this.color = new Color(255, 51, 51);
            }
            setVisible(true);
            record = true;
        }
    }// Closedoff ends
    @Override
    protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(color);
         g.fillRect(5,5,95,95);
//         g.setColor(Color.WHITE);
//         g.fillRect(0,0,100,100);
    }// paintComponent ends
}
