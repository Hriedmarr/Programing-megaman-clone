package megaclone;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class Input {
	/*
	 * This isn't done yet!!!
	 * 
	 */
	public class InputHandler implements KeyListener
	{
		//Handles directional commands
		private int xMove;
		//Handles other commands
		private boolean[] cmds;
		
		private final Set<Integer> pressed = new HashSet<Integer>();
		
		public InputHandler()
		{
			cmds = new boolean[2];
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
						if(xMove <= 4)
						{
							xMove = -4;
						}
					}
					if(k == KeyEvent.VK_RIGHT){
						if(xMove >= -4)
						{
							xMove = 4;
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
				if(xMove <= 4)
				{
					xMove = -4;
				}
			}
			if(key == KeyEvent.VK_RIGHT && pressed.contains(KeyEvent.VK_LEFT)){
				if(xMove >= -4)
				{
					xMove = 4;
				}
			}
			e.consume();
		}
			
		@Override
		public void keyReleased(KeyEvent e) {
			pressed.remove(e.getKeyCode());
			if(pressed.size() < 1)
			{
				xMove = 0;
			}
			e.consume();
		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
		public int getXMove()
		{
			return xMove;
		}
		
		public boolean[] getCmds()
		{
			return cmds;
		}
	}

	
	private InputHandler handler;
	
	private Player p;
	
	
	public void update()
	{
		p.setMoveX(handler.getXMove());
	}
	
	public Input(Player p)
	{
		handler = new InputHandler();
		this.p = p;
			
	}
	
	public InputHandler getHandler()
	{
		return handler;
	}
}
