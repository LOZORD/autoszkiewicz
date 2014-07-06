import java.awt.Color;
import java.awt.color.*;
import java.awt.image.*;;
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

	//TODO write a one arg write() func that gets image type from file suffix

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

	void floodFill(int x, int y, int clr)
	{
		if (
			x < 0 || x >= this.getWidth() ||
			y < 0 || y >= this.getHeight() ||
			this.get(x, y) == clr
		   )
		{
			return;
		}

		//otherwise, recursively flood

		set(x,y, clr);

		floodFill(x + 1, y, clr);

		floodFill(x - 1, y, clr);

		floodFill(x, y + 1, clr);

		floodFill(x, y - 1, clr);

		return;
	}

	void drawRect(int origX, int origY, int height, int width, int clr)
	{
		drawRectOutline(origX, origY, height, width, clr);

		floodFill(origX + 1, origY + 1, clr);
	}

	void drawRect(int origX, int origY, int height, int width, Color clr)
	{
		drawRect(origX, origY, height, width, clr.getRGB());
	}





}
