package uk.co.willhobson.hobicons.sprites;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**Sprite super class, for game objects*/
public class Sprite
{
	protected Image image;
	protected double posX;
	protected double posY;
	protected double velX;
	protected double velY;
	protected double width; 
	protected double height;
	
	public Sprite()
	{
		posX = 0;
		posY = 0;
		velX = 0;
		velY = 0;
	}
	
	/** Sets the image for the sprite to use from an image object
	 * 
	 * @param i The Image object to use, width and height are obtained from this.
	 */
	public void setImage(Image i)
	{
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}
	
	/** Sets the image for the sprite to use from the steam emoticon database,
	 * for example to get :nepnep: you would pass "nepnep".
	 * @param emoticon Emoticon to use, you should totally pass nepnep to this.
	 */
	public void setImage(String emoticon)
	{
		String url = "https://steamcommunity-a.akamaihd.net/economy/emoticon/" + emoticon;
		Image i = new Image(url);
		width = i.getWidth();
		height = i.getHeight();
		this.setImage( i );
	}
	
	/** Sets the image for the sprite to use from the steam emoticon database,
	 * for example to get :nepnep: you would pass "nepnep".
	 * This one lets you manually set the size to scale them to however big you want. go ham.
	 * 
	 * @param emoticon Emoticon to use, you should totally pass nepnep to this.
	 * @param w Width to scale to 
	 * @param h Height to scale to
	 * @param aspect Obey aspect ratio or not? If true image will be largest possible size in box specified. 
	 */
	public void setImage(String emoticon, int w, int h, boolean aspect)
	{
		String url = "https://steamcommunity-a.akamaihd.net/economy/emoticon/" + emoticon;
		Image i = new Image(url,w,h, false, true);
		width = w;
		height = h;
		this.setImage( i );
	}
	
	/** setPos Set the position of your sprite.
	 * 
	 * @param x x pos.
	 * @param y y pos.
	 */
	public void setPos(double x, double y)
	{
		posX = x;
		posY = y;
	}
	
	/** setVel Set the velocity of your sprite.
	 * 
	 * @param x Velocity in the x direction
	 * @param y Velocity in the y direction
	 */
	public void setVel(double x, double y)
	{
		velX = x;
		velY = y;
	}
	
	public void addVel(double x, double y)
	{
		velX += x;
		velY += y;
	}
	
	public void update(double time)
	{
		posX += velX * time;
		posY += velY * time;
	}
	
	public void render(GraphicsContext gc)
	{
		gc.drawImage( image, posX, posY );
	}
	
	public Rectangle2D getBoundary()
	{
		return new Rectangle2D(posX,posY,width,height);
	}
	
	public boolean intersects(Sprite s)
	{
		return s.getBoundary().intersects( this.getBoundary() );
	}
	
	public String toString()
	{
		return " Position: [" + posX + "," + posY + "]" 
				+ " Velocity: [" + velX + "," + velY + "]";
	}
}

























