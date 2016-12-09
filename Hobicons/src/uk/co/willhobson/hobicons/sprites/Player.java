package uk.co.willhobson.hobicons.sprites;

import java.util.ArrayList;
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
	
	
	public void KeyPress( ArrayList<String> input )
	{
		//Inputs
        if (input.contains("A"))
            this.addVel(-25,0);
        if (input.contains("D"))
        	this.addVel(25,0);
        if (input.contains("W"))
        	this.addVel(0,-25);
        if (input.contains("S"))
        	this.addVel(0,25);
	}
	
	@Override
	public void update( double time )
	{
		if(!onScreen())
		{
			int x = Hobicons.screenWidth;
			int y = Hobicons.screenHeight;
			
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
	
}
