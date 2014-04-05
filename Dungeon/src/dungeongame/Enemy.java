package dungeongame;

import java.awt.Color;
import java.awt.Graphics;



public class Enemy extends Character
{

	
	
	public Enemy(World world, int x, int y, int width, int length, int angle, int health)
	{
		super(world, x, y, width, length, angle, health);
		move = true;
		
	}

	public void move()
	{
		
			angle = (int) (Math.toDegrees(Math.atan2(getPlayerY() - y,getPlayerX() - x)));
			//paint.shoot(this);
			
		super.move();
	}
	
	
}
