package megaclone;

import java.util.Scanner;

/**
 * Class which contains all core game objects and the game loop.
 * @author Sirius
 * @version 0.87
 */
public class Game {

	/** The frequency the of the game's updates. */
	private int fps;
	/** Whether the game is actually running or not. */
	private boolean isRunning;
	/** The graphical representation of the game. */
	private GameFrame gFrame;
	/** the size of the screen that the game plays on.*/
	private int xS, yS;
	
	/**
	 * Initializes a new Game object which is running.
	 */
	public Game()
	{
		isRunning = true;
		fps = 30;
		xS = 600;
		yS = 800;
		gFrame = new GameFrame(640, 480, "MMClone(Temporary name)");
	}
	
	/**
	 * The game loop.
	 * Creates the world and runs the game itself.
	 */
	public void run()
	{
		World world = new World(gFrame);
		long time;
		while(isRunning)
		{
			time = System.currentTimeMillis();
			
			
			
			world.update(gFrame);
			
			//GameFrame updates
			gFrame.update();
			//delay
			
			time = (1000/fps) - (System.currentTimeMillis() - time);
			
			if(time > 0)
			{
				System.out.println("----------");
				try
				{
					Thread.sleep(time);
				}
				catch(Exception e){}
				
			}
		}
		
	}
	
	/**
	 * The main method. Creates the game and starts it.
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("Press enter to begin normally, \"T\" to test.");
		//Scanner scan = new Scanner(System.in);
		//if(scan.next().equals("T"))
		//	Test.testing();
		
		Game game = new Game();
		game.run();
	}
	
	
}
