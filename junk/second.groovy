dim = 64

//set up the image
myImg = new AutoImg(dim,dim)

//make the diag line bounds
//def a = dim.intdiv(4)

//def b = (3 * dim).intdiv(4)

//draw a white diagonal line on a black background
//for (i in a..b)
//{
//  myImg.set(i, i, 0xffffff)
//}

//draw a green square at the top left corner
//myImg.drawRect(4,4, 4, 4, 0x00ff00)

myImg.drawRectOutline(4,4,4,4, 0xffffff)

//write the file using the default file specs
myImg.write('green_square.bmp')
