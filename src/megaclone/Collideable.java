package megaclone;

public class Collideable {
	protected int x;
	protected int y;
	protected Integer w;
	protected Integer h;
	
	protected int moveX;
	protected int moveY;
	
	public Collideable(int x, int y, Integer w, Integer h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean boundingBoxCollision(Collideable c)
	{
		return boundingBoxCollision(c.getX(), c.getY(), c.getW(), c.getH());
	}
	
	//Private, due to questions with getting boxes
	private boolean boundingBoxCollision(int b_x, int b_y, int b_w, int b_h)
	{
		if ((x > b_x + b_w - 1) || //Left?
				(y > b_y + b_h - 1) || // Under?
				(b_x > x + w - 1) || // Right?
				(b_y > y + h - 1))   // Above?
		{
			// no collision
			return false;
		}

		// collision
		return true;
	}
	
	public void move()
	{
		x += moveX;
		y += moveY;
	}
	
	//is there a global collision?

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public int getMoveX() {
		return moveX;
	}

	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}

	public int getMoveY() {
		return moveY;
	}

	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}
}
