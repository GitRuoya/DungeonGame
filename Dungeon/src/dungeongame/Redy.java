package dungeongame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Redy extends Enemy 
{
	int i = 0;
	
	public Redy(World world, int x, int y, int width, int length, int angle, int health)
	{
		super(world, x, y, width, length, angle, health);
		move = true;
		speed = 3;
		maxHealth = 5;
	}
	
	public void move(){
		i++;
		super.move();
		if (i>shootingRate){
			world.shoot(this, angle);
		i = 0;
		}
	}
	public void paintCharacter (Graphics2D g){
		g.setColor(Color.RED);
		super.paintCharacter(g);
	}


	
}
