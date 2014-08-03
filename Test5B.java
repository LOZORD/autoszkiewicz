import java.awt.Color;
import java.awt.color.*;
import java.awt.Point;

class Test5B
{
	public static void main (String [] args)
	{
		int dim = 32;
		
		AutoImg myImg = new AutoImg(dim, dim);
		
    int c = 0xffaa5c;
		
		//then draw a square

		myImg.drawRect(dim/4, dim/4, dim/2, dim/2, c);

		//myImg.write("test2.bmp", "bmp");

		myImg.write("miami.bmp");
	}
}
