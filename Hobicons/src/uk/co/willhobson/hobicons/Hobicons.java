package uk.co.willhobson.hobicons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.Timer;

import uk.co.willhobson.hobicons.interfaces.Controllable;
import uk.co.willhobson.hobicons.interfaces.Reaper;
import uk.co.willhobson.hobicons.sprites.Asteroid;
import uk.co.willhobson.hobicons.sprites.Player;
import uk.co.willhobson.hobicons.sprites.Sprite;

public class Hobicons extends JFrame implements ActionListener, KeyListener, Reaper
{
	// Config Vars
	public static final String version = "V0.4S";
	public static final int screenWidth = 1600;
	public static final int screenHeight = 900;
	public static final int tickRate = 60;
	public static final int fPS = 60;

	// Internal vars
	private LinkedList<Sprite> spriteList = new LinkedList<Sprite>();
	private LinkedList<String> inputsList = new LinkedList<String>();

	private DoubleBuffer db;
	private double buffer = System.nanoTime();

	private void init( )
	{
		setTitle( "Hobicons " + version );
		setSize( screenWidth, screenHeight );
		db = new DoubleBuffer( spriteList );
		setContentPane( db );
		addKeyListener( this );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	///////////////////////////////////////////////////////////
	// IF FIRST //
	///////////////////////////////////////////////////////////
	private void postInit( )
	{
		Timer timer = new Timer( 1000 / tickRate, this );
		timer.start();

		Player player1 = new Player( spriteList );
		player1.setImage( "spazhorror", 32, 32 );
		player1.setPos( screenWidth / 2, screenHeight / 2 );

		Asteroid as1 = new Asteroid( spriteList );
		as1.setImage( "DamageControlAsteroid", 32, 32 );
		as1.setPos( 600, 100 );
		as1.addReaper( this );
		
		Asteroid as2 = new Asteroid( spriteList );
		as2.setImage( "DamageControlAsteroid", 32, 32 );
		as2.setPos( 650, 200 );
		as2.addReaper( this );
	}
	/////////////////////////////////////////////////////
	// ELSE //
	/////////////////////////////////////////////////////

	// Game Logic, run at 60fps because memes.
	public void actionPerformed( ActionEvent ev )
	{
		double now = System.nanoTime();
		double elapsedTime = (now - buffer) / 1000000000.0;
		buffer = now;

		for (Sprite sprite : spriteList)
		{
			if (sprite instanceof Controllable)
			{
				((Controllable) sprite).KeyPress( inputsList );
				
				//collision detection
				for (Sprite target : spriteList)
				{
					if (sprite.intersects( target ) && sprite!=target)
					{
						System.out.println( "die diedie!" );
						target.kill();
					}
				}
				
			}
			sprite.update( elapsedTime );
		}
	}

	private void finished( )
	{
		setLocationRelativeTo( null );
		setVisible( true );
	}

	public Hobicons()
	{
		super();
		init();
		postInit();
		finished();
	}

	public static void main( String[] args )
	{
		javax.swing.SwingUtilities.invokeLater( new Runnable()
		{
			public void run( )
			{
				Hobicons main = new Hobicons();
			}
		} );
	}

	@Override
	public void keyTyped( KeyEvent e )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed( KeyEvent e )
	{
		String key = KeyEvent.getKeyText( e.getKeyCode() );
		if (!inputsList.contains( key ))
		{
			inputsList.add( key );
		}
	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		String key = KeyEvent.getKeyText( e.getKeyCode() );
		inputsList.remove( key );
	}

	@Override
	public void DieDieDie( Sprite s )
	{
		spriteList.remove( s );
	}

}