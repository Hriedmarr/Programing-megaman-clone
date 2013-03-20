package megaclone;
/**
 * @author Kyle Picinich
 * @version 0.5
 * @since 2013 - 03 - 09
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * This <code>Sprite</code> class represents a single animation and all frames of said animation.
 * @author Sirius
 * @version 0.87
 */
public class Sprite {
	public static enum RepeatMode {single, loop, pingpong};
	
	/**Width of the sprite*/
	private Integer w;
	/**Height of the sprite*/
	private Integer h;
	/**end-animation behavior of the {@code Sprite}*/
	private RepeatMode repMode;
	/**image sequence to cycle through.*/
	private BufferedImage[] frames;
	/**Animation speed in ms.*/
	private int animSpeed;
	/**Current frame the sprite is representing*/
	private int currentFrame;
	/**Whether the sprite is cycling forward or backward.*/
	private boolean inReverse;
	/**time the sprite was last updated.*/
	private long timeLastUpdated;
	
	/**
	 * Constructor, initializes a newly created {@code Sprite} with parameters width, height, frames, repMode, and
	 * animSpeed.
	 * @param width (Width of the sprite.)
	 * @param height (Height of the sprite.)
	 * @param frames (The sprite's animation frames.)
	 * @param repMode (an enum representing the end-animation behavior of a sprite.)
	 * @param animSpeed (The speed that a sprite changes animation frames in ms.)
	 */
	public Sprite(int width, int height, BufferedImage[] frames, RepeatMode repMode, int animSpeed)
	{
		w = new Integer(width);
		h = new Integer(height);
		this.frames = frames;
		this.repMode = repMode;
		this.animSpeed = animSpeed;
		currentFrame = 0;
		
	}

	/**
	 * Updates the sprite's animation and draws the sprite to the screen.
	 * @param g (GameFrame which is the graphical representation of the program. Access getBBGraphics2D to get its backbuffer.
	 * @param x (x location where the sprite will be drawn on the screen)
	 * @param y (y location where the sprite will be drawn on the screen)
	 */
	public void update(GameFrame g, double x, double y)
	{
		if(System.currentTimeMillis() - timeLastUpdated >= animSpeed)
		{
			advanceFrame();
			timeLastUpdated = System.currentTimeMillis();
		}
		g.getBBGraphics2D().drawImage(frames[currentFrame], ((int)x), ((int)y), g);
	}
	
	/**
	 * Advances the currentFrame of the sprite in accordance to the repMode defined in the constructor.
	 */
	void advanceFrame()
	{
		if(repMode == RepeatMode.single)
		{
			if(!inReverse)
			{
				if(currentFrame + 1 == frames.length)
				{
				}
				else
				{
					currentFrame++;
				}
			}
			else
			{
				if(currentFrame - 1 < 0)
				{
					
				}
				else
				{
					currentFrame--;
				}
			}
		}
		else if(repMode == RepeatMode.loop)
		{
			if(!inReverse)
			{
				if(currentFrame + 1 == frames.length)
				{
					currentFrame = 0;
				}
				else
				{
					currentFrame++;
				}
			}
			else
			{
				if(currentFrame - 1 < 0)
				{
					currentFrame = frames.length - 1;
				}
				else
				{
					currentFrame--;
				}
			}
		}
		else if(repMode == RepeatMode.pingpong)
		{
			if(!inReverse)
			{
				if(currentFrame + 1 == frames.length)
				{
					inReverse = true;
					currentFrame--;
				}
				else
				{
					currentFrame++;
				}
			}
			else
			{
				if(currentFrame - 1 < 0)
				{
					inReverse = false;
					currentFrame++;
				}
				else
				{
					currentFrame--;
				}
			}
		}
	}
	
	/**
	 * Getter of sprite width.
	 * @return w (Integer representing width.)
	 */
	public Integer getW() {
		return w;
	}

	/**
	 * Setter of sprite width.
	 * @param w (Integer representing width.)
	 */
	public void setW(Integer w) {
		this.w = w;
	}

	/**
	 * Getter of sprite height.
	 * @return h (Integer representing height.)
	 */
	public Integer getH() {
		return h;
	}

	/**
	 * Setter of sprite height.
	 * @param h (Integer representing height.)
	 */
	public void setH(Integer h) {
		this.h = h;
	}

	/**
	 * Gets the current frame of the sprite.
	 * @return the currentFrame of the sprite.
	 */
	public int getCurrentFrame() {
		return currentFrame;
	}

	/**
	 * Sets the currentFrame of the sprite.
	 * @param currentFrame (int representing the current frame of the sprite)
	 */
	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	/**
	 * Returns the number of frames in a sprite.
	 * @return length of animation.
	 */
	public int getAnimationLength()
	{
		return frames.length;
	}

	/**
	 * Returns true if the sprite is in reverse, false if not.
	 * @return inReverse
	 */
	public boolean isInReverse() {
		return inReverse;
	}

	/**
	 * Sets whether the sprite is to be in reverse or not
	 * @param inReverse (boolean)
	 */
	public void setInReverse(boolean inReverse) {
		this.inReverse = inReverse;
	}
}
