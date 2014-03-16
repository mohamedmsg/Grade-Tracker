import java.awt.*;
import javax.swing.*;


public class Background extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535870036807392770L;
	private Image background;
	
	public Background(String filename){
		this.setPreferredSize(new Dimension(600, 500));
		this.setLayout(new BorderLayout());
		background = new ImageIcon(getClass().getResource(filename)).getImage();
	}
	
	@Override
    public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
	
}
