import java.awt.Color;
import java.awt.color.*;
import java.awt.Point;

public class Test4
{
  public static void main (String [] args)
  {
    int dim = 1024;

    AutoImg myImg = new AutoImg(dim, dim);

    //draw a rising diagonal line
    for(int a = 0; a < dim; a++)
    {
      myImg.set(dim - 1 - a,a, Color.cyan);
    }

    myImg.write("test4a.bmp");

    //then draw a pink square concentric inside an orange square
    Point start = new Point(dim/4, dim/4);

    Point inner = myImg.drawHollowRect(start.x, start.y, dim/2, dim/2, dim/16, dim/16, Color.orange.getRGB());

    myImg.write("test4b.bmp");

    myImg.drawRect(inner.x, inner.y, dim/16, dim/16, Color.pink.getRGB());

    myImg.write("test4c.bmp");
  }
}
