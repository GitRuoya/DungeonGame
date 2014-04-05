package dungeongame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Player extends Character
{

	private boolean keyPressedW;
	private boolean keyPressedS;
	private boolean keyPressedA;
	private boolean keyPressedD;
	private int shootAngle;
	public boolean cheatKeyPressed;

	
	public Player(World world, int x, int y, int width, int length, int angle, int health)
	{
		super(world, x, y, width, length, angle, health);
		speed = 10;
		maxHealth = 50;
		cheatKeyPressed = false;
	}

	public void move()
	{
		if (keyPressedW)
		{
			angle = 270;
		}
		if (keyPressedS)
		{
			angle = 90;
		}
		if (keyPressedA)
		{
			angle = 180;
		}
		if (keyPressedD)
		{
			angle = 0;
		} if (keyPressedW && keyPressedA)
		{
			angle = 225;
		} if (keyPressedW && keyPressedD)
		{
			angle = 315;
		} if (keyPressedS && keyPressedD)
		{
			angle = 45;
		} if (keyPressedS && keyPressedA)
		{
			angle = 135;
		}

		if (powerUp == PowerUp.RAPIDFIRE_TYPE){
			if (powerUpLast % 3 == 0){			
				world.shoot(world.getCharacters().get(0), shootAngle);
			}
			powerUpLast--;
			if (powerUpLast < 1){
				powerUp = PowerUp.NO_TYPE;
			}
		}
		if (powerUp == PowerUp.HEAL_TYPE){
			this.health = 50;
			powerUp = PowerUp.NO_TYPE;
		}
		super.move();
		
		System.out.println(powerUp + "");
	}
	
	public void paintCharacter (Graphics2D g){
		shootAngle+=5;
		if (shootAngle == 360){
			shootAngle = 0;
		}
		g.setColor(Color.BLUE);
		
		AffineTransform at = g.getTransform();
		
		g.rotate(Math.toRadians(shootAngle), x + 25, y + 25);

		g.fillRect(x + 25, y + 20, 40, 10);
		
		g.setTransform(at);

		super.paintCharacter(g);
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyChar() == 'w')
		{
			keyPressedW = true;
			move = true;
		}
		if (e.getKeyChar() == 's')
		{
			keyPressedS = true;
			move = true;
		}
		if (e.getKeyChar() == 'a')
		{
			keyPressedA = true;
			move = true;
		}
		if (e.getKeyChar() == 'd')
		{
			keyPressedD = true;
			move = true;
		}
		if (e.getKeyChar() == 'p')
		{
			cheatKeyPressed = true;
		}
/*		if (e.getKeyChar() == ' '){
			shootAngle+=30;
			if (shootAngle == 360){
				shootAngle = 0;
			}
		}*/
	}



	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyChar() == 'w')
		{
			keyPressedW = false;

		}
		if (e.getKeyChar() == 's')
		{
			keyPressedS = false;

		}
		if (e.getKeyChar() == 'a')
		{
			keyPressedA = false;

		}
		if (e.getKeyChar() == 'd')
		{
			keyPressedD = false;

		}
		if (!keyPressedW && !keyPressedS && !keyPressedA && !keyPressedD){
			move = false;
		}
		if (e.getKeyChar() == ' ' && powerUp == PowerUp.NO_TYPE){
			world.shoot(world.getCharacters().get(0), shootAngle);
		}

	}

	public int thePowerUp(){
		return this.powerUp;
	}
}

