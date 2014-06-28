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

  //setter with Color param
  def set(int x, int y, Color c) { this.setRGB(x,y, c.getRGB()) }

  //setter with int param
  def set(int x, int y, int c) { this.setRGB(x,y,c) }

  //getter
  def get(int x, int y) { this.getRGB(x,y,c) }

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

  //TODO add shape drawers

}
