package megaclone;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.TreeMap;

public abstract class Entity extends Collideable{
	protected SpriteSheet spriteDB;
	private Sprite currentSprite;
	protected String currentSpriteKey;
	protected long timeLastUpdated;
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
		setCurrentSprite("0:5");

		
	}
	
	public void update(GameFrame g, Room room)
	{
		//Collision
		room.collisions(this);
		
		spriteChange();
		currentSprite.update(g, x, y);
		timeLastUpdated = System.currentTimeMillis();
		move();
	}
	
	/*
	 * Method to allow for spritechanging to occur in the update of this class AFTER collision.
	 */
	public abstract void spriteChange();
	
	public void collision(Collideable c)
	{
		int moveXH = moveX;
		int moveYH = moveY;
		
		if(boundingBoxCollision(c))
		{
			//Make sure all functions called result in changing the movex and movey
			if(c instanceof Tile)
			{
				resolveTileCollision((Tile)c, moveXH, moveYH);
			}
		}
	}
	
	public void resolveTileCollision(Tile c, int moveXH, int moveYH)
	{
		if(c.getSolid() == null)
		{
			return;
		}
		if(moveYH + y < c.getY() && c.getSolid()[0]) // Down
		{
			moveYH = c.getY() - y - h;
		}
		if(moveYH + y > c.getY() + c.getH() && c.getSolid()[2]) // Up
		{
			moveYH = c.getY() - y + c.getH();
		}
		if(moveXH + x > c.getX() && c.getSolid()[3]) // Right
		{
			moveXH = c.getX() - x - w;
		}
		if(moveXH + x < c.getX() + c.getW() && c.getSolid()[1]) // Left
		{
			moveXH = c.getX() - x + c.getW();
		}
		
		
		moveX = moveXH;
		moveY = moveYH;
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
