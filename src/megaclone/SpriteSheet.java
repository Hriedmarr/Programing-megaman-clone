package megaclone;

import java.util.TreeMap;

public class SpriteSheet {
	private TreeMap<String, Sprite> spriteDB;
	
	public SpriteSheet()
	{
		spriteDB = new TreeMap<String, Sprite>();
	}
	
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
	
	public void setDefault(Sprite sprite)
	{
		spriteDB.remove("default");
		spriteDB.put("default", sprite);
	}
	
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
	
}
