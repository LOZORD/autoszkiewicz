import java.awt.Point;

class BoolPoint extends Point
{
  private boolean isSet;

  public BoolPoint(int x, int y)
  {
    super(x,y);
    this.isSet = false;
  }

  public void set ()
  {
    this.isSet = true;
  }

  public void unset ()
  {
    this.isSet = false;
  }

  public boolean isSet ()
  {
    return this.isSet;
  }
}
