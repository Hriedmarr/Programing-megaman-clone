package megaclone;

import org.w3c.dom.*;
/**
 * @author student
 */
public class Test {
	public static void main(String[] args) {
	/**
	 * This will run the sprite loader method in Resource Loader three times, 
	 * each grabbing a different sprite, and then spitting out the data
	 */
		ResourceLoader r = new ResourceLoader();
		Document d = r.loadDoc("resources/player.xml");
		/**
		 * Should print out 29 35 11 loop 250
		 */
		r.loadSprite((Element)d.getElementsByTagName("sprite").item(0));
		/**
		 * Should print out 39 37 9 loop 250
		 */
		r.loadSprite((Element)d.getElementsByTagName("sprite").item(1));
		/**
		 * Should print out 38 37 10 loop 250
		 */
		r.loadSprite((Element)d.getElementsByTagName("sprite").item(2));
	}
}
