package uk.co.willhobson.hobicons.sprites;

import java.util.HashMap;
import java.util.LinkedList;

public class Projectile extends Sprite
{

	public Projectile( HashMap<String, LinkedList<Sprite>> spriteMap )
	{
		super( spriteMap );
	}

	public void update( double time )
	{
		if (!onScreen())
		{
			kill();
		}
		super.update( time );
		angle = -Math.toDegrees( Math.atan2( velX, velY ) ) + 180;
	}

}
