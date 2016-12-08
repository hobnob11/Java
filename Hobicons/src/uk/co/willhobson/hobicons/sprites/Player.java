package uk.co.willhobson.hobicons.sprites;

import javafx.scene.canvas.GraphicsContext;
import uk.co.willhobson.hobicons.Hobicons;
import uk.co.willhobson.hoblib.HobFX;

public class Player extends Sprite
{
	
	@Override
	public void update( double time )
	{
		if(!onScreen())
		{
			int x = Hobicons.ScreenWidth;
			int y = Hobicons.ScreenHeight;
			
			if( posX < 0 )
			{
				posX = x;
			}
			else if( posX > x)
			{
				posX = 0;
			}
			else if( posY < y)
			{
				posY = y;
			}
			else if( posY > y)
			{
				posY = 0;
			}
			
		}
		super.update( time );
	}
	
	@Override
	public void render( GraphicsContext gc )
	{
		double ang = Math.toDegrees( Math.atan2(velX,velY) );
		HobFX.drawRotatedImage( gc, image, -ang + 90, posX, posY );
	}
}
