import java.awt.Color
//import java.awt.Graphics
import java.awt.image.BufferedImage
//import java.io.* --> should already be imported
import javax.imageio.ImageIO
//import java.lang.Math
//import java.lang.Number

//@InheritConstructors
class AutoImg extends java.awt.image.BufferedImage
{

  //Constructor
  AutoImg(int w, int h, int type = BufferedImage.TYPE_INT_RGB)
  {
    super(w,h,type)
  }

  //pixel setter with Color param
  def set(int x, int y, Color c) { this.setRGB(x,y, c.getRGB()) }

  //pixel setter with int param
  def set(int x, int y, int c) { this.setRGB(x,y,c); println("Set ${x}, ${y} to ${c}!") }

  //pixel getter
  def get(int x, int y) { this.getRGB(x,y) }

  //I know these are kind of redundant...

  //writes this image to a file
  def write(fileName = 'test_img.bmp', fileType = 'bmp')
  {
    try
    {
      def outputFile = new File (fileName);
      ImageIO.write(this, fileType, outputFile)
      println("Wrote image to file ${fileName}!")
      //outputFile.close() XXX need to close?
    }
    catch (IOException e)
    {
      System.err.print("Could not write image to file ${fileName}")
      System.err.println(e)
    }
  }

  def drawRect(int origX, int origY, int height, int width, int color)
  {
    drawRectOutline(origX, origY, height, width, color)
    floodFill(origX + 1, origY + 1, color)
  }

  def drawRectOutline(int origX, int origY, int height, int width, int color)
  {
    try
    {
      //draw the horizontal lines
      for (i in origX..<width)
      {
        this.set(i, origY, color)
        this.set(i, origY + height, color)
        println("i is ${i}")
      }

      //draw the vertical lines
      for(j in origY..<height)
      {
        this.set(origX, j, color)
        this.set(origX + width, color)
        println("j is ${j}")
      }

    }
    catch (Exception e)
    {
      //TODO may want to change from general to more specific exception
      System.err.println('Attempted to draw rectangle out of bounds of image!')
      //System.err.println("x-coord: ${i}, y-coord: ${j}")
      System.exit(1)
    }
  }

  def drawHollowRect
  {
    //TODO
  }

  //def floodFill(int x, int y, Color c)
  //{
  //  floodFill(x,y,c.getRGB())
  //}

  def floodFill(int x, int y, int c)
  {
    if (x < 0 || x >= this.getWidth() ||
        y < 0 || y >= this.getHeight())
    {
      //we are out of bounds, so return
      return
    }

    //if we have already filled this pixel, or we've reached a boundary
    if (this.get(x,y) == c)
    {
      return
    }

    else
    {

      this.set(x,y,c)

      floodFill(x + 1, y, c)
      floodFill(x - 1, y, c)
      floodFill(x, y + 1, c)
      floodFill(x, y - 1, c)
    }

  }

  //TODO add shape drawers

}
