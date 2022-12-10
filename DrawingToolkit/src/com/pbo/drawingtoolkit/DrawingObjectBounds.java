package com.pbo.drawingtoolkit;

import java.awt.Color;

abstract class DrawingObjectBounds extends DrawingObject
{
	public DrawingObjectBounds()
	{
		super();
	}
	
	public DrawingObjectBounds(int x1, int y1, int x2, int y2, Color color, boolean fill)
	{
		super(x1, y1, x2, y2, color);
	}
	 
	public int getUpperLeftX()
	{
		return Math.min(getX1(),getX2());
	}
	
	public int getUpperLeftY()
	{
		return Math.min(getY1(),getY2());
	}
	
	public int getWidth()
	{
		return Math.abs(getX1()-getX2());
	}
	
	public int getHeight()
	{
		return Math.abs(getY1()-getY2());
	}
}