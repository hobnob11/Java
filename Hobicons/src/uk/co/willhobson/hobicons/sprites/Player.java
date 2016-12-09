package uk.co.willhobson.hobicons.sprites;

import java.util.LinkedList;

import uk.co.willhobson.hobicons.Hobicons;
import uk.co.willhobson.hobicons.interfaces.Controllable;

public class Player extends Sprite implements Controllable
{

	public Player( LinkedList<Sprite> SpriteList )
	{
		super( SpriteList );
		// TODO Auto-generated constructor stub
	}

	public void KeyPress( LinkedList<String> inputsList )
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
	}

	@Override
	public void update( double time )
	{
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
		angle = -Math.toDegrees( Math.atan2( velX, velY ) ) + 90;
		super.update( time );
	}

}
