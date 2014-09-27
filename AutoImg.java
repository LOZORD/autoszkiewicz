import java.awt.Color;
import java.awt.color.*;
import java.awt.image.*;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.io.*;
import java.util.LinkedList;
import java.util.HashSet;

import javax.imageio.*;

public class AutoImg extends BufferedImage
{

  //for now, only allow RGB images
  AutoImg(int w, int h)
  {
    super(w, h, BufferedImage.TYPE_INT_RGB);
  }

  void set(int x, int y, Color clr)
  {
    set(x, y, clr.getRGB());
  }

  void set(Point p, Color clr)
  {
    set(p, clr.getRGB());
  }

  void set(Point p, int clr)
  {
    set(p.x, p.y, clr);
  }

  void set(int x, int y, int clr)
  {
    setRGB(x, y, clr);
  }

  int get(int x, int y)
  {
    return getRGB(x, y);
  }

  int get(Point p)
  {
    return get(p.x, p.y);
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

  void writeBMP(String fileName)
  {
    write(fileName, "bmp");
  }

  void drawRectOutline(int origX, int origY, int width, int height, int clr)
  {
    try
    {
      //draw two horizontal lines
      for (int i = 0; i <= width; i++)
      {
        set(origX + i, origY, clr);
        set(origX + i, origY + height, clr);
      }

      //then, two vertical lines
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

  void drawRectOutline(Point p, int w, int h, int clr)
  {
    drawRectOutline(p.x, p.y, w, h, clr);
  }

  void drawRectOutline(Point a, Point b, int clr)
  {
    int w = b.x - a.x;
    int h = b.y - a.y;

    drawRectOutline(a.x, a.y, w, h, clr);
  }

  void stackFill(int x, int y, int clr)
  {

    LinkedList<Point> stack = new LinkedList<Point>();
    HashSet<Point> alreadySetPts = new HashSet<Point>();

    stack.push(new Point(x,y));

    Point pixel = null;

    while(!stack.isEmpty())
    {
      pixel = stack.pop();

      if (inBounds(pixel)
        && (get(pixel.x, pixel.y) != clr)
        && !alreadySetPts.contains(pixel) )
      {
        //set this pixel
        set(pixel.x, pixel.y, clr);

        alreadySetPts.add(pixel);

        //then push its neighbors
        stack.push(new Point(pixel.x + 1, pixel.y));
        stack.push(new Point(pixel.x - 1, pixel.y));
        stack.push(new Point(pixel.x, pixel.y + 1));
        stack.push(new Point(pixel.x, pixel.y - 1));

      }
    }
  }

  void stackFill(Point p, int clr)
  {
    stackFill(p.x, p.y, clr);
  }

  void fill(Point p, int clr)
  {
    //TODO add a function param so we can do different fills
    stackFill(p, clr);
  }

  void fill(Point p, Color clr)
  {
    fill(p, clr.getRGB());
  }

  boolean inBounds (Point p)
  {
    return inBounds(p.x, p.y);
  }

  boolean inBounds (int x, int y)
  {
    boolean xGood = x >= 0 && x < this.getWidth();

    boolean yGood = y >= 0 && y < this.getHeight();

    return xGood && yGood;
  }

  Point drawRect(int origX, int origY, int width, int height, int clr)
  {
    drawRectOutline(origX, origY, width, height, clr);

    stackFill(origX + 1, origY + 2, clr);

    return new Point(origX, origY);
  }

  Point drawRect(int origX, int origY, int width, int height, Color clr)
  {
    return drawRect(origX, origY, width, height, clr.getRGB());
  }

  Point drawRect(Point a, Point b, int clr)
  {
    return drawRect(a.x, a.y, (b.x - a.x), (b.y - a.y), clr);
  }

  Point drawRect(Point a, Point b, Color c)
  {
    return drawRect(a, b, c.getRGB());
  }

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

  //draws a hollow rectangle and returns the (x,y) point of the NW corner of the inner rect
  Point drawHollowRect(int origX, int origY, int outerW, int outerH, int innerW, int innerH, int clr)
  {
    //a hollow rectangle is made up of two rectangular outlines

    //first draw the outer rectangle
    drawRectOutline(origX, origY, outerW, outerH, clr);

    Point innerPoint = getConcentric(origX, origY, outerW, outerH, innerW, innerH);

    int iPx = innerPoint.x;

    int iPy = innerPoint.y;

    //then draw the inner border
    drawRectOutline(iPx, iPy, innerW, innerH, clr);

    //finally, fill in the difference
    stackFill(origX + 1, origY + 2, clr);

    return innerPoint;
  }

  Point drawHollowRect(int origX, int origY, int outerW, int outerH, int innerW, int innerH, Color clr)
  {
    return drawHollowRect(origX, origY, outerW, outerH, innerW, innerH, clr.getRGB());
  }

  Point drawHollowRect(Point oA, Point oB, int thickness, int clr)
  {
    return drawHollowRect(oA.x, oA.y, (oB.x - oA.x), (oB.y - oA.y), thickness, thickness, clr);
  }

  Point drawHollowRect(Point oA, Point oB, int thickness, Color clr)
  {
    return drawHollowRect(oA, oB, thickness, clr.getRGB());
  }

  Point drawHollowRect(Point oA, Point oB, Point iA, Point iB, int clr)
  {
    int outerW = oB.x - oA.x;
    int outerH = oB.y - oA.y;
    int innerW = iB.x - iB.x;
    int innerH = iB.y - iB.y;

    return drawHollowRect(oA.x, oA.y, outerW, outerH, innerW, innerH, clr);
  }

  Point drawHollowRect(Point oA, Point oB, Point iA, Point iB, Color clr)
  {
    return drawHollowRect(oA, oB, iA, iB, clr.getRGB());
  }

  //gets the NW point of an inner rectangle concentric to an outer rectangle
  Point getConcentric (int origX, int origY, int ow, int oh, int iw, int ih)
  {
    int newX = origX + ((ow - iw)/2);

    int newY = origY + ((oh - ih)/2);

    return new Point (newX, newY);
  }

}
