package uk.co.willhobson.hobicons;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uk.co.willhobson.hobicons.sprites.Player;

public class Hobicons extends Application
{
	public static final String Version = "V0.3";
	public static final int ScreenWidth = 1024;
	public static final int ScreenHeight = 1024;
	
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
		
		Player player1 = new Player();
		player1.setImage( "spazhorror" , 32 , 32 , true);
		player1.setPos( 200, 100 );
		
		new AnimationTimer()
		{
			public void handle( long now )
			{
				double elapsedTime = (now - before) / 1000000000.0;
				before = now;
				
				//Inputs
                if (input.contains("LEFT"))
                    player1.addVel(-25,0);
                if (input.contains("RIGHT"))
                	player1.addVel(25,0);
                if (input.contains("UP"))
                	player1.addVel(0,-25);
                if (input.contains("DOWN"))
                	player1.addVel(0,25);
				
                //logic 
                player1.update( elapsedTime );
                
                
                //render
                gc.clearRect( 0, 0, ScreenWidth	, ScreenHeight );
                player1.render( gc );
			}
			
		}.start();
		
		primaryStage.show();
		
	}

}
