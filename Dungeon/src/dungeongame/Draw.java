package dungeongame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Draw extends Canvas implements KeyListener
{
//	Player player;// Enemy thing = new Enemy(60, 40, 20, 10);
	ArrayList<Character> characters;
	ArrayList<Projectile> projectiles;
	ArrayList<Rectangle> blocks;
	ArrayList<PowerUp> powerUps;
	Player player;
	//int numberOfRedies = 5;

	public Draw( World world)
	{
		characters = world.getCharacters();
		projectiles = world.getProjectiles();
		blocks = world.getBlocks();
		powerUps = world.getPowerUps();
		player = world.getPlayer();
//		player = new Player(this, 0, 0, 50, 50, 0, 50);
//		characters.add(player);
//		for (int i = 0; i < 5; i++)
//		{
//			characters.add(new Redy(this, i * 100, 400, 40, 40, i * 60, 5));
//			characters.add(new Pinky(this, i * 100, 600, 40, 40, i * 60, 5));
//			characters.add(new Orangy(this, i * 100, 800, 40, 40, i * 60, 5));
//			characters.add(new Biggy(this, i * 100, 1000, 60, 60, i * 60, 20));
//			blocks.add(new Rectangle(i *50 + 300, 750, 50, 50));
//			// projectiles.add(new Projectile(i*100, 800, 10, 40, 5, 270)); OH,
//			// and
//			// nyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyanyan!
//		}
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		for (int i = characters.size() - 1; i > -1; i--)
		{
			characters.get(i).paintCharacter(g2);
		}
		for (int i = projectiles.size() - 1; i > -1; i--)
		{
			projectiles.get(i).paintProjectile(g2);
		}
		for (int i = 0; i < blocks.size(); i++)
		{
			g2.draw(blocks.get(i));
		}
		for (int i = 0; i < powerUps.size(); i++)
		{
			powerUps.get(i).paintPowerUp(g2);
		}
	}


	/*
	 * public Image loadImage() { String a = "Super Mario.png";
	 * 
	 * File file = new File(a); Image image = ImageIO.read(file); }
	 */

//	public int getPlayerX()
//	{
//		return player.x;
//	}
//
//	public int getPlayerY()
//	{
//		return player.y;
//	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		((Player) characters.get(0)).keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		((Player) characters.get(0)).keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}
}
	