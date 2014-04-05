package dungeongame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Bossy extends Enemy
{
	int spawnI = 0;
	int i = 0;
	public Bossy(World world, int x, int y, int width, int length, int angle, int health)
	{
		super(world, x, y, width, length, angle, health);
		move = true;
		speed = 2;
		maxHealth = 100;
	}
	
	public void move(){
		spawnI++;
		i++;
		super.move();
		if (spawnI>shootingRate + health){
			world.spawn(this);
		spawnI = 0;
		}
		if (i>shootingRate + health ){
			world.superShoot(this);
		i = 0;
		}
	}
	public void paintCharacter (Graphics2D g){
		g.setColor(Color.RED);
		super.paintCharacter(g);
	}
}
