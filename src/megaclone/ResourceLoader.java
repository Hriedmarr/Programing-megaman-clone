package megaclone;
import javax.imageio.ImageIO;
import javax.xml.parsers.*;
import javax.xml.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
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
	 * with a DOM parser. The variable dbis used to parse the created File into a 
	 * Document. From there the Document variable is accessed to obtain its element, 
	 * then normalized.
	 * @param doc is used to access specific data files that other methods will pass in.
	 * @return d is a successfully parsed XML file
	 */
	public Document loadDoc(String doc) {
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
	public SpriteSheet loadSpriteSheet(Element sheetTemplate) {
		String ssSrc = sheetTemplate.getAttribute("src");
		Document d = loadDoc(ssSrc);
		NodeList nodes = d.getElementsByTagName("sprite");
		NodeList sNodeList = sheetTemplate.getChildNodes();
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
	public Sprite loadSprite(Element spriteElem) {
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
			System.out.println(size[0] +" "+ size[1]+" "+ frames.length+" "+ repMode.toString()+" "+ speed);
			return new Sprite(size[0], size[1], frames, repMode, speed);
			
		}
		catch(java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Stores the attributes of an XML file which will be used for the Entity class
	 * 
	 * When the attribute data is parsed, checks if the data was one value or another,
	 * and depending upon that data, calls another method.
	 * @param entityElem XML Element holding attribute data.
	 * @return ret an Entity instance where the XML data will be used.
	 */
	public Entity loadEntity(Element entityElem) {
		Entity ret = null;
		NodeList nodec = entityElem.getChildNodes();
		Element charNode = (Element)nodec.item(0);
		String idAtt = charNode.getAttribute("id");
		String nameAtt = charNode.getAttribute("name");
		String entity = charNode.getAttribute("entityType");
		if(entity.equals("Player")) {
			System.out.println("ITS A PLAYER");
			ret = loadPlayer(nameAtt, idAtt);
		}
		if(entity.equals("Enemy")){System.out.println("ITS A PLAYER");}
		return ret;
		
	}
	public static Player loadPlayer(String name, String document) {
		Document d = loadDoc(document);
		NodeList entities = d.getElementsByTagName("Character");
		Player p = null;
		for(int i = 0; i < entities.getLength(); i++) {
			if(((Element) entities.item(i)).getAttribute("name").equals(name)) {
				if( ((Element)entities.item(i)).getAttribute("entityType").equals("Player"))
				{
					p = loadPlayer(((Element)entities.item(i)));
				}
			}
		}
		return p;
	}
}
