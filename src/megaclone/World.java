package megaclone;

public class World {

	private GameFrame frame;
	
	
	//Temporary
	private Player p;
	
	public World(GameFrame frame)
	{
		this.frame = frame;
	}
	
	public void init()
	{
		
	}
	
	
	
	
	
	public void update()
	{
		
	}
	
	public void connectInput(Player p)
	{
		frame.addKeyListener(p.getI().getHandler());
	}
	
	public void debugMode()
	{
		p = ResourceLoader.loadPlayer("Geminga", "Resources/");
	}
}
