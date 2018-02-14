package info233v2018.labuke5;

public class Recursion {
    public void displayRowOfIntegers(int x, int y){
        String out = "";
        if (y > 0) {
            out += x + "" + x;
            displayRowOfIntegers(x, y - 1);
        }
        System.out.print(out);
    }
}
