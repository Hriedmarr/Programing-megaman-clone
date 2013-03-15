package megaclone;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class Input {

	public class InputHandler implements KeyListener
	{
		private Player p;
		private final Set<Integer> pressed = new HashSet<Integer>();
		
		public InputHandler(Player p)
		{
			this.p = p;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			//I sense a disturbance in the force. Not sure how to deal with it.
			int key = e.getKeyCode();
			pressed.add(e.getKeyCode());
			if(pressed.size() >= 1) {
				for(Integer k : pressed)
				{
					if(k == KeyEvent.VK_LEFT){
						if(Math.abs(p.getMoveX()) <= 2)
						{
							p.setMoveX(p.getMoveX() - 2);
						}
					}
					if(k == KeyEvent.VK_RIGHT){
						if(Math.abs(p.getMoveX()) <= 2)
						{
							p.setMoveX(p.getMoveX() + 2);
						}
					}
					if(k == KeyEvent.VK_SPACE){
					// later
					}
					if(k == KeyEvent.VK_1){
					//ugh
					}
				}
			}
			//Checks for opposing inputs
			if(key == KeyEvent.VK_LEFT && pressed.contains(KeyEvent.VK_RIGHT)){
				if(Math.abs(p.getMoveX()) <= 2)
				{
					p.setMoveX(p.getMoveX() - 2);
				}
			}
			if(key == KeyEvent.VK_RIGHT && pressed.contains(KeyEvent.VK_LEFT)){
				if(Math.abs(p.getMoveX()) <= 2)
				{
					p.setMoveX(p.getMoveX() - 2);
				}
			}
		}
			
		@Override
		public void keyReleased(KeyEvent e) {
			pressed.remove(e.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
	}
	
	private InputHandler handler;
	
	public Input(Player p)
	{
		handler = new InputHandler(p);
	}
	
	public InputHandler getHandler()
	{
		return handler;
	}
}
