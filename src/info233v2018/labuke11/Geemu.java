package info233v2018.labuke11;

import java.util.Random;

public class Geemu {
    private Random rngeesus = new Random();
    private int range;
    private int ans;
    private int tries;
    private boolean gameEnded;

    public static void main(String[] args) {
        Geemu gem = new Geemu(5);
        System.out.println(gem.getAns());
    }

    private Geemu(int tries) {
        range = 100;
        ans = rngeesus.nextInt(range);
        this.tries = tries;
        gameEnded = false;
    }

    Geemu(int n, int tries) {
        range = n;
        ans = rngeesus.nextInt(range);
        this.tries = tries;
        gameEnded = false;
    }

    public String guess(int guess) {
        String result = "";

        if (tries > 0) {
            if (guess == ans) {
                result = "Correct";
                gameEnded = true;
            } else if (guess < ans) {
                result = "Guess: " + guess + ", Guess higher. " + tries + " left.";
                tries--;
                if (tries == 0) {
                    result = "Guess: " + guess + " is incorrect, Game Over";
                    gameEnded = true;
                }
            } else if (guess > ans) {
                result = "Guess: " + guess + ", Guess lower. " + tries + " left.";
                tries--;
                if (tries == 0) {
                    result = "Guess: " + guess + " is incorrect, Game Over";
                    gameEnded = true;
                }
            } else if (guess > range || guess < 0) {
                result = "Guess: " + guess + ", Guess between 0 and " + range;
            }
        } else {
            result = "No more attempts";
        }

        return result;
    }

    private int getAns() {
        return ans;
    }

    public int getRange() {
        return range;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }
}
