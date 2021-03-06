import java.awt.Color;
import java.awt.color.*;

class Test2
{
  public static void main (String [] args)
  {
    System.out.println("Draw a diagonal red line going " +
      "through at centered solid white square");
    int dim = 512;

    AutoImg myImg = new AutoImg(dim, dim);

    //first draw a diagonal line
    for(int a = 0; a < dim; a++)
    {
      myImg.set(a,a, Color.red);
    }

    //then draw a square

    myImg.drawRect(dim/4, dim/4, dim/2, dim/2, Color.white);

    //myImg.write("test2.bmp", "bmp");

    myImg.write("white_and_red.bmp");

    System.out.println("Written!");
  }
}
