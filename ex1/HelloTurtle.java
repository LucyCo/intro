import il.ac.huji.cs.intro.ex1.*;

public class HelloTurtle{
    public static void main(String[] args){
        IntroTurtle tzav = new IntroTurtle();
        tzav.counterclockwise(90);
        tzav.forward(100);
        tzav.clockwise(90);
        tzav.forward(50);
        tzav.counterclockwise(90);
        tzav.forward(80);
        tzav.penUp();
        tzav.back(80);
        tzav.penDown();
        tzav.clockwise(90);
        tzav.forward(50);
        tzav.counterclockwise(45);
        tzav.setPenSize(5);
        tzav.setPenColor(IntroTurtle.Color.RED);
        tzav.forward(120);
        tzav.penUp();
        tzav.back(120);
        tzav.penDown();
        tzav.setPenColor(IntroTurtle.Color.ORANGE);
        tzav.clockwise(2);
        tzav.forward(120);
        tzav.penUp();
        tzav.back(120);
        tzav.penDown();
        tzav.setPenColor(IntroTurtle.Color.YELLOW);
        tzav.clockwise(2);
        tzav.forward(120);
        tzav.penUp();
        tzav.back(120);
        tzav.penDown();
        tzav.setPenColor(IntroTurtle.Color.GREEN);
        tzav.clockwise(2);
        tzav.forward(120);
        tzav.penUp();
        tzav.back(120);
        tzav.penDown();
        tzav.setPenColor(IntroTurtle.Color.BLUE);
        tzav.clockwise(2);
        tzav.forward(120);
        tzav.penUp();
        tzav.back(120);
        tzav.penDown();
        tzav.setPenColor(IntroTurtle.Color.VIOLET);
        tzav.clockwise(2);
        tzav.forward(120);
        tzav.penUp();
        tzav.back(120);
        tzav.penDown();
        tzav.setPenColor(IntroTurtle.Color.WHITE);
        tzav.setPenSize(2);
        tzav.clockwise(125);
        tzav.forward(100);
        tzav.counterclockwise(90);
        tzav.forward(80);
        tzav.penUp();
        tzav.back(80);
        tzav.penDown();
        tzav.clockwise(90);
        tzav.forward(100);
        tzav.clockwise(90);
        tzav.forward(50);
        tzav.counterclockwise(90);
        tzav.forward(80);
        tzav.penUp();
        tzav.back(80);
        tzav.penDown();
        tzav.clockwise(90);
        tzav.forward(50);
        tzav.counterclockwise(135);
        tzav.forward(20);
        tzav.clockwise(120);
        tzav.forward(40);
        tzav.clockwise(120);
        tzav.forward(40);
        tzav.clockwise(120);
        tzav.forward(20);
        tzav.counterclockwise(135);
        tzav.forward(100);
        tzav.counterclockwise(90);
        tzav.forward(80);
        tzav.penUp();
        tzav.back(80);
        tzav.penDown();
        tzav.clockwise(180);
        tzav.hideTurtle();
    }
}