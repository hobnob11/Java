package uk.co.willhobson.hobicons.sprites;

import javafx.scene.canvas.GraphicsContext;
import uk.co.willhobson.hoblib.HobFX;

public class Player extends Sprite
{
	
	
	
	@Override
	public void render( GraphicsContext gc )
	{
		double ang = Math.toDegrees( Math.atan2(velX,velY) );
		HobFX.drawRotatedImage( gc, image, ang, posX, posY );
	}
}
