package uk.co.willhobson.hobicons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import uk.co.willhobson.hobicons.interfaces.Controllable;
import uk.co.willhobson.hobicons.sprites.Asteroid;
import uk.co.willhobson.hobicons.sprites.Sprite;
import uk.co.willhobson.hoblib.HobSwing;

public class Hobicons extends JPanel implements ActionListener
{
	// Config Vars
	public static final String Version = "V0.4S";
	public static final int ScreenWidth = 1600;
	public static final int ScreenHeight = 900;
	public static final int FPS = 60;
	// Internal vars
	public LinkedList<Sprite> SpriteList = new LinkedList<Sprite>();
	private long before = System.nanoTime();

	private void init( )
	{
		Timer timer = new Timer( 1000 / FPS, this );
		timer.start();
		Asteroid ass1 = new Asteroid( SpriteList );
		ass1.setImage( "spazhorror", 64, 64, true );
		ass1.setPos( ScreenWidth / 2, ScreenHeight / 2 );

	}

	public Hobicons()
	{
		super();
		init();
	}

	// Game Logic, run at 60fps because memes.
	public void actionPerformed( ActionEvent ev )
	{
		long now = System.nanoTime();
		double elapsedTime = (now - before) / 1000000000.0;
		before = now;

		for (Sprite sprite : SpriteList)
		{
			sprite.update( elapsedTime );
			// sprite.setAng( -10 );
			sprite.setAng( (System.currentTimeMillis() / 10) % 360 );
		}
		repaint();

	}

	@Override
	public void update( Graphics g )
	{
		// TODO Auto-generated method stub
		super.update( g );
	}

	@Override
	public void paint( Graphics g )
	{
		super.paint( g );
		// Color c = new Color( 255, 255, 255 );
		// g.setColor( c );
		// g.fillRect( 0, 0, ScreenWidth, ScreenHeight );

		// Rendering Stuff
		for (Sprite sprite : SpriteList)
		{
			sprite.render( g );
			Toolkit.getDefaultToolkit().sync();
			System.out.println( sprite );
		}
	}

	public static void main( String[] args )
	{
		javax.swing.SwingUtilities.invokeLater( new Runnable()
		{
			public void run( )
			{
				JPanel main = new JPanel();
				main.setTitle( "Hobicons " + Version );
				main.setSize( ScreenWidth, ScreenHeight );
				main.setLocationRelativeTo( null );
				main.setVisible( true );

				Hobicons hobicon = new Hobicons();
			}
		} );
	}

}