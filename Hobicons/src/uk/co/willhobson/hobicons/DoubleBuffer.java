package uk.co.willhobson.hobicons;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

import uk.co.willhobson.hobicons.sprites.Sprite;

public class DoubleBuffer extends JPanel implements ActionListener
{
	private LinkedList<Sprite> spriteList;

	public DoubleBuffer( LinkedList<Sprite> sl)
	{
		spriteList = sl;
		Timer timer = new Timer( 1000 / Hobicons.fPS, this );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		repaint();
	}

	@Override
	protected void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		for (Sprite sprite : spriteList)
		{
			sprite.render( g );
			Toolkit.getDefaultToolkit().sync();
		}
	}
}
