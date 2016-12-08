package com.willhobson.hobsnake;

import java.util.ArrayList;
import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uk.co.willhobson.hoblib.Hob;
import uk.co.willhobson.hoblib.HobFX;

public class HobSnake extends Application{
	double hx = 0;
	double hy = 0;
	double hvelx = 0;
	double hvely = 0;
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	@Override
	public void start(Stage theStage) throws Exception 
	{
		// TODO Auto-generated method stub
		theStage.setTitle("Yo ready for some memes kanz?");
		
		Group root = new Group();
		Scene theScene = new Scene( root );
		theStage.setScene( theScene );
		
		Canvas canvas = new Canvas(512,512);
		root.getChildren().add( canvas );
		
		ArrayList<String> inputs = new ArrayList<String>();
		
		theScene.setOnKeyPressed( 
			new EventHandler<KeyEvent>()
			{
				public void handle(KeyEvent e)
				{
					String code = e.getCode().toString();

					//no duplicates, that wouldn't make sense :D:
					if ( !inputs.contains( code ) )
					{
						inputs.add( code );
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
						inputs.remove( code );
					}
				}
			);
		
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		String url = "https://steamcommunity-a.akamaihd.net/economy/emoticon/";
		Image EmotiD = new Image( url + "D", 64, 64, true, true ); 
		Image EmotiN = new Image( url + "nepnep", 32, 32, true, true );
		Image EmotiS = new Image( url + "praisesun", 128, 128, true, true );
		Image G = new Image( "https://i.ytimg.com/vi/LCVIbjmMOOI/maxresdefault.jpg" , 512, 512, false, true );
		Image EmotiH = new Image( url + "spazhorror", 32, 32, true, true );
		
		final long startNanoTime = System.nanoTime();

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
				
				double x = 256 + 128 * Math.cos( t );
				double y = 256 + 128 * Math.sin( t );
				double nx = x + 64 * Math.cos( t * 12 );
				double ny = y + 64 * Math.sin( t * 12 );
				
				hvelx = Hob.clamp( hvelx + (inputs.contains( "RIGHT" ) ? 1 : 0) - (inputs.contains( "LEFT" ) ? 1 : 0), -100.0, 100.0 );
				hvely = Hob.clamp( hvely + (inputs.contains( "DOWN" ) ? 1 : 0) - (inputs.contains( "UP" ) ? 1 : 0), -100.0, 100.0 );
				
				hx = Hob.clamp( hx+hvelx / 10.0, 0.0, 512.0-32.0 );
				hy = Hob.clamp( hy + hvely / 10.0, 0.0, 512.0-32.0 );
				double ang = Math.toDegrees( Math.atan2(hvelx,hvely) );
				System.out.println( ang );
				gc.drawImage( G, 0, 0 );
				gc.drawImage( EmotiS, 256-64, 256-64 );
				gc.drawImage( EmotiD, x-32, y-32 );
				gc.drawImage( EmotiN, nx-16, ny-16 );
				//gc.drawImage( EmotiH, hx-16, hy-16 );
				HobFX.drawRotatedImage( gc, EmotiH, -ang+90, hx, hy );
			}
		}.start();
		
		theStage.show();
	}

}
