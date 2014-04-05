package dungeongame;
//Copyright 2014 Invisible Cheese Inc.
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Start implements ActionListener
{
	private JFrame frame = new JFrame("Dungeon Game");
	private World world = new World();
	private Draw paint = new Draw(world);
	private Timer paintTimer = new Timer(33, this);
	private Timer gameTimer = new Timer(33, this);
	
	public static void main(String[] args)
	{
		Start start = new Start();
	}

	public Start()
	{
		JOptionPane.showMessageDialog(null, "Use WASD to move and the spacebar to shoot! Defeat all of the enemies to pass the level.");
		frame.setSize(1280, 960);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(paint);
		paintTimer.start();
		gameTimer.start();
		frame.setVisible(true);
		frame.addKeyListener(paint);
		playSound();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(paintTimer))
		{
			paint.repaint();
		}
		if (e.getSource().equals(gameTimer))
		{
			world.run();
		}
	}
	
	public void playSound()
	{
		// System.out.println(getClass());
		AudioClip music = JApplet.newAudioClip(getClass().getResource("export.wav"));
		music.loop();
	}
}
