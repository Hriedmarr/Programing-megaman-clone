package megaclone;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
/**
 * 
 * @author Sirius
 *
 */
public class GameFrame extends JFrame {
	/**
	 * Create one backbuffer at first, but after collision methods are created, implement the multiple backbuffers.
	 */
	
	private int wWidth, wHeight;
	private Insets insets;
	private BufferedImage backBuffer;
	
	
	
	public GameFrame(int _wWidth, int _wHeight, String title)
	{
		//Try this for now, hope it doesn't explode.
		super(title);
		wWidth = _wWidth;
		wHeight = _wHeight;
		
		init();
	}
	
	
	private void init()
	{
		setSize(wWidth, wHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		insets = getInsets();
		setSize(insets.left + wWidth + insets.right, insets.top + wHeight + insets.bottom);
		
		//This gets remodeled with multibbuffers.
		backBuffer = new BufferedImage(wWidth, wHeight, BufferedImage.TYPE_INT_ARGB);
		
	}
	
	
	/*
	 * both of these become depreciated with introduction of multiple bbuffers.
	 */
	public void update()
	{
		Graphics2D g = (Graphics2D)getGraphics();
		g.drawImage(backBuffer, insets.left, insets.top, this);
		((Graphics2D)backBuffer.getGraphics()).clearRect(0, 0, wWidth, wHeight);
	}
	public Graphics2D getBBGraphics2D()
	{
		return (Graphics2D)backBuffer.getGraphics();
	}
	
}
