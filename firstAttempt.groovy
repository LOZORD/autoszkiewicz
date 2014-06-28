dim = 64

//set up the image
myImg = new AutoImg(dim,dim)

//make the diag line bounds
def a = dim.intdiv(4)

def b = (3 * dim).intdiv(4)

//draw a white diagonal line on a black background
for (i in a..b)
{
  myImg.set(i, i, 0xffffff)
}

//write the file using the default file specs
myImg.write()
