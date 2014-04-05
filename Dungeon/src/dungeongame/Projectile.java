package dungeongame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Projectile
{
	
	protected boolean move = true;
	protected int x;
	protected int y;
	protected int width;
	protected int length;
	protected int angle;
	protected int speed;
	protected Color color;
	protected boolean isEvil;
	private Rectangle2D.Double body = new Rectangle2D.Double();
	
	public Projectile (int x,int y,int width,int length,int speed,int angle, boolean isEvil){
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		this.speed = speed;
		this.angle = angle;
		this.isEvil = isEvil;
		body.width = width;
		body.height = length;
	}
	
	public void paintProjectile(Graphics2D g){
		g.setColor(color);
		body.x = x;
		body.y = y;
		g.fill(body);
	}
	
	public void move(){
		if (move)
		{
			x += speed * Math.cos(Math.toRadians(angle));
			y += speed * Math.sin(Math.toRadians(angle));
		}
	}
	
	public Rectangle2D.Double getBody()
	{
		return body;
	}
	
}
