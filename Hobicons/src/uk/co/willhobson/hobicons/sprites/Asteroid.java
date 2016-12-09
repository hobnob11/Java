package uk.co.willhobson.hobicons.sprites;

import java.util.HashMap;
import java.util.LinkedList;

import uk.co.willhobson.hobicons.Hobicons;

public class Asteroid extends Sprite
{
	private int size;
	private HashMap<String, LinkedList<Sprite>> spriteMap;
	
	public Asteroid( HashMap<String, LinkedList<Sprite>> spriteMap )
	{
		super( spriteMap );
		this.spriteMap = spriteMap;
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}
	
	@Override
	public void kill( )
	{
		//size = size - 1;
		if(size>0) {
			for (int i=1; i<=4; i++)
			{
				Asteroid as = new Asteroid( spriteMap );
				as.addReaper( gabriel );
				as.setImage( "asteroid", size*16, size*16 );
				double x = Math.random() * (double) Hobicons.screenWidth;
				double y = Math.random() * (double) Hobicons.screenHeight;
				as.setPos( posX, posY );
				as.setAngVel( Math.random()*2 -1.0 );
				as.setVel( (Math.random()*400.0 -200.0)*(5-size), (Math.random()*400.0 -200.0)*(5-size) );
				as.setSize( size );
			}
		}
		// TODO Auto-generated method stub
		super.kill();
	}
	
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
		super.update( time );
	}
}