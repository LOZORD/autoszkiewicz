import java.awt.Color;
import java.awt.color.*;

class Test2
{
	public static void main (String [] args)
	{
		int dim = 128;
		
		AutoImg myImg = new AutoImg(dim, dim);
		
		
		//first draw a diagonal line
		for(int a = 0; a < dim; a++)
		{
			myImg.set(a,a, Color.pink);
		}
		
		//then draw a square
		
		myImg.drawRect(dim/4, dim/4, dim/2, dim/2, Color.orange);
		
		myImg.write("test2.bmp", "bmp");
		
		System.out.println("Written!");
	}
}
