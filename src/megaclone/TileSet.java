package megaclone;

import java.awt.*;
import java.awt.image.*;
import java.awt.image.BufferedImage.*;
import org.w3c.dom.*;

import java.io.File;
import javax.imageio.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;

public class TileSet
{
	private TreeMap<Integer, TileData> tileDB;
	
	public TileSet(TreeMap<Integer, TileData> tileDB)
	{
		this.tileDB = tileDB;
	}
	
	public TileData getTileByID(int id)
	{
		return tileDB.get(id);
	}
	
	public void debugShowAllTilesInSet(GameFrame g)
	{
		//ArrayList<Tile> hold = new ArrayList<Tile>();
		int y = 0;
		int x = 0;
		int spacing = 24;
		System.out.println(tileDB.size());
		for(int i = 0; i < tileDB.size(); i++)
		{
			TileData holdTile = getTileByID(i);
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
	}
}
