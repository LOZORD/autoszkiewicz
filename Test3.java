import java.awt.Color;
import java.awt.color.*;
import java.awt.Point;

class Test3
{
  public static void main (String [] args)
  {
    System.out.println("Concentric blue and white squares with red diagonal");

    int dim = 1024;

    AutoImg myImg = new AutoImg(dim, dim);

    //first draw a diagonal line
    for(int a = 0; a < dim; a++)
    {
      myImg.set(a,a, Color.red);
    }

    Point aa = myImg.drawHollowRect(dim/4, dim/4, dim/2, dim/2, dim/4, dim/4, Color.blue);

    myImg.drawRect(aa.x, aa.y, dim/4, dim/4, Color.white);

    myImg.write("red_white_and_blue.bmp");

    System.out.println("Written!");
  }
}
