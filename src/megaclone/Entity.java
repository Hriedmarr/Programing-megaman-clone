package megaclone;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.TreeMap;

public class Entity extends Collideable{
	protected SpriteSheet spriteDB;
	private Sprite currentSprite;
	protected String currentSpriteKey;
	//Temporary constructor, may change to diff constructor.
	/*
	 *	requires location still	
	 *Passed everything, created by the resourceLoader
	 */
	public Entity(SpriteSheet spriteDB)
	{
		//still temporary.
		super(200, 200, 0, 0);
		this.spriteDB = spriteDB;
		setCurrentSprite(spriteDB.getSprite("default"));

		
	}
	
	public void update(GameFrame g, long timeLastUpdated)
	{
		/*
		 * okie, collision will go here laterz
		 */
		currentSprite.update(g, x, y, timeLastUpdated);
		move();
	}
	
	public void collision(Collideable c)
	{
		int moveXH = moveX;
		int moveYH = moveY;
		
		if(boundingBoxCollision(c))
		{
			if(moveXH + x > x) // Right
			{
				moveXH = c.getX() - x - w;
			}
			if(moveXH + x < x) // Left
			{
				moveXH = c.getX() - x + c.getW();
			}
			if(moveYH + y < y) // Down
			{
				moveYH = c.getY() - y - h;
			}
			if(moveYH + y > y) // Up
			{
				moveYH = c.getY() - y + c.getH();
			}
		}
	}
	
	public Sprite getCurrentSprite()
	{
		return currentSprite;
	}
	
	private void setCurrentSprite(Sprite sprite)
	{
		currentSprite = sprite;
		w = currentSprite.getW();
		h = currentSprite.getH();
	}
	
	public void setCurrentSprite(String key)
	{
		currentSpriteKey = key;
		setCurrentSprite(spriteDB.getSprite(key));
	}
}
