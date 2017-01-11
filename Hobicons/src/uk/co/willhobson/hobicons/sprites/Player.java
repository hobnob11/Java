package uk.co.willhobson.hobicons.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;

import uk.co.willhobson.hobicons.Hobicons;
import uk.co.willhobson.hobicons.interfaces.Controllable;
import uk.co.willhobson.hoblib.Hob;

public class Player extends Sprite implements Controllable
{
	private int mouseX = 0;
	private int mouseY = 0;
	private int canShoot = 0;
	private int fireRate = 100;
	private HashMap<String, LinkedList<Sprite>> spriteMap;

	public Player( HashMap<String, LinkedList<Sprite>> spriteMap )
	{
		super( spriteMap );
		this.register();
		this.spriteMap = spriteMap;
	}

	private void shoot( )
	{
		if (canShoot == 0)
		{
			canShoot = (Hobicons.tickRate / fireRate);

			Projectile proj = new Projectile( spriteMap );
			proj.addReaper( gabriel );
			proj.setImage( "nepnep", 16, 16 );
			int vel = 400;
			proj.setPos( posX, posY );
			proj.setVel( Math.cos( Math.toRadians( angle) ) * vel, Math.sin(Math.toRadians( angle) ) * vel );
		}
	}

	public void keyPress( LinkedList<String> inputsList )
	{
		// Inputs
		if (inputsList.contains( "A" ))
			this.addVel( -25, 0 );
		if (inputsList.contains( "D" ))
			this.addVel( 25, 0 );
		if (inputsList.contains( "W" ))
			this.addVel( 0, -25 );
		if (inputsList.contains( "S" ))
			this.addVel( 0, 25 );
		if (inputsList.contains( "Space" ))
			this.setVel( 0, 0 );
		if (inputsList.contains( "MOUSECLICK" ))
			shoot();

	}

	@Override
	public void mouseMove( int x, int y )
	{
		mouseX = x;
		mouseY = y;
	}

	@Override
	public void update( double time )
	{
		canShoot = (int) Hob.clamp( (double) canShoot - 1.0, 0.0, 999999.0 );

		if (!onScreen())
		{
			int x = Hobicons.screenWidth;
			int y = Hobicons.screenHeight;

			if (posX < 0)
			{
				posX = x;
			} else if (posX > x)
			{
				posX = 0;
			} else if (posY < y)
			{
				posY = y;
			} else if (posY > y)
			{
				posY = 0;
			}

		}
		angle = -Math.toDegrees( Math.atan2( posX - mouseX, posY - mouseY ) ) - 90;
		super.update( time );
	}
	
	@Override
	public void render( Graphics g )
	{
		super.render( g );
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.red);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
		g2d.drawString( "X: " + posX + " Y: " + posY + " A: " + (int)angle, 10, 20 );
		g2d.drawString( "FPS " + (int)((1/Hobicons.elapsedTime)) , 10, 40 );
	}
	
	
	@Override
	public void register( )
	{
		Hobicons.controllablesList.add( this.getClass().getSimpleName() );
	}

}
