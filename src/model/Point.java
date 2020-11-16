package model;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    //public void setX(int x) {this.x = x; }

    public int getY() { return y; }

    //public void setY(int y) { this.y = y;}

    public Boolean isEqual(Point p){
        return (p.getX() == this.x && p.getY() == this.y);
    }

    public String toString(){
      String x = String.valueOf(this.getX());
      String y = String.valueOf(this.getY());
      return "(" + x + ", " + y + ")";
    }
}
