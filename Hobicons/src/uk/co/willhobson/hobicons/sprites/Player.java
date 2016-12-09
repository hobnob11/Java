package uk.co.willhobson.hobicons.sprites;

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
	private int fireRate = 2;
	private HashMap<String, LinkedList<Sprite>> spriteMap;
	
	public Player( HashMap<String, LinkedList<Sprite>> spriteMap )
	{
		super( spriteMap );
		this.register();
		this.spriteMap = spriteMap;
	}

	private void shoot()
	{
		if(canShoot == 0 )
		{
			canShoot = (Hobicons.tickRate/fireRate);
			System.out.println( "Bang!" );
			
			Projectile proj = new Projectile( spriteMap );
			proj.addReaper( gabriel );
			proj.setImage( "nepnep" , 8, 8 );
			int vel = 400;
			proj.setPos( posX, posY );
			proj.setVel( Math.sin(angle)*vel, Math.cos( angle )*vel );
			
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
		canShoot = (int)Hob.clamp( (double)canShoot - 1.0, 0.0, 999999.0 );
		
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
	public void register( )
	{
		System.out.println( "WASDP" );
		Hobicons.controllablesList.add( this.getClass().getSimpleName() );
	}

}
