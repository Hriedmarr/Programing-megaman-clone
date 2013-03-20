package megaclone;

public class Tile extends Collideable {
	private TileData tileData;
	
	public Tile(int x, int y, TileData tileData)
	{
		super(x, y, 16, 16);
		this.tileData = tileData;
	}
	
	public Tile(int x, int y)
	{
		super(x, y, 16, 16);
		this.tileData = null;
	}
	
	public void update(GameFrame g)
	{
		if(tileData != null)
		{
			tileData.update(g, x, y);
		}
	}
	
	public boolean[] getSolid()
	{
		try
		{
			return tileData.getIsSolid();
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
