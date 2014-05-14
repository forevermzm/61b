import java.util.ArrayList;

public class Y extends X{
    public static final int x = 6;

    @Override
    public int getX(){
        return x;
    }

    public static void main(String[] args){
        Y y = new Y();
        X x = new X();

        System.out.println("Y is: " + y.super.getX());
        System.out.println("X is: " + ((Y) x).getX());
    }
}