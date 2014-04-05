package dungeongame;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JApplet;

public class Character
{
	public static final int CHARACTER_AND_CHARACTER_COLLISION = 1;
	public static final int CHARACTER_AND_PROJECTILE_COLLISION = 2;
	public static final int CHARACTER_AND_BLOCK_COLLISION = 3;
	public static final int CHARACTER_AND_POWERUP_COLLISION = 4;
	protected boolean move = false;
	protected boolean move2 = false;
	protected int speed;
	protected int angle;
	protected int angle2;
	protected int x;
	protected int y;
	protected int xOther;
	protected int yOther;
	protected int lastX;
	protected int lastY;
	protected int width;
	protected int length;
	protected World world;
	protected int health;
	protected int shootingRate = 49;
	public int powerUp;
	protected int powerUpLast;
	private Ellipse2D.Double body = new Ellipse2D.Double();
	int maxHealth;

	public Character(World world, int x, int y, int width, int length, int angle, int health)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		this.angle = angle;
		this.world = world;
		this.health = health;
		body.width = width;
		body.height = length;
		lastX = x;
		lastY = y;
	}

	public void collided(int other, int collisionType)
	{

		this.move2 = true;
		if (collisionType == CHARACTER_AND_PROJECTILE_COLLISION)
		{
			playSound();
			this.xOther = world.getProjectiles().get(other).x;
			this.yOther = world.getProjectiles().get(other).y;
			if (this instanceof Player && this.world.getProjectiles().get(other).isEvil)
			{
				health--;
			} else if (this instanceof Enemy && !this.world.getProjectiles().get(other).isEvil)
			{
				health--;
			}
		} else if (collisionType == CHARACTER_AND_CHARACTER_COLLISION)
		{
			playSound();
			this.xOther = world.getCharacters().get(other).x;
			this.yOther = world.getCharacters().get(other).y;

			if (world.getCharacters().get(other).x == x && world.getCharacters().get(other).x == y)
			{
				// System.out.println("X = " + OtherX + " Y =  " +OtherY +
				// " objectX = " + x + " objectY =  " +y);

				angle2 = (int) (Math.random() * 360);
			} else
			{
				if (this instanceof Player && this.world.getCharacters().get(other) instanceof Enemy)
				{
					health --;
				}
				angle2 = 180 + (int) (Math.toDegrees(Math.atan2(world.getCharacters().get(other).y - y, world.getCharacters().get(other).x - x)));
			}
		} else if (collisionType == CHARACTER_AND_BLOCK_COLLISION)
		{
			this.x = this.lastX;
			this.y = this.lastY;
			/*
			 * this.xOther = world.getBlocks().get(other).x; this.yOther =
			 * world.getBlocks().get(other).y; angle2 = 180 + (int)
			 * (Math.toDegrees(Math.atan2(world.getBlocks().get(other).y - y,
			 * world.getBlocks().get(other).x - x)));
			 */
		} else if (collisionType == CHARACTER_AND_POWERUP_COLLISION){
				this.powerUp = world.getPowerUps().get(other).type;

		}

		// else

		// System.out.println(health + "");
	}

	public int getPlayerX()
	{
		return world.getCharacters().get(0).x;
	}

	public int getPlayerY()
	{
		return world.getCharacters().get(0).y;
	}

	public void move()
	{
		if (collidedWithOther())
		{
			move2 = true;
			if (xOther == x && yOther == y)
			{
				angle2 = (int) (Math.random() * 360);
			} else
			{
				angle2 = 180 + (int) (Math.toDegrees(Math.atan2(yOther - y, xOther - x)));
			}
		} else
		{
			move2 = false;
		}

		if (move)
		{
			lastX = x;
			lastY = y;
			x += speed * Math.cos(Math.toRadians(angle));
			y += speed * Math.sin(Math.toRadians(angle));
		}
		if (move2)
		{

			x += speed * Math.cos(Math.toRadians(angle2));
			y += speed * Math.sin(Math.toRadians(angle2));
		}
		if (this.x < 0)
		{
			this.x = 0;
		}
		if (this.y < 0)
		{
			this.y = 0;
		}
		if (this.x > 1230)
		{
			this.x = 1230;
		}
		if (this.y > 890)
		{
			this.y = 890;
		}
	}

	private boolean collidedWithOther()
	{
		return Math.sqrt((double) (Math.pow((x - xOther), 2) + Math.pow((y - yOther), 2))) < 40;
	}

	public void paintCharacter(Graphics2D g)
	{
		// body.setFrame(x,y,width,length);
		body.x = x;
		body.y = y;
		g.fill(body);
		g.setColor(Color.BLACK);
		g.fillRect(x, y - 20, width, 10);
		g.setColor(Color.BLUE);
		g.fillRect(x, y - 20, width * health / maxHealth, 10);
	}

	public void playSound()
	{
		// System.out.println(getClass());
		AudioClip scream = JApplet.newAudioClip(getClass().getResource("slap_2.aiff"));
		scream.play();
	}

	public Ellipse2D.Double getBody()
	{
		return body;
	}

	public void harmed()
	{

	}
}
