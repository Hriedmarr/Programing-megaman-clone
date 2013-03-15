package megaclone;

/**
 * Class which contains all core game objects and the game loop.
 * @author Sirius
 *
 */
public class Game {

	/**
	 * Contains a GameWindow, World, and ResourceLoader
	 */
	private int fps;
	private boolean isRunning;
	private GameFrame gFrame;
	private int xS, yS;
	
	public Game()
	{
		isRunning = true;
		fps = 30;
		xS = 600;
		yS = 800;
		gFrame = new GameFrame(600, 800, "MMClone(Temporary name)");
	}
	
	public void run()
	{
		World world = new World(gFrame);
		long time;
		while(isRunning)
		{
			time = System.currentTimeMillis();
			
			
			
			
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
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	
}
