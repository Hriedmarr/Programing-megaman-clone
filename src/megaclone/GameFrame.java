package megaclone;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
/**
 * Class extended from JFrame to allow for a reliably-timed double-buffered system displaying game objects
 * graphically.
 * @author Sirius
 * @version 0.87
 */
public class GameFrame extends JFrame {
	/*
	 * Create one backbuffer at first, but after collision methods are created, implement the multiple backbuffers.
	 */
	
	/** Width and height instance variables*/
	private int wWidth, wHeight;
	/** Insets of the jFrame, required to draw objects into the frame correctly. */
	private Insets insets;
	/** The backbuffer, which is drawn every frame to the graphics context of GameFrame.*/
	private BufferedImage backBuffer;
	
	
	/**
	 * Constructor for GameFrame, creates a newly initialized GameFrame with a title and width, height, and a title.
	 * @param _wWidth (Width of the game window.)
	 * @param _wHeight (Height of the game window.)
	 * @param title (String for the tile of the game window.)
	 */
	public GameFrame(int _wWidth, int _wHeight, String title)
	{
		//Try this for now, hope it doesn't explode.
		super(title);
		wWidth = _wWidth;
		wHeight = _wHeight;
		
		init();
	}
	
	/**
	 * Does further initialization of the GameFrame.
	 * Sets the size, and shows the frame.
	 * Also initializes the backBuffer.
	 */
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
	
	
	/**
	 * Update method, called by the gameloop of the Game class in order to draw the backbuffer to gameFrame's 
	 * graphics.
	 */
	public void update()
	{
		Graphics2D g = (Graphics2D)getGraphics();
		g.drawImage(backBuffer, insets.left, insets.top, this);
		((Graphics2D)backBuffer.getGraphics()).clearRect(0, 0, wWidth, wHeight);
	}
	
	/**
	 * Gets the backbuffer of GameFrame, called by all drawing classes' update methods.
	 * @return Graphics2D of the backBuffer.
	 */
	public Graphics2D getBBGraphics2D()
	{
		return (Graphics2D)backBuffer.getGraphics();
	}
	
}
