package megaclone;

import java.util.ArrayList;
import java.util.TreeMap;
/**
 * @author Kyle Picinich
 * @version 0.5
 * @since 2013 - 03 - 09
 */
public class SpriteSheet {
	private TreeMap<String, Sprite> spriteDB;
	/**
	 * Creates a new TreeMap of strings and sprite classes
	 */
	public SpriteSheet()
	{
		spriteDB = new TreeMap<String, Sprite>();
	}
	/**
	 * adds sprites to the sprite database depending on if the database is empty or
	 * if the database has a key made
	 * */
	public void addSprite(String key, Sprite sprite)
	{
		if(spriteDB.isEmpty())
		{
			spriteDB.put("default", sprite);
		}
		if(!spriteDB.containsKey(key))
		{
			spriteDB.put(key, sprite);
		}
	}
	/**
	 * removes the sprite that was in the position, and inserts another one 
	 * into the same position
	 */
	public void setDefault(Sprite sprite)
	{
		spriteDB.remove("default");
		spriteDB.put("default", sprite);
	}
	/**
	 * gets the default sprite or the sprite with a corresponding key.
	 */
	public Sprite getSprite(String key)
	{
		if(spriteDB.containsKey(key))
		{
			return spriteDB.get(key);
		}
		else
		{
			return spriteDB.get("default");
		}
	}
	
	public ArrayList<String> getSprites()
	{
		ArrayList<String> ret = new ArrayList<String>();
		
		for(String s : spriteDB.navigableKeySet())
		{
			ret.add(s);
		}
		
		return ret;
	}
}
