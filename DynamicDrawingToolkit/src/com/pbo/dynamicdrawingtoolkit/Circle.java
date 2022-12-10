package com.pbo.dynamicdrawingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends DrawingObject
{
	private int circleWidth, circleHeight;
	
	public Circle()
	{
		super();
	}
	
	public Circle( int x1, int y1, int x2, int y2, Color color)
	{
		super(x1, y1, x2, y2, color);
	}
	
	@Override 
	public int getHeight()
	{
		return Math.abs(getX1()-getX2());
	}
	
	@Override
	public void draw( Graphics g )
	{
		circleWidth = getWidth();
		circleHeight = getHeight();
		
		g.drawOval(getUpperLeftX(), getUpperLeftY(), circleWidth, circleHeight);
	}
}