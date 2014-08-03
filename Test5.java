import java.awt.Color;
import java.awt.color.*;
import java.awt.Point;

public class Test5
{
  public static void main (String [] args)
  {

    /*
      FIXME
      There is a weird out of heap space error coming from this!
      Even when the dimensions are small, it still happens.
    */

    int dim = 64;

    AutoImg myImg = new AutoImg(dim, dim);

    int [] colors = new int[]{0xffaa5c, 0xda727e, 0xac6c82, 0x685c79, 0x455c7b};

    int c = 0xffaa5c;

    Point oA, oB, iA, iB, p;

    oA = new Point(4, 4);

    oB = new Point(dim - 4, dim - 4);

    int thickness = 4;

    int s = colors.length;
    
    //FIXME
    //p = myImg.drawHollowRect(oA, oB, thickness, colors[0]);

    //p = myImg.drawRect(oA, oB, c);

    p = myImg.drawRect(dim/4, dim/4, dim/2, dim/2, c);

    /*
    for (int i = 0; i < 1; i++)
    {
       p = myImg.drawHollowRect(oA, oB, thickness, colors[i%s]);

       oA = p;

       oB = new Point(dim/(2*i+1), dim/(2*i+1));

       myImg.writeBMP("test5_" + i + ".bmp");
    }
    */

    myImg.writeBMP("sunset.bmp");
  }
}
