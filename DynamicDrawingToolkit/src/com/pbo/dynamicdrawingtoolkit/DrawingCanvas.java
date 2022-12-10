package com.pbo.dynamicdrawingtoolkit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingCanvas extends JPanel
{
	private ArrayList<DrawingObject> myShapes;
	private int currentShapeType;
	private DrawingObject currentShapeObject;
	private DrawingObject selectedShapeObject;
	private Color currentShapeColor;

	JLabel statusLabel;
	
	public DrawingCanvas(JLabel statusLabel){
		 myShapes = new ArrayList<DrawingObject>();
		 currentShapeType=-1;
		 currentShapeObject=null;
		 currentShapeColor=Color.BLACK;
		 selectedShapeObject=null;

		 this.statusLabel = statusLabel;
		 setLayout(null);
		 setBackground( Color.WHITE );
		 add( statusLabel, BorderLayout.SOUTH );

		 MouseHandler handler = new MouseHandler();
		 addMouseListener( handler );
		 addMouseMotionListener( handler );
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
	
		for ( int counter= myShapes.size()-1; counter>=0; counter-- )
			myShapes.get(counter).draw(g);
	
		if (currentShapeObject != null)
			currentShapeObject.draw(g);
		
		if (selectedShapeObject != null)
		{
			g.setColor(Color.BLUE);
			g.drawRect(selectedShapeObject.getUpperLeftX() - 1, selectedShapeObject.getUpperLeftY() - 1, selectedShapeObject.getWidth() + 2, selectedShapeObject.getHeight() + 2);
		}
	}
	
	public void setCurrentShapeType(int type)
	{
		currentShapeType=type;
		currentShapeObject=null;
		selectedShapeObject = null;
		
		Cursor cursor;
		if (currentShapeType == -1) {
			cursor = new Cursor(Cursor.HAND_CURSOR);
		} else {
			cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		}
		
		setCursor(cursor);
		repaint();
	}
	
	public void clearDrawing()
	{
		myShapes.clear();
		currentShapeType=-1;
		currentShapeObject=null;
		selectedShapeObject = null;
		repaint();
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked( MouseEvent event ) 
		{
			selectedShapeObject = null;
			if (currentShapeType == -1) {
				for ( int counter= myShapes.size()-1; counter>=0; counter-- )
				{
					DrawingObject d = myShapes.get(counter);
					if (d.bound.getBounds().contains(event.getX(), event.getY())) {
						selectedShapeObject = d;
						break;
					}
				}
			}
			
			repaint();
		}
		
		public void mousePressed( MouseEvent event )
		{
			Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
			switch (currentShapeType)
			{
				case 0:
				currentShapeObject= new Line( event.getX(), event.getY(),
				event.getX(), event.getY(),currentShapeColor);
				setCursor(cursor);
				break;
				
				case 1:
				currentShapeObject= new Circle( event.getX(), event.getY(),
				event.getX(), event.getY(), currentShapeColor);
				setCursor(cursor);
				break;
				
				case 2:
				currentShapeObject= new MyRectangle( event.getX(), event.getY(),
				event.getX(), event.getY(), currentShapeColor);
				setCursor(cursor);
				break;
				
				case 3:
				currentShapeObject= new Oval( event.getX(), event.getY(),
				event.getX(), event.getY(), currentShapeColor);
				setCursor(cursor);
				break;
			}
		}
	
		public void mouseReleased( MouseEvent event )
		{
			if (currentShapeObject != null) {
				currentShapeObject.setX2(event.getX());
				currentShapeObject.setY2(event.getY());
				currentShapeObject.bound = new Rectangle(currentShapeObject.getUpperLeftX(), currentShapeObject.getUpperLeftY(), currentShapeObject.getWidth(), currentShapeObject.getHeight());
			
				myShapes.add(currentShapeObject);
				System.out.println(myShapes.toArray());
			
				currentShapeObject=null;
				
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(cursor);
			}
			
			repaint();
		}
	
		public void mouseMoved( MouseEvent event )
		{
			statusLabel.setText(String.format("Mouse Coordinates X: %d Y: "
					+ "%d",event.getX(),event.getY()));
		}
	
		public void mouseDragged( MouseEvent event )
		{
			int xMouse = event.getX(), yMouse = event.getY();
			if (currentShapeObject != null) {
				currentShapeObject.setX2(xMouse);
				currentShapeObject.setY2(yMouse);
				statusLabel.setText(String.format("Mouse Coordinates X: %d Y: "
						+ "%d", xMouse, yMouse));
			} else if (selectedShapeObject != null) {
				int xDiff = (selectedShapeObject.getWidth()/2), yDiff = (selectedShapeObject.getHeight()/2);
				selectedShapeObject.setX1(xMouse - xDiff);
				selectedShapeObject.setX2(xMouse + xDiff);
				selectedShapeObject.setY1(yMouse - yDiff);
				selectedShapeObject.setY2(yMouse + yDiff);
				
				selectedShapeObject.bound = new Rectangle(selectedShapeObject.getUpperLeftX(), selectedShapeObject.getUpperLeftY(), selectedShapeObject.getWidth(), selectedShapeObject.getHeight());
			}
			
			repaint();
		}
	}

}