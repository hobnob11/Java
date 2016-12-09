package uk.co.willhobson.hobicons;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

import uk.co.willhobson.hobicons.sprites.Sprite;
import uk.co.willhobson.hoblib.HobSwing;

@SuppressWarnings("serial")
public class DoubleBuffer extends JPanel implements ActionListener
{
	private HashMap<String, LinkedList<Sprite>> spriteMap;
	public DoubleBuffer( HashMap<String, LinkedList<Sprite>> spriteMap )
	{
		this.spriteMap = spriteMap;
		Timer timer = new Timer( 1000 / Hobicons.fPS, this );
		timer.start();
		
		Image cs = Toolkit.getDefaultToolkit().getImage( "assets/csgocross.png" );
		Cursor css = Toolkit.getDefaultToolkit().createCustomCursor( cs, new Point(8,0), "noscop'd");
		setCursor( css );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		repaint();
	}

	@Override
	protected void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		for (String key : spriteMap.keySet())
		{
			LinkedList<Sprite> spriteList = spriteMap.get( key );
			for (Sprite sprite : spriteList)
			{
				sprite.render( g );
				Toolkit.getDefaultToolkit().sync();
			}
		}

	}
}
