package info233v2018.labuke15;

import javax.print.DocFlavor;

public class GuessingGame {
    private DecisionTreeInterface<String> tree;

    public GuessingGame(String question, String noAnswer, String yesAnswer) {
        DecisionTree<String> no = new DecisionTree<>(noAnswer);
        DecisionTree<String> yes = new DecisionTree<>(yesAnswer);
        tree = new DecisionTree<>(question, no, yes);
    }

    public void play() {
        tree.resetCurrentNode();
        while (!tree.isAnswer()) {
            //Ask current question
            System.out.println(tree.getCurrentData());
            if (Client.isUserResponseYes()) {
                tree.advanceToYes();
            } else {
                tree.advanceToNo();
            }
        }
            assert tree.isAnswer();

            System.out.println("My guess is " + tree.getCurrentData() + ". Am i right?");
            if (Client.isUserResponseYes()) {
                System.out.println("I win");
            } else {
                learn();
            }
    }

    private void learn() {

    }

    private static class Client {
        public static boolean isUserResponseYes() {
            return true;
        }
    }
}
