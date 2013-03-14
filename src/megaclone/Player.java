package megaclone;

public class Player extends Entity{
	private  Input i; 
	public Player(SpriteSheet spriteDB, Input i){
		super(spriteDB);
		this.i =i; 
	}
	
	
	public void update(GameFrame g, long timeLastUpdated)
	{
		
		
		
		super.update(g, timeLastUpdated);
	}
	
	/*
	 * Decide how the movements from input will change the sprite of the player.
	 * 	For example:
	 * 		an moveX of +2 will result in a sprite of 1:5
	 * 			setCurrentSprite("1:5");
	 * 
	 * 	procedure
	 * 		check if moveX != 0
	 * 					
	 * 
	 */
	
	public void translateInput()
	{
		 int s;
		 int d;
		 
		 //translation from input
		 
		 String key = "" + s + ":" + d;
		 if(!key.equals(currentSpriteKey))
		 {
			 setCurrentSprite(key);
		 }
	}
	
}
