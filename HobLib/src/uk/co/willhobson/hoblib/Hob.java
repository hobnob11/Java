package uk.co.willhobson.hoblib;

/**Hob-co Lib Functions of usefulness, probably */
public class Hob
{
    /**
     * Detects memes in a string
     *
     * @param Memes the string to analyse. 
     */
	public static boolean memes(String Memes)
	{
		return Memes.contains( "Kanz" );
	}
	
    /**
     * Math.Clamp, limits a number between the min and max
     *
     * @param x The number to clamp
     * @param min the minimum the number can go.
     * @param max the maximum the number can go.
     */
	public static double clamp(Double x, Double min, Double max)
	{
		return Math.max( Math.min( x , max ) , min );
	}
}
