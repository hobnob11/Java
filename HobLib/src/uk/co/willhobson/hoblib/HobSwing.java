package uk.co.willhobson.hoblib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

public class HobSwing
{
	public static Image imageFromURL(String URL)
	{
		URL url = null;
		try
		{
			url = new URL(URL);
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return java.awt.Toolkit.getDefaultToolkit().createImage(url);   
		
	}
	
	public static void drawRotatedImage(Graphics g, Image i, double ang, double x, double y)
	{
		Graphics2D g2d = (Graphics2D)g;
		double a = Math.toRadians( ang );
		double w = i.getWidth( null );
		double h = i.getHeight( null );
		//y = y - h; // do not question that which fucking noone understands.
		g2d.rotate( a , x , y );
		g2d.drawImage( i, (int)(x - w/2), (int)(y - h/2), null );
		g2d.rotate( -a , x , y );
		//g.drawImage( i, (int)x, (int)y, null );
	}
}
