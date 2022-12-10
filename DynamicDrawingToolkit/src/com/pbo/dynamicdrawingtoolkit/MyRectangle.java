package com.pbo.dynamicdrawingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

public class MyRectangle extends DrawingObject
{
	private int rectangleWidth, rectangleHeight;
	
	public MyRectangle()
	{
		super();
	}
	
	public MyRectangle ( int x1, int y1, int x2, int y2, Color color)
	{
		super(x1, y1, x2, y2, color);
	}
	
	@Override
	public void draw( Graphics g )
	{
		rectangleWidth = getWidth();
		rectangleHeight = getHeight();
		
		g.drawRect(getUpperLeftX(), getUpperLeftY(), rectangleWidth, rectangleHeight);
	}
}