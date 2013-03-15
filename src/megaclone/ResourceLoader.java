package megaclone;
import javax.imageio.ImageIO;
import javax.xml.parsers.*;
import javax.xml.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import org.w3c.dom.*;
public class ResourceLoader {
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
	public static SpriteSheet loadSpriteSheet(Element sheetTemplate) {
		//Load data from sheet template
		String ssName = sheetTemplate.getAttribute("name");
		String ssSizeT = sheetTemplate.getAttribute("sizeT");
		String ssSizeST = sheetTemplate.getAttribute("sizeST");
		String ssSrc = sheetTemplate.getAttribute("src");
		Document d = loadDoc(ssSrc);
		NodeList nodes = d.getElementsByTagName("sprite");
		//Set up the spritesheet
		NodeList sNodeList = sheetTemplate.getChildNodes();
		//Make the spritesheet here!
		SpriteSheet ret = new SpriteSheet();
		for(int i = 0; i < sNodeList.getLength(); i++) {
			Element sNode = (Element)sNodeList.item(i);
			String sName = sNode.getAttribute("name");
			String type = sNode.getAttribute("type");
			String subType = sNode.getAttribute("subType");
			for(int j = 0; j < nodes.getLength(); j++) {
				Element node = (Element)nodes.item(j);
				if(sName.equals(node)) {
					ret.addSprite(type+":"+subType, loadSprite(node));
					//load and add to the sprite sheet
					//key is type + ":" + subType
				}	
			}
		}
		return ret;
	}
	public static Sprite loadSprite(Element spriteElem) {
		NodeList nodec = spriteElem.getChildNodes();
		Element imageNode = (Element)nodec.item(0);
		String imageFile = imageNode.getAttribute("file");
		Element node = (Element)imageNode.getChildNodes().item(0);
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
		
		Scanner scan2 = new Scanner(sizet);
		scan2.useDelimiter(",\\s*");
		if(scan2.hasNextInt())
			size[0] = scan.nextInt();
		if(scan2.hasNextInt())
			size[1] = scan.nextInt();
		
		Scanner scan3 = new Scanner(arrayt);
		scan3.useDelimiter(",\\s*");
		if(scan3.hasNextInt())
			array[0] = scan.nextInt();
		if(scan3.hasNextInt())
			array[1] = scan.nextInt();
		
		Scanner scan4 = new Scanner(spacingt);
		scan4.useDelimiter(",\\s*");
		if(scan4.hasNextInt())
			spacing[0] = scan.nextInt();
		if(scan4.hasNextInt())
			spacing[1] = scan.nextInt();
		
		Element animNode = (Element)nodec.item(1);		
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
	/*
	public static void loadTileSheet(String doc) {
		Document d = loadDoc(doc);
		NodeList nodes = d.getElementsByTagName("sprite");
		for(int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element)nodes.item(i);
			System.out.println(node.getAttribute("name"));
		}
	}
	public static void loadTile(Element spriteElem) {
		NodeList nodec = spriteElem.getChildNodes();
		Element imageNode = (Element)nodec.item(0);
		String tileScale = imageNode.getAttribute("scale");
	}
	*/
	
	/*
	public static Enemy loadEnemy(Element enemyElem) {
		Enemy e;
		
	}
	*/
	
	public static Player loadPlayer(Element playerElem) {
		Player p;
		NodeList nodeList = playerElem.getElementsByTagName("SpriteSheet");
		
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
