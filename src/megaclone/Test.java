package megaclone;

import java.util.Scanner;

/**
 * Class to test functions of other classes.
 * @author Sirius
 *
 */
public class Test {

	/**
	 * Testing interface.
	 */
	public static void testing()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 1, 2, or 3.");
		if(scan.hasNextInt())
		{
			switch(scan.nextInt())
			{
			case 1:
				Test.debugTileSet();
				break;
			case 2:
				Test.showSprites();
				break;
			case 3:
				Test.invalidRoom();
				break;

			}

		}
		System.out.println("Press any key to close.");
		scan.next();
		System.exit(0);

	}
	/**
	 * Tests the tileset. Checks to see that the tileset is loading properly and that the tiles can be called
	 * and displayed correctly. Displays all tiles in a tileset to the screen.
	 */
	public static void debugTileSet()
	{
		//Setup
		GameFrame g = new GameFrame(640, 480, "TEST");
		int y = 0;
		int x = 0;
		int spacing = 24;
		TileSet ts = ResourceLoader.loadTileSet("Resources/Tilesets/tourianTest.xml");

		try
		{
			for(int i = 0; ts.getTileByID(i) != null; i++)
			{
				TileData holdTile = ts.getTileByID(i);
				//System.out.print("[" + i + "]");
				String hold = "[" + i + "]";
				if(i/17 == y + 1)
				{
					System.out.println();
					y++;
					x = 0;
				}
				if(holdTile != null)
					holdTile.update(g, x * spacing, y * spacing);
				g.getBBGraphics2D().drawChars(hold.toCharArray(), 0, hold.length(), x * spacing, y * spacing + spacing/2);
				x++;
				//hold.add(holdTile);
			}
			g.update();
			System.out.println();
			Scanner scan = new Scanner(System.in);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/** 
	 * Tests the spriteSheet. Shows all sprites in a spritesheet. Checks if it will throw exceptions.
	 * Sprites should display normally.*/
	public static void showSprites()
	{
		GameFrame g = new GameFrame(640, 480, "TEST");
		Player p = ResourceLoader.loadPlayer("Geminga", "Resources/CharDB.xml");

		int x = 0;
		int y = 0;
		try
		{
			for(String s : p.spriteDB.getSprites())
			{
				p.spriteDB.getSprite(s).update(g, x, y);
				x += 30;
				y += 30;
			}
			g.update();
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Tests the roomloader's reaction to invalid input. Should throw a NullPointerException.
	 */
	public static void invalidRoom()
	{
		try
		{
			//valid file, invalid structure.
			Room r = ResourceLoader.loadRoom("Resources/Areas/debug-singleroom.xml", "Resources/Tilesets/tourianTest.xml");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
