package uk.co.willhobson.hobicons.sprites;

import java.util.LinkedList;

public class Bullet extends Sprite
{
	
	public Bullet( LinkedList<Sprite> SpriteList )
	{
		super( SpriteList );
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update( double time )
	{
		if(!onScreen())
		{
			//kill some how
		}
		super.update( time );
	}
	
}
