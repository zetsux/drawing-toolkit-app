package com.pbo.dynamicdrawingtoolkit;

import java.awt.Dimension;

import javax.swing.JFrame;

public class DrawingToolkit
{
	public static void main( String args[] )
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DrawingFrame canvasGUI = new DrawingFrame();
				canvasGUI.setSize(new Dimension(1000, 1000));
				canvasGUI.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				canvasGUI.setUndecorated(true);
				canvasGUI.setVisible(true);
			}
		});
	}
}
