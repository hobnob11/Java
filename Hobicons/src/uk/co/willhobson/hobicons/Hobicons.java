package uk.co.willhobson.hobicons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.Timer;

import uk.co.willhobson.hobicons.interfaces.Controllable;
import uk.co.willhobson.hobicons.interfaces.Reaper;
import uk.co.willhobson.hobicons.sprites.Asteroid;
import uk.co.willhobson.hobicons.sprites.Player;
import uk.co.willhobson.hobicons.sprites.Sprite;

@SuppressWarnings("serial")
public class Hobicons extends JFrame implements ActionListener, KeyListener, Reaper, MouseMotionListener, MouseListener
{
	// Config Vars
	public static final String version = "V0.5S-Memes";
	public static final int screenWidth = 1920;
	public static final int screenHeight = 1080;
	public static final int tickRate = 60;
	public static final int fPS = 60;
	public static double elapsedTime;
	// Internal vars
	private HashMap<String, LinkedList<Sprite>> spriteMap = new HashMap<String, LinkedList<Sprite>>();
	private LinkedList<String> inputsList = new LinkedList<String>();
	public static LinkedList<String> controllablesList = new LinkedList<String>();
	private int mouseX, mouseY;

	private DoubleBuffer db;
	private double buffer = System.nanoTime();

	private void init( )
	{
		setTitle( "Hobicons " + version );
		setSize( screenWidth, screenHeight );
		db = new DoubleBuffer( spriteMap );
		setContentPane( db );
		addKeyListener( this );
		addMouseMotionListener( this );
		addMouseListener( this );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	///////////////////////////////////////////////////////////
	// IF FIRST //
	///////////////////////////////////////////////////////////
	private void postInit( )
	{
		Timer timer = new Timer( 1000 / tickRate, this );
		timer.start();

		Player player1 = new Player( spriteMap );
		player1.addReaper( this );
		player1.setImage( "spazhorror", 32, 32 );
		player1.setPos( screenWidth / 2, screenHeight / 2 );

		for (int i = 0; i <= 20; i++)
		{
			Asteroid as = new Asteroid( spriteMap );
			as.addReaper( this );
			as.setImage( "asteroid", 64, 64 );
			double x = Math.random() * (double) screenWidth;
			double y = Math.random() * (double) screenHeight;
			as.setPos( x, y );
			as.setAngVel( Math.random()*2 -1.0 );
			as.setVel( Math.random()*20.0 -10.0, Math.random()*20.0 -10.0 );
			as.setSize( 4 );
		}

	}

	/////////////////////////////////////////////////////
	// ELSE //
	/////////////////////////////////////////////////////
	public void actionPerformed( ActionEvent ev )
	{
		double now = System.nanoTime();
		elapsedTime = (now - buffer) / 1000000000.0;
		buffer = now;

		HashSet<String> keys = new HashSet<String>();
		keys.addAll( spriteMap.keySet() );
		for (String key : keys)
		{
			LinkedList<Sprite> idk = new LinkedList<Sprite>();
			idk.addAll( spriteMap.get( key ) );
			for (Sprite sprite : idk)
			{
				if (controllablesList.contains( key ))
				{
					Controllable cSprite = ((Controllable) sprite);
					cSprite.keyPress( inputsList );
					cSprite.mouseMove( mouseX, mouseY );
				}
				sprite.update( elapsedTime );
			}
		}
		if (spriteMap.get( "Projectile" ) != null)
		{
			LinkedList<Sprite> projectiles = new LinkedList<Sprite>();
			projectiles.addAll( spriteMap.get( "Projectile" ) );
			for (Sprite projectile : projectiles)
			{
				LinkedList<Sprite> asteroids = new LinkedList<Sprite>();
				asteroids.addAll( spriteMap.get( "Asteroid" ) );
				for (Sprite asteroid : asteroids)
				{
					if (projectile.intersects( asteroid ))
					{
						asteroid.kill();
						projectile.kill();
					}
				}
			}
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
				new Hobicons();
			}
		} );
	}

	public void keyTyped( KeyEvent e )
	{
	}

	public void keyPressed( KeyEvent e )
	{
		String key = KeyEvent.getKeyText( e.getKeyCode() );
		if (!inputsList.contains( key ))
		{
			inputsList.add( key );
		}
	}

	public void keyReleased( KeyEvent e )
	{
		String key = KeyEvent.getKeyText( e.getKeyCode() );
		inputsList.remove( key );
	}

	public void DieDieDie( Sprite s )
	{
		spriteMap.get( s.getClass().getSimpleName() ).remove( s );
	}

	public void mouseMoved( MouseEvent e )
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mousePressed( MouseEvent e )
	{
		if (!inputsList.contains( "MOUSECLICK" ))
		{
			inputsList.add( "MOUSECLICK" );
		}
	}

	public void mouseReleased( MouseEvent e )
	{
		inputsList.remove( "MOUSECLICK" );
	}

	public void mouseDragged( MouseEvent e )
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked( MouseEvent e )
	{
	}

	public void mouseEntered( MouseEvent e )
	{
	}

	public void mouseExited( MouseEvent e )
	{
	}

}