class Test5C
{
	public static void main (String [] args)
	{
		int dim = 64;
		
		AutoImg myImg = new AutoImg(dim, dim);
		
		int red = 0xff0000;
		
		int green = 0x00ff00;
		
    int c = 0xffaa5c;
		
		myImg.drawRectOutline(0, 0, 16, 16, c);
		
		myImg.write("help.bmp");
	}
}
