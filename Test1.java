class Test1
{
	public static void main (String [] args)
	{
		int dim = 64;
		
		AutoImg myImg = new AutoImg(dim, dim);
		
		int red = 0xff0000;
		
		int green = 0x00ff00;
		
		//first draw a diagonal line
		for(int a = 0; a < dim; a++)
		{
			myImg.set(a,a, red);
		}
		
		//then draw a square
		
		myImg.drawRectOutline(0, 0, 16, 16, green);
		
		myImg.write();
		
		System.out.println("Written!");
	}
}
