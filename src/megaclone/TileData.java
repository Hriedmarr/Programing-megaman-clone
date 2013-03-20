package megaclone;

import java.awt.*;
import java.awt.image.*;
import java.awt.image.BufferedImage.*;
import org.w3c.dom.*;
import java.io.File;
import javax.imageio.*;
import java.util.Scanner;
import java.awt.geom.*;
import java.util.ArrayList;
/**
 * TileData contains all data that represents a particular tile containing an image and passable walls.
 * 
 * @author Sirius
 * @version 0.87
 */
public class TileData
{
	/** The graphical representation of the tile.*/
	private BufferedImage tile;
	/** Contains the booleans for each side of the tile, organized: U R D L*/
	private boolean[] isSolid;
	/** the tile's identification type.*/
	private int tileType;
	
	/**
	 * Initializes a TileData with a bufferedImage and an int value for tiletype
	 * @param tileImage ( BufferedImage representing a tile's graphical appearance.)
	 * @param _tileType (int representing a tile's type.)
	 */
	public TileData(BufferedImage tileImage, int _tileType)
	{
		tileType = _tileType;
		this.tile = tileImage;
		
		isSolid = new boolean[4];
		//Background will be black for alpha.
		for(int i = 0; i < getIsSolid().length; i++)
		{
			isSolid[i] = false;
		}
	}
	
	/**
	 * Update method, draws the tile to the GameFrame's backbuffer at x and y
	 * @param g (GameFrame, the backBuffer's Graphics2D is retrieved from this)
	 * @param x (int representing the tile's location)
	 * @param y (int representing the tile's location)
	 */
	public void update(GameFrame g, int x, int y)
	{
		g.getBBGraphics2D().drawImage(tile, x, y, g);
	}
	
	/** Getter for tileType
	 * @return tileType
	 */
	public int getTileType()
	{
		return tileType;
	}
	/**
	 * Getter for the passability of the tile.
	 * @return isSolid
	 */
	public boolean[] getIsSolid() {
		return isSolid;
	}
	/**
	 * Setter for the passability of the tile.
	 * @param isSolid (boolean array representing passability of the tile)
	 */
	public void setIsSolid(boolean[] isSolid) {
		this.isSolid = isSolid;
	}
}
