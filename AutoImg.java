import java.awt.Color;
import java.awt.color.*;
import java.awt.image.*;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.io.*;
import javax.imageio.*;

public class AutoImg extends BufferedImage
{

	//for now, only allow RGB images
	AutoImg(int w, int h)
	{
		super(w, h, BufferedImage.TYPE_INT_RGB);
	}

	void set (int x, int y, Color clr)
	{
		set(x, y, clr.getRGB());
	}

	void set(int x, int y, int clr)
	{
		setRGB(x, y, clr);
	}

	int get(int x, int y)
	{
		return getRGB(x, y);
	}

	Color getColor(int x, int y)
	{
		int someRGB = get(x,y);

		return new Color(someRGB);
	}

	void write(String fileName, String fileType)
	{
		try
		{
			File outputFile = new File (fileName);
			ImageIO.write(this, fileType, outputFile);
			System.out.println("Successfully wrote image to file " + fileName);
		}
		catch (IOException e)
		{
			System.err.println("Could not write image to file " + fileName);
			System.err.println(e.toString());
			System.exit(1);
		}
	}

	void write()
	{
		write("test_img.bmp", "bmp");
	}

	void write(String fileName)
	{
		int suffixStart = fileName.lastIndexOf('.');

		//start one character after the dot
		String fileType = fileName.substring(suffixStart + 1);

		write(fileName, fileType);

	}

	void drawRectOutline(int origX, int origY, int height, int width, int clr)
	{
		try
		{
			for (int i = 0; i <= width; i++)
			{
				set(origX + i, origY, clr);
				set(origX + i, origY + height, clr);
			}

			for (int j = 0; j <= height; j++)
			{
				set(origX, origY + j, clr);
				set(origX + width, origY + j, clr);
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Attempted to draw rectangle out of image's bounds!");
			System.exit(1);
		}
	}

	//FIXME --> causes stackoverflows at larger img sizes
	void floodFill(int x, int y, int clr)
	{
		if 	(
				x < 0 || x >= this.getWidth() ||
				y < 0 || y >= this.getHeight()
			)
		{
			return;
		}

		else if (this.get(x, y) == clr)
		{
			return;
		}

		//otherwise, recursively flood
		else
		{

			set(x,y, clr);

			floodFill(x + 1, y, clr);

			floodFill(x - 1, y, clr);

			floodFill(x, y + 1, clr);

			floodFill(x, y - 1, clr);

			return;
		}
	}

	//because at large sizes, floodFill was causing StackOverflows
	void safeFill(int x, int y, int w, int h, int clr)
	{
		for (int i = x; i < x + w; i++)
		{
			for (int j = y; j < y + h; j++)
			{
				set(i, j, clr);
			}
		}
	}

//Attempted optimization of safeFill
//	void riskyFill(int x, int y, int w, int h, int clr)
//	{
//		for (int i = x + 1; i < w; i++)
//		{
//			for (int j = y + 1; true; j++)
//			{
//				if (get(i,j) == clr) break;
//				else set(i,j,clr);
//			}
//		}
//	}

	void drawRect(int origX, int origY, int width, int height, int clr)
	{
		drawRectOutline(origX, origY, height, width, clr);

		//floodFill(origX + 1, origY + 1, clr);

		safeFill(origX, origY, height, width, clr);

		//riskyFill(origX, origY, width, height, clr);
	}

	void drawRect(int origX, int origY, int width, int height, Color clr)
	{
		drawRect(origX, origY, height, width, clr.getRGB());
	}

	//draws a hollow rectangle and returns the (x,y) point of the NW corner of the inner rect
	Point drawHollowRect(int origX, int origY, int outerW, int outerH, int innerW, int innerH)
	{
		//TODO
		return new Point(0,0);
	}


}
