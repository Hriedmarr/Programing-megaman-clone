package megaclone;

import java.util.Scanner;

public class Player extends Entity{
	private Input i; 
	public Player(SpriteSheet spriteDB, Input i){
		super(spriteDB);
		this.setI(i); 
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
	
	public void spriteChange()
	{
		Scanner scan = new Scanner(currentSpriteKey);
		//should avoid flying exceptions.
		scan.useDelimiter(":");
		 int s = scan.nextInt();
		 int d = scan.nextInt();;
		 
		 //translation from input
		 if(moveX != 0)
		 {
			 s = 1;
			 if(moveX < 0)
			 {
				 d = 3;
			 }
			 if(moveX > 0)
			 {
				 d = 5;
			 }
		 }
		 if(moveY != 0)
		 {
			 s = 2;
		 }
		 
		 String key = "" + s + ":" + d;
		 if(!key.equals(currentSpriteKey))
		 {
			 setCurrentSprite(key);
		 }
	}


	public Input getI() {
		return i;
	}


	public void setI(Input i) {
		this.i = i;
	}
	
}
