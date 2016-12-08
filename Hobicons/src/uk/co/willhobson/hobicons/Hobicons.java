package uk.co.willhobson.hobicons;

import java.util.ArrayList;

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
	public static final String Version = "V0.2";
	
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
		
		Canvas canvas = new Canvas( 1024, 1024 );
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
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Player player1 = new Player();
		player1.setImage( "spazhorror" , 32 , 32 , true);
		player1.setPos( 200, 100 );
		
		
	}

}
