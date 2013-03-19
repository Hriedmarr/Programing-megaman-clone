package megaclone;

public class Room {
	private int width;
	private int height;
	private Player player;
	private Tile[][] roomContents;
	private TileSet tileSet;
	
	public Room(int width, int height, Tile[][] roomContents, TileSet tileSet)
	{
		this.width = width;
		this.height = height;
		this.roomContents = roomContents;
		this.tileSet = tileSet;
	}
	
	public void update(GameFrame g)
	{
		//tileSet.debugShowAllTilesInSet(g);
		for(int i = 0; i < roomContents.length; i++)
		{
			for(int j = 0; j < roomContents[i].length; j++)
			{
				roomContents[i][j].update(g);
			}
		}
		
		player.update(g, this);
	}
	
	public void collisions(Entity e)
	{
		
		//Tile collisions.
		for(int i = 0; i < roomContents.length; i++)
		{
			for(int j = 0; j < roomContents[i].length; j++)
			{
				e.collision(roomContents[i][j]);
			}
		}
		//Entity collisions.
		if(e == player)
		{
			//Enemy collisions go here.
		}
		
		/*Enemies' collisions will go here.
		 * 
		 */
	}
	
	public void setPlayer(Player p)
	{
		player = p;
	}
}
