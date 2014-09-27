clean: ; rm *.class *.bmp *.jpg *.tiff

test1: ; javac Test1.java; java Test1; open test_img.bmp

test2: ; javac Test2.java; java Test2; open white_and_red.bmp
