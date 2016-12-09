package uk.co.willhobson.hobicons.sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.LinkedList;

import uk.co.willhobson.hobicons.Hobicons;
import uk.co.willhobson.hobicons.interfaces.Reaper;
import uk.co.willhobson.hoblib.Hob;
import uk.co.willhobson.hoblib.HobSwing;

/** Sprite super class, for game objects */
public class Sprite
{
	protected Image image;
	protected double posX;
	protected double posY;
	protected double velX;
	protected double velY;
	protected double width;
	protected double height;
	protected double angle;
	protected double angVel;
	protected Reaper gabriel;

	public Sprite( HashMap<String, LinkedList<Sprite>> spriteMap )
	{
		posX = 0;
		posY = 0;
		velX = 0;
		velY = 0;
		angle = 0;
		angVel = 0;
		LinkedList<Sprite> spriteList = spriteMap.get( this.getClass().getSimpleName() );
		if(spriteList != null)
		{
			spriteList.add( this );
		}
		else
		{
			spriteList = new LinkedList<Sprite>();
			spriteList.add( this );
			spriteMap.put( this.getClass().getSimpleName(), spriteList );
		}

	}

	/**
	 * Sets the image for the sprite to use from the steam emoticon database,
	 * for example to get :nepnep: you would pass "nepnep". This one lets you
	 * manually set the size to scale them to however big you want. go ham.
	 * 
	 * @param emoticon
	 *            Emoticon to use, you should totally pass nepnep to this.
	 * @param w
	 *            Width to scale to
	 * @param h
	 *            Height to scale to
	 */
	public void setImage( String emoticon, int w, int h )
	{
		String url = "https://steamcommunity-a.akamaihd.net/economy/emoticon/" + emoticon;
		Image i = HobSwing.imageFromURL( url );
		image = i.getScaledInstance( w, h, Image.SCALE_REPLICATE );
		width = w;
		height = h;
	}

	/**
	 * setPos Set the position of your sprite.
	 * 
	 * @param x
	 *            x pos.
	 * @param y
	 *            y pos.
	 */
	public void setPos( double x, double y )
	{
		posX = x;
		posY = y;
	}

	/**
	 * setVel Set the velocity of your sprite.
	 * 
	 * @param x
	 *            Velocity in the x direction
	 * @param y
	 *            Velocity in the y direction
	 */
	public void setVel( double x, double y )
	{
		velX = x;
		velY = y;
	}

	/**
	 * addVel Set the velocity of your sprite.
	 * 
	 * @param x
	 *            Velocity to add to the x direction
	 * @param y
	 *            Velocity to add to the y direction
	 */
	public void addVel( double x, double y )
	{
		velX += x;
		velY += y;
	}

	public void setAng( double ang )
	{
		angle = ang;
	}

	public void setAngVel( double angVel )
	{
		this.angVel = angVel;
	}
	/**
	 * Main "Think" Function, run on frame
	 * 
	 * @param time
	 *            - Pass how long since last update in seconds
	 */
	public void update( double time )
	{
		velX = Hob.clamp( velX, -5000.0, 5000.0 );
		velY = Hob.clamp( velY, -5000.0, 5000.0 );
		// angle = -Math.toDegrees( Math.atan2(velX,velY) ) + 180;
		posX += velX * time;
		posY += velY * time;
		angle = angle + angVel;
	}

	/**
	 * This handles all of the rendering for the object, do any fancy stuff like
	 * rotation here
	 * 
	 * @param gc
	 *            Graphics context, passed at runtime.
	 */
	public void render( Graphics g )
	{

		HobSwing.drawRotatedImage( g, image, angle, posX, posY );
	}

	/**
	 * This returns a 2d rectangle representing the sprites hitbox
	 * 
	 * @return Rectangle2D - the bounding box of the sprite
	 */
	public Rectangle2D getBoundary( )
	{
		return new Rectangle2D.Double( posX, posY, width, height );
	}

	/**
	 * Calculates if two sprites intersect using their bounding boxes
	 * 
	 * @param s
	 *            the other sprite to check
	 * @return boolean if they intersect
	 */
	public boolean intersects( Sprite s )
	{
		return s.getBoundary().intersects( this.getBoundary() );
	}

	/**
	 * if the sprite is on screen
	 * 
	 * @return boolean if it is
	 */
	public boolean onScreen( )
	{
		Point2D p = new Point2D.Double( posX, posY );
		return new Rectangle2D.Double( 0, 0, Hobicons.screenWidth, Hobicons.screenHeight ).contains( p );
	}

	/**
	 * returns information such as pos and vel
	 * 
	 */
	public String toString( )
	{
		return " Position: [" + posX + "," + posY + "]" + " Velocity: [" + velX + "," + velY + "]";
	}

	public void addReaper( Reaper r )
	{
		gabriel = r;
	}

	public void kill( )
	{
		gabriel.DieDieDie( this );
	}

}