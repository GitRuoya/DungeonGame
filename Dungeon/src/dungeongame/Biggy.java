package dungeongame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Biggy extends Enemy
{
	public Biggy(World world, int x, int y, int width, int length, int angle, int health)
	{
		super(world, x, y, width, length, angle, health);
		move = true;
		speed = 5;
		maxHealth = 20;
	}
	
	public void move(){
		super.move();
	}
	public void paintCharacter (Graphics2D g){
		g.setColor(Color.MAGENTA);
		super.paintCharacter(g);
	}
}
