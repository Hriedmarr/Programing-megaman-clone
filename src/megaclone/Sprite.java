package megaclone;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Sprite {
	public static enum RepeatMode {single, loop, pingpong};
	
	private Integer w;
	private Integer h;
	private RepeatMode repMode;
	private BufferedImage[] frames;
	private int animSpeed;
	private int currentFrame;
	private boolean inReverse;
	
	//Temporary constructor.
	public Sprite(int width, int height, BufferedImage[] frames, RepeatMode repMode, int animSpeed)
	{
		w = new Integer(width);
		h = new Integer(height);
		this.frames = frames;
		this.repMode = repMode;
		this.animSpeed = animSpeed;
		currentFrame = 0;
	}

	public void update(GameFrame g, double x, double y, long timeLastUpdated)
	{
		if(System.currentTimeMillis() - timeLastUpdated >= animSpeed)
		{
			advanceFrame();
			//Trying new thing with passing the long in order to consolidate sprite object size.
		}
		g.getBBGraphics2D().drawImage(frames[currentFrame], ((int)x), ((int)y), g);
	}
	
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
	
	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	public int getAnimationLength()
	{
		return frames.length;
	}

	public boolean isInReverse() {
		return inReverse;
	}

	public void setInReverse(boolean inReverse) {
		this.inReverse = inReverse;
	}
}
