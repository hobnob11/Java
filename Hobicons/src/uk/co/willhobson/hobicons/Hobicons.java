package uk.co.willhobson.hobicons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.Timer;

import uk.co.willhobson.hobicons.sprites.Asteroid;
import uk.co.willhobson.hobicons.sprites.Sprite;

public class Hobicons extends JFrame implements ActionListener
{
	// Config Vars
	public static final String version = "V0.4S";
	public static final int screenWidth = 1600;
	public static final int screenHeight = 900;
	public static final int fPS = 60;

	// Internal vars
	public LinkedList<Sprite> spriteList = new LinkedList<Sprite>();
	public DoubleBuffer db;

	private void init( )
	{
		setTitle( "Hobicons " + version );
		setSize( screenWidth, screenHeight );
		db = new DoubleBuffer( spriteList );
		setContentPane( db );
	}

	private void postInit( )
	{
		Timer timer = new Timer( 1000 / fPS, this );
		timer.start();
		
		Asteroid meme = new Asteroid( spriteList );
		meme.setImage( "spazhorror" , 64 , 64 );
	}

	// Game Logic, run at 60fps because memes.
	public void actionPerformed( ActionEvent ev )
	{

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

}