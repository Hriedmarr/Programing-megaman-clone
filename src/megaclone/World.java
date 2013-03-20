package megaclone;

public class World {

	private GameFrame frame;
	private Room room;
	private Player p;
	
	public World(GameFrame frame)
	{
		this.frame = frame;
		//For now, load with debugmode
		debugMode();
		//connect Input.
		connectInput(p);
		//put player in debugroom
		room.setPlayer(p);
	}
	
	public void update(GameFrame g)
	{
		room.update(g);
	}
	
	public void connectInput(Player p)
	{
		frame.addKeyListener(p.getI().getHandler());
	}
	
	public void debugMode()
	{
		p = ResourceLoader.loadPlayer("Geminga", "Resources/CharDB.xml");
		room = ResourceLoader.loadRoom("Resources/Areas/Debug.xml", "Resources/Tilesets/tourianTest.xml");
	}
}
