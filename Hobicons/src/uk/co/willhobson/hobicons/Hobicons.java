package uk.co.willhobson.hobicons;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uk.co.willhobson.hobicons.interfaces.Controllable;
import uk.co.willhobson.hobicons.sprites.Asteroid;
import uk.co.willhobson.hobicons.sprites.Player;
import uk.co.willhobson.hobicons.sprites.Sprite;

public class Hobicons extends Application
{
	public static final String Version = "V0.3";
	public static final int ScreenWidth = 1600;
	public static final int ScreenHeight = 900;

	public LinkedList<Sprite> SpriteList = new LinkedList<Sprite>();
	
	private long before = System.nanoTime();
	
	public static void main( String[] args )
	{
		launch(args);

	}

	@Override
	public void start( Stage primaryStage ) throws Exception
	{
		primaryStage.setTitle( "Hobicons " + Version );
		
		Group root = new Group();
		Scene theScene = new Scene( root );
		primaryStage.setScene( theScene );
		
		Canvas canvas = new Canvas( ScreenWidth, ScreenHeight );
		root.getChildren().add( canvas );
		
		//inputs
		ArrayList<String> input = new ArrayList<String>();
		
		theScene.setOnKeyPressed( 
			new EventHandler<KeyEvent>()
			{
				public void handle(KeyEvent e)
				{
					String code = e.getCode().toString();
					if ( !input.contains(code) )
					{
						input.add( code );
					}
				}
			}
		);
		
		theScene.setOnKeyReleased( 
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						input.remove( code );
					}
				}
			);
		
		
		//Spawners
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Player player1 = new Player( SpriteList );
		player1.setImage( "spazhorror" , 32 , 32 , true);
		player1.setPos( 200, 100 );
		
		
		Asteroid asteroid1 = new Asteroid( SpriteList );
		asteroid1.setImage( "DamageControlAsteroid" , 64 , 64 , true );
		asteroid1.setPos( ScreenWidth/2, ScreenHeight/2 );
		
		new AnimationTimer()
		{
			public void handle( long now )
			{
				double elapsedTime = (now - before) / 1000000000.0;
				before = now;

                gc.clearRect( 0, 0, ScreenWidth	, ScreenHeight );
                
                for (Sprite sprite : SpriteList)
                {
                	if ( sprite instanceof Controllable )
                	{
                		((Controllable) sprite).KeyPress( input );
                	}
                	
                	sprite.update( elapsedTime );
                	sprite.render( gc );
                }
			}
			
		}.start();
		
		primaryStage.show();
		
	}

}
