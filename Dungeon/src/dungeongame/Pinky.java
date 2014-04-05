package dungeongame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pinky extends Enemy
{
	public Pinky(World world, int x, int y, int width, int length, int angle, int health)
	{
		super(world, x, y, width, length, angle, health);
		move = true;
		speed = 6;
		maxHealth = 5;
	}
	
	public void move(){
		super.move();
	}
	public void paintCharacter (Graphics2D g){
		g.setColor(Color.PINK);
		super.paintCharacter(g);
	}
}
