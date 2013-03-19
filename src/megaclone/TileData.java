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
public class TileData
{
	private BufferedImage tile;
	private boolean[] isSolid;
	private int tileType;
	// U R D L
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
	
	public void update(GameFrame g, int x, int y)
	{
		g.getBBGraphics2D().drawImage(tile, x, y, g);
	}
	
	public int getTileType()
	{
		return tileType;
	}
	public boolean[] getIsSolid() {
		return isSolid;
	}
	public void setIsSolid(boolean[] isSolid) {
		this.isSolid = isSolid;
	}
}
