package dungeongame;

import java.applet.AudioClip;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class World
{
	Player player;// Enemy thing = new Enemy(60, 40, 20, 10);
	ArrayList<Character> characters = new ArrayList<Character>();
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
	ArrayList<Level> levels = new ArrayList<Level>();
	int levelIndex;
	String levelStuff;
	BufferedReader br;
	int powerUpTime;
	public World()
	{
		InputStream is = getClass().getResourceAsStream("levels.txt");
        br = new BufferedReader(new InputStreamReader(is));
        for (int i = 0; i<15; i++){
			try
			{
				levelStuff = br.readLine();
				levels.add(new Level( getNumbersInLevel(0), getNumbersInLevel(1), getNumbersInLevel(2), getNumbersInLevel(3), getNumbersInLevel(4)));
				System.out.println("LEVEL MADE!!");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //Care we do NOT!
			}
        	
		//FileInputStream fs = new FileInputStream("Levels.txt");
		//BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(fs)));
		//try
		//{
		//} catch (IOException e)
		//{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
//		levels.add(new Level(0, 2, 0, 0, 5));
//		levels.add(new Level(1, 0, 0, 0, 10));
//		levels.add(new Level(1, 0, 0, 50, 0));
		levelStart();
	}

	public void levelStart()
	{
	
		characters.clear();
		projectiles.clear();
		blocks.clear();
		powerUps.clear();
		player = new Player(this, 0, 0, 50, 50, 0, 50);
		characters.add(player);
		powerUpTime = 0;
		Level currentLevel;
		currentLevel = levels.get(levelIndex);
		for (int i = 0; i < currentLevel.getNumberOfThings(1); i++)
		{
			characters.add(new Redy(this, (int) (Math.random() * 980 + 300), (int) (Math.random() * 660 + 300), 40, 40, 0, 5));
		}
		for (int i = 0; i < currentLevel.getNumberOfThings(2); i++)
		{
			characters.add(new Pinky(this, (int) (Math.random() * 980 + 300), (int) (Math.random() * 660 + 300), 40, 40, 0, 5));
		}
		for (int i = 0; i < currentLevel.getNumberOfThings(3); i++)
		{
			characters.add(new Orangy(this, (int) (Math.random() * 980 + 300), (int) (Math.random() * 660 + 300), 40, 40, 0, 5));
		}
		for (int i = 0; i < currentLevel.getNumberOfThings(4); i++)
		{
			characters.add(new Biggy(this, (int) (Math.random() * 980 + 300), (int) (Math.random() * 660 + 300), 60, 60, 0, 20));
		}
		for (int i = 0; i < currentLevel.getNumberOfThings(5); i++)
		{
			blocks.add(new Rectangle((int) (Math.random() * 980 + 300), (int) (Math.random() * 660 + 300), 50, 50));
		}
		if (characters.size() == 1){
			characters.add(new Bossy(this, (int) (Math.random() * 980 + 300), (int) (Math.random() * 660 + 300), 100, 100, 0, 100));
		}
		if (levelIndex == 15){
			JOptionPane.showMessageDialog(null, "Success is yours!!!!!!!!");
			System.exit(0);
		}
		// characters.add(new Pinky(this, (int) (Math.random() * 1280), (int)
		// (Math.random() * 960), 40, 40, 0, 5));
		// characters.add(new Orangy(this, (int) (Math.random() * 1280), (int)
		// (Math.random() * 960), 40, 40, 0, 5));
		// characters.add(new Biggy(this, (int) (Math.random() * 1280), (int)
		// (Math.random() * 960), 60, 60, 0, 20));
		// blocks.add(new Rectangle((int) (Math.random() * 1280) + 300, (int)
		// (Math.random() * 960), 50, 50));
	}

	public void run()
	{
		projectileDie();
		characterDie();
		collision();
		for (int i = 0; i < characters.size(); i++)
		{
			characters.get(i).move();
		}
		for (int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).move();
		}
		if (!characters.contains(player))
		{
			JOptionPane.showMessageDialog(null, "GAME OVER. Please enter another coin to continue.");
			System.exit(0);
		}
		if (characters.size() == 1 || player.cheatKeyPressed)
		{
			levelIndex++;
			levelStart();
		}
		if (player.powerUp == 0){
			powerUpTime++;
			if (powerUpTime == player.health + 80){
				if ((int) (Math.random() * 2) == 1){				
					powerUps.add(new PowerUp( (int) Math.random() * 980 + 300, (int) Math.random() * 660 + 300, 100, 100, 1));
				} else {
					powerUps.add(new PowerUp( (int) Math.random() * 980 + 300, (int) Math.random() * 660 + 300, 100, 100, 2));
				}
			}
		}
	}

	
	public ArrayList<Character> getCharacters()
	{
		return characters;
	}

	public ArrayList<Rectangle> getBlocks()
	{
		return blocks;
	}

	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}

	public ArrayList<PowerUp> getPowerUps()
	{
		return powerUps;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void shoot(Character character, int angle)
	{
		projectiles.add(new Projectile(character.x + 20, character.y + 20, 10, 10, 15, angle, !(character instanceof Player)));
	}
	public void superShoot(Character character)
	{
		for (int i = 0; i<10; i++){
		projectiles.add(new Projectile(character.x + 50, character.y + 50, 10, 10, 5, i*36, !(character instanceof Player)));
		}
	}
	public void spawn(Character character)
	{
		characters.add(new Redy(this, character.x + 50, character.y + 50, 40, 40, 0, 5));
		if (character.health > 80){
			characters.add(new Orangy(this, character.x + 50, character.y + 50, 40, 40, 0, 5));
		}else if (character.health > 40){
		characters.add(new Pinky(this, character.x + 50, character.y + 50, 40, 40, 0, 5));
	  	}else{
	  		characters.add(new Biggy(this, character.x + 50, character.y + 50, 60, 60, 0, 20));
	  	}
}

	public void projectileDie()
	{
		for (int i = 0; i < projectiles.size(); i++)
		{
			if (projectiles.get(i).x < 0 || projectiles.get(i).y < 0 || projectiles.get(i).x > 1260 || projectiles.get(i).y > 920)
			{
				projectiles.remove(i);
			}
		}
	}

	public void collision()
	{
		for (int i = 0; i < characters.size(); i++)
		{
			for (int j = 0; j < characters.size(); j++)
			{
				if (i != j /* && (i != 0 && j != 0) */)
				{
					// System.out.println(i + "   " + j);
					// int difference = ((asd.get(i).width + asd.get(i).length)
					// / 2) + ((asd.get(j).width + asd.get(j).length) / 2);
					// if
					// (asd.get(i).getAsdf().intersects(asd.get(j).getAsdf().getFrame()))
					if (getDistanceOfCharacters(i, j) < 40)
					{
						characters.get(i).collided(j, Character.CHARACTER_AND_CHARACTER_COLLISION);
						// System.out.println(Math.sqrt((double)
						// (Math.pow((asd.get(j).x - asd.get(i).x), 2) +
						// Math.pow((asd.get(j).y - asd.get(i).y), 2))));
					} else
					{
						characters.get(i).move2 = false;
					}
				}
			}
		}
		for (int i = 0; i < characters.size(); i++)
		{
			for (int j = 0; j < blocks.size(); j++)
			{
				if (characters.get(i).getBody().intersects(blocks.get(j)))
				{
					characters.get(i).collided(j, Character.CHARACTER_AND_BLOCK_COLLISION);
				}
			}
		}
		for (int i = 0; i < characters.size(); i++)
		{
			for (int j = 0; j < projectiles.size(); j++)
			{
				if (getDistanceOfProjectile(i, j) < 100)
				{
					if (projectiles.get(j).isEvil != characters.get(i) instanceof Enemy)
						characters.get(i).collided(j, Character.CHARACTER_AND_PROJECTILE_COLLISION);
				}
			}
		}
			for (int j = 0; j < powerUps.size(); j++)
			{
				if (getDistanceOfPowerUp(j) < 75)
				{
					characters.get(0).powerUpLast = 200;
					characters.get(0).collided(j, Character.CHARACTER_AND_POWERUP_COLLISION);
					powerUps.clear();
				}
			}
		for (int i = 0; i < blocks.size(); i++)
		{
			for (int j = 0; j < projectiles.size(); j++)
			{
				if (projectiles.get(j).getBody().intersects(blocks.get(i)))
				{
					projectiles.remove(j);
				}
			}
		}
	}

	public void characterDie()
	{
		for (int i = 0; i < characters.size(); i++)
		{
			if (characters.get(i).health < 1)
			{
				characters.remove(i);
			}
		}
	}
	

	private double getDistanceOfCharacters(int i, int j)
	{
		return Math.sqrt((double) (Math.pow((characters.get(j).x - characters.get(i).x), 2) + Math.pow((characters.get(j).y - characters.get(i).y), 2)));
	}

	private double getDistanceOfProjectile(int i, int j)
	{
		return Math.sqrt((double) (Math.pow((projectiles.get(j).x - characters.get(i).x), 2) + Math.pow((projectiles.get(j).y - characters.get(i).y), 2)));
	}
	private double getDistanceOfPowerUp(int j)
	{
		return Math.sqrt((double) (Math.pow((powerUps.get(j).x - characters.get(0).x), 2) + Math.pow((powerUps.get(j).y - characters.get(0).y), 2)));
	}
	private int getNumbersInLevel(int thingSearched){

			
        	return Integer.parseInt("" + levelStuff.charAt(thingSearched * 2));
	
	}
}