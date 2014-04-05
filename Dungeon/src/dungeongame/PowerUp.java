package dungeongame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class PowerUp
{
	public static final int NO_TYPE = 0;
	public static final int RAPIDFIRE_TYPE = 1;
	public static final int HEAL_TYPE = 2;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int type;
	protected int color;
	private Ellipse2D.Double body = new Ellipse2D.Double();
	public PowerUp(int x, int y, int width, int height, int type){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = 10;
		this.type = type;
	}
	public void paintPowerUp(Graphics2D g){
		if (this.type == RAPIDFIRE_TYPE){		
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.CYAN);
		}
		body.x = x;
		body.y = y;
		body.width = width;
		body.height = height;
		g.fill(body);
		
	}

}
