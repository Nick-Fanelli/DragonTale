package gfx;

import entity.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion {
	
	private final int x;
	private final int y;

	private final int width;
	private final int height;

	private int xMap;
	private int yMap;

	private final Animation animation;

	private BufferedImage[] sprites;
	
	private boolean shouldRemove;
	
	public Explosion(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		width = 30;
		height = 30;
		
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/explosion.gif"
				)
			);
			
			sprites = new BufferedImage[6];

			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(70);
		
	}
	
	public void update() {
		animation.update();
		if(animation.hasPlayedOnce()) {
			shouldRemove = true;
		}
	}
	
	public boolean shouldRemove() { return shouldRemove; }
	
	public void setMapPosition(int x, int y) {
		xMap = x;
		yMap = y;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(
			animation.getImage(),
			x + xMap - width / 2,
			y + yMap - height / 2,
			null
		);
	}
	
}

















