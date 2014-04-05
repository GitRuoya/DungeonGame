package dungeongame;


	import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

	public class Orangy extends Enemy
	{
		public Orangy(World world, int x, int y, int width, int length, int angle, int health)
		{
			super(world, x, y, width, length, angle, health);
			move = true;
			speed = 5;
			maxHealth = 5;
		}
		
		public void move(){
			super.move();
			if (x < 50){
				x = 1180;
			} 
			if (y < 50){
				y = 840;
			}
			if (x > 1180){
				x = 50;
			} 
			if (y > 840){
				y = 50;
			} 
		}
		public void paintCharacter (Graphics2D g){
			g.setColor(Color.ORANGE);
			super.paintCharacter(g);
		}
	}


