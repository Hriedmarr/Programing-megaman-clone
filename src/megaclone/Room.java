package megaclone;
/**
 * Room class which represents each room. 
 * Contains a player and enemies, and the tiles as well.
 * 
 * @author Sirius
 * @version 0.87
 */
public class Room {
	/** Width of the room in tiles */
	private int width;
	/** Height of the room in tiles */
	private int height;
	/** Player in the room */
	private Player player;
	/** Tile array holding the room's Tiles. */
	private Tile[][] roomContents;
	/** tileset represented by the room's tiles.*/
	private TileSet tileSet;
	
	/**
	 * Initializes a new room with width, height, tiles and a tileset
	 * @param width (Width of the room in tiles)
	 * @param height (Height of the room in tiles)
	 * @param roomContents (Tile content of the room.)
	 * @param tileSet (Tileset used in the room.)
	 */
	public Room(int width, int height, Tile[][] roomContents, TileSet tileSet)
	{
		this.width = width;
		this.height = height;
		this.roomContents = roomContents;
		this.tileSet = tileSet;
	}
	
	/**
	 * Update method for room, which calls its tiles to write to the GameFrame's backbuffer.
	 * @param g (GameFrame, contains backbuffer.)
	 */
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
	
	/**
	 * Calls for collisions between an entity and tiles, and between an entity and other entities.
	 * @param e
	 */
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
	
	/**
	 * Passes a player object to the room.
	 * @param p (Player to be passed to the room)
	 */
	public void setPlayer(Player p)
	{
		player = p;
	}
}
