package megaclone;

import javax.imageio.ImageIO;
import javax.xml.parsers.*;
import javax.xml.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.TreeMap;

import org.w3c.dom.*;
/**
 * @author Jake Eason
 * @author Kyle Picinich
 * @version 0.5
 * @since 2013 - 03 - 09
 */
public class ResourceLoader {
	/**
	 * Initializes a file's name with the String parameter, then parses the file.
	 * 
	 * The DocumentBuilder variable is part of a process to parse the XML document 
	 * with a DOM parser. The variable db is used to parse the created File into a 
	 * Document. From there the Document variable is accessed to obtain its element, 
	 * then normalized.
	 * @param doc is used to access specific data files that other methods will pass in.
	 * @return d is a successfully parsed XML file
	 */
	public static Document loadDoc(String doc) {
		File resource = new File(doc);
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document d = db.parse(resource);
			d.getDocumentElement().normalize();
			return d;
		}catch(Exception ex) {ex.printStackTrace();}
		return null;
	}	

	/**
	 * Loads a XML file with multiple sprites for use in a SpriteSheet class.
	 * 
	 * Each XML file has attributes which are needed to access specific values of 
	 * the XML file's data, so we go through a NodeList of each little Element of the XML
	 * and check to see if the current node's position is what we are looking for.
	 * @param sheetTemplate is an XML Element that has various attributes
	 * @return ret is a SpriteSheet class which uses the XML's attributes to add a new sprite
	 */
	public static SpriteSheet loadSpriteSheet(Element sheetTemplate) {
		//Load data from sheet template
		String ssName = sheetTemplate.getAttribute("name");
		String ssSizeT = sheetTemplate.getAttribute("sizeT");
		String ssSizeST = sheetTemplate.getAttribute("sizeST");
		String ssSrc = sheetTemplate.getAttribute("src");
		Document d = loadDoc(ssSrc);
		NodeList nodes = d.getElementsByTagName("sprite");
		//Set up the spritesheet
		NodeList sNodeList = sheetTemplate.getElementsByTagName("sprite");
		//Make the spritesheet here!
		SpriteSheet ret = new SpriteSheet();
		for(int i = 0; i < sNodeList.getLength(); i++) {
			Element sNode = (Element)sNodeList.item(i);
			String sName = sNode.getAttribute("name");
			String type = sNode.getAttribute("type");
			String subType = sNode.getAttribute("subType");
			for(int j = 0; j < nodes.getLength(); j++) {
				Element node = (Element)nodes.item(j);
				if(sName.equals(node.getAttribute("name"))) {
					ret.addSprite(type+":"+subType, loadSprite(node));
					//load and add to the sprite sheet
					//key is type + ":" + subType
				}	
			}
		}
		return ret;
	}
	/**
	 * Load sprite data to return to s Sprite class
	 * 
	 * Uses NodeLists and Elements of the XML file to store various attributes.
	 * Each NodeList is an array list of the different Elements in an XML file.
	 * Once the Element has reached the desired location, the XML's attributes
	 * are stored into Strings, which are then parsed with Scanner and with 
	 * Scanner delimiters to ignore the ", " between each piece of data into 
	 * their respective arrays.
	 * <p>
	 * The next Element in the XML is not a child node, so we get the next item in 
	 * the Node List, which holds the sprite's animation mode, we then store that data
	 * depending on its RepeatMode attribute.
	 * We then create a new File from the earlier attribute imageFile, which we use
	 * to create buffered images of each sprite, with all data retrieved,
	 * we return a new Sprite instance with the image's size, the buffered image, and the
	 * RepeatMode with its speed.
	 * 
	 * @param spriteElem XML Element holding ChildNodes whose attributes are needed.
	 * @return new Sprite instance, where the XML file data is entered
	 */
	public static Sprite loadSprite(Element spriteElem) {
		NodeList nodec = spriteElem.getElementsByTagName("image");
		Element imageNode = (Element)nodec.item(0);
		String imageFile = imageNode.getAttribute("file");
		Element node = (Element)imageNode.getElementsByTagName("grid").item(0);
		String post = node.getAttribute("pos");
		String sizet = node.getAttribute("size");
		String arrayt = node.getAttribute("array");
		String spacingt = node.getAttribute("spacing");

		int[] pos = new int[2];
		int[] size = new int[2];
		int[] array = new int[2];
		int[] spacing = new int[2];

		Scanner scan = new Scanner(post);
		scan.useDelimiter(",\\s*");
		if(scan.hasNextInt())
			pos[0] = scan.nextInt();
		if(scan.hasNextInt())
			pos[1] = scan.nextInt();
		scan = new Scanner(sizet);
		scan.useDelimiter(",\\s*");
		if(scan.hasNextInt())
			size[0] = scan.nextInt();
		if(scan.hasNextInt())
			size[1] = scan.nextInt();
		scan = new Scanner(arrayt);
		scan.useDelimiter(",\\s*");
		if(scan.hasNextInt())
			array[0] = scan.nextInt();
		if(scan.hasNextInt())
			array[1] = scan.nextInt();
		scan = new Scanner(spacingt);
		scan.useDelimiter(",\\s*");
		if(scan.hasNextInt())
			spacing[0] = scan.nextInt();
		if(scan.hasNextInt())
			spacing[1] = scan.nextInt();
		Element animNode = (Element)spriteElem.getElementsByTagName("animation").item(0);		
		Sprite.RepeatMode repMode;
		if(animNode.getAttribute("repMode").equals("loop")) {
			repMode = Sprite.RepeatMode.loop;
		}
		else if(animNode.getAttribute("repMode").equals("pingpong")) {
			repMode = Sprite.RepeatMode.pingpong;
		}
		else {
			repMode = Sprite.RepeatMode.single;
		}
		int speed = Integer.parseInt(animNode.getAttribute("speed"));
		File imgFile = new File(imageFile);
		BufferedImage frames[] = new BufferedImage[array[0]*array[1]];
		BufferedImage source;
		try {
			source = ImageIO.read(imgFile);
			for(int j = 0; j < array[1]; j++) {
				for(int i = 0; i < array[0]; i++) {
					frames[i + (j * array[0])] = source.getSubimage(pos[0] + (i * (size[0] + spacing[0])), pos[1] + (j * (size[1] + spacing[1])), size[0], size[1]);
				}
			}
			return new Sprite(size[0], size[1], frames, repMode, speed);

		}
		catch(java.io.IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static TileSet loadTileSet(String doc) {
		Document solids = loadDoc(doc);
		String hold = ((Element)solids.getElementsByTagName("tileset").item(0)).getAttribute("src");
		Document d = loadDoc(hold);
		NodeList nodes = d.getElementsByTagName("tileset");
			Element node = (Element)nodes.item(0);
			String imageFile = ((Element)node.getElementsByTagName("image").item(0)).getAttribute("file");
	        Element sheetNode = ((Element)((Element)node.getElementsByTagName("image").item(0)).getElementsByTagName("grid").item(0));
	        String posS = sheetNode.getAttribute("pos");
	        String sizeS = sheetNode.getAttribute("size");
	        String arrayS = sheetNode.getAttribute("array");
	        String spacingS = sheetNode.getAttribute("spacing");
	        Scanner scan;

	        scan = new Scanner(posS).useDelimiter(",\\s*");
	        
	        int[] pos = new int[2];
	        int j = 0;
	        while(scan.hasNext())
	        {
	            if(scan.hasNextInt())
	            {
	                pos[j] = scan.nextInt();
	                j++;
	            }
	            else
	            {
	                scan.next();
	            }
	        }

			//System.out.println(pos[0] + " " + pos[1]);

	        scan = new Scanner(sizeS).useDelimiter(",\\s*");
	        int[] size = new int[2];
	        j = 0;
	        while(scan.hasNext())
	        {
	            if(scan.hasNextInt())
	            {
	                size[j] = scan.nextInt();
	                j++;
	            }
	            else
	            {
	                scan.next();
	            }
	        }

			//System.out.println(size[0] + " " + size[1]);

	        scan = new Scanner(arrayS).useDelimiter(",\\s*");;
	        int[] array = new int[2];
	        j = 0;
	        while(scan.hasNext())
	        {
	            if(scan.hasNextInt())
	            {
	                array[j] = scan.nextInt();
	                j++;
	            }
	            else
	            {
	                scan.next();
	            }
	        }

			//System.out.println(array[0] + " " + array[1]);

	        scan = new Scanner(spacingS).useDelimiter(",\\s*");;
	        int[] spacing = new int[2];
	        j = 0;
	        while(scan.hasNext())
	        {
	            if(scan.hasNextInt())
	            {
	                spacing[j] = scan.nextInt();
	                j++;
	            }
	            else
	            {
	                scan.next();
	            }
	        }

			//System.out.println(spacing[0] + " " + spacing[1]);

	        //Loading Image
	        //System.out.println(imageFile);
	        File imgFile = new File(imageFile);
	        //frames = new BufferedImage[array[0]*array[1]];
	        BufferedImage source;
	        TreeMap<Integer, TileData> tileDB = new TreeMap<Integer, TileData>();
	        try
	        {
	        	source = ImageIO.read(imgFile);
	        	//Cutting...
	        	//Either hold on to this for later or just make it simpler
				//System.out.println("This worked, I think" + array[1] + " " + array[0]);
	        	int count = 0;
	        	for(int k = 0; k < array[1]; k++)
	        	{
					//System.out.println("This worked, I think");
	        	    for(int l = 0; l < array[0]; l++)
	        	    {
	        	        //frames[i + (j * array[0])] = source.getSubimage(pos[0] + (i * (size[0] + spacing[0])), pos[1] + (j * (size[1] + spacing[1])), size[0], size[1]);

	        	    	//hopefully this works...
	        	    	TileData t = new TileData(source.getSubimage(pos[0] + (l * (size[0] + spacing[0])), pos[1] + (k * (size[1] + spacing[1])), size[0], size[1]), tileDB.size());
	        	    	if(t != null)
	        	    	{
	        	    		tileDB.put(new Integer(count), t);
	        	    		count++;
	        	    	}
	        	    	//System.out.println("This worked, I think");
	        	    }
	        	}
	        }
	        catch(java.io.IOException e)
	        {
	        	e.printStackTrace();
	        	return null;
	        }

	        NodeList tileSolids = ((Element)solids.getElementsByTagName("tileset").item(0)).getElementsByTagName("tile");
	        for(int i = 0; i < tileSolids.getLength(); i++)
	        {
	        	Element holdSolid = (Element)tileSolids.item(i);
	        	boolean[] solidSides = new boolean[4];
	        	String solidString = holdSolid.getAttribute("solid");
	        	scan = new Scanner(solidString).useDelimiter(",\\s*");
		        for(int k = 0; k < 4; k++)
		        {
		        	solidSides[j] = scan.nextInt() == 1;
		        }
		        int index = Integer.parseInt(holdSolid.getAttribute("index"));
		        if(tileDB.get(index) != null)
		        	tileDB.get(index).setIsSolid(solidSides);
		        else
		        {
		        	System.out.println(index);
		        }
	        }

	        
	        TileSet ret = new TileSet(tileDB);
	        
		
		return ret;
	}
	
	public static Room loadRoom(String doc, String tileSetLoc)
	{
		TileSet tileSet = ResourceLoader.loadTileSet(tileSetLoc);
		
		Document d = loadDoc(doc);
		
		Element layerElem = (Element)d.getElementsByTagName("layer").item(0);
		
		Tile[][] roomContents = new Tile[Integer.parseInt(layerElem.getAttribute("width"))][Integer.parseInt(layerElem.getAttribute("height"))];
		
		NodeList x = layerElem.getElementsByTagName("data");
		Element dataElemT = (Element)layerElem.getElementsByTagName("data").item(0);
		
		//System.out.println(roomContents.length);
		//System.out.println(roomContents[0].length);
		
		int width = Integer.parseInt(layerElem.getAttribute("width"));
		int height = Integer.parseInt(layerElem.getAttribute("height"));
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				roomContents[i][j] = new Tile(i * 16, j * 16);
			}
		}
		Tile[] holdArray = new Tile[width];
		int holdAInd = 0;
		int lineCheck = 0;
		NodeList holdTileData = dataElemT.getElementsByTagName("tile");
		int chk2 = holdTileData.getLength();
		for(int i = 0; i < holdTileData.getLength(); i++)
		{
			String hold = ((Element)holdTileData.item(i)).getAttribute("gid");
			int holdID = Integer.parseInt( hold );
			if(holdAInd < holdArray.length - 1)
			{
				holdArray[holdAInd] = new Tile(lineCheck * 16, holdAInd * 16, tileSet.getTileByID(holdID));
				holdAInd++;
			}
			else if(holdAInd == holdArray.length - 1)
			{
				holdArray[holdAInd] = new Tile(lineCheck * 16, holdAInd * 16, tileSet.getTileByID(holdID));
				holdAInd = 0;

				//This had better work
				
				roomContents[lineCheck] = holdArray;
				holdArray = new Tile[height];
				lineCheck++;
			}
		}
		Room ret = new Room(width, height, roomContents, tileSet);
		return ret;
	}
	/**
	 * Stores the attributes of an XML file which will be used for the Entity class
	 * 
	 * When the attribute data is parsed, checks if the data was one value or another,
	 * and depending upon that data, calls another method.
	 * @param entityElem XML Element holding attribute data.
	 * @return ret an Entity instance where the XML data will be used.
	 */
	/*
	public static Enemy loadEnemy(Element enemyElem) {
		Enemy e;
		
	}
	*/
	public static Player loadPlayer(Element playerElem) {
		Player p = null;
		NodeList nodeList = playerElem.getElementsByTagName("spritesheet");
		SpriteSheet hold = loadSpriteSheet((Element)nodeList.item(0));
		p = new Player(hold);
		return p;
	}

	/*
	public static Entity[] loadEntities(String document)
	{
		//JakeistoImplement~!
	}
	*/

	public static Player loadPlayer(String name, String document)
	{
		Document d = loadDoc(document);
		//Character might change.
		NodeList entities = d.getElementsByTagName("Character");
		Player p = null;
		for(int i = 0; i < entities.getLength(); i++)
		{
			if(((Element) entities.item(i)).getAttribute("name").equals(name))
			{
				if( ((Element)entities.item(i)).getAttribute("entityType").equals("Player"))
				{
					p = loadPlayer(((Element)entities.item(i)));
				}
			}
		}
		return p;
	}
}
