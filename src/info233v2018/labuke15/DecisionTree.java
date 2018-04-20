package info233v2018.labuke15;

import info233v2018.labuke7.Iterator;

public class DecisionTree<T> implements DecisionTreeInterface<T> {
    public DecisionTree(String answer) {

    }

    public DecisionTree(String question, DecisionTree no, DecisionTree yes) {

    }

    /**
     * Gets the data in the current node.
     *
     * @return The data object in the current node, or null if the current node is null.
     */
    @Override
    public T getCurrentData() {
        return null;
    }

    /**
     * Sets the data in the current node.
     * Precondition: The current node is not null.
     *
     * @param newData The new data object.
     */
    @Override
    public void setCurrentData(Object newData) {

    }

    /**
     * Sets the data in the children of the current, creating them if they do not exist.
     * Precondition: The current node is not null.
     *
     * @param responseForNo  The new data object for the left child.
     * @param responseForYes The new data object for the right child.
     */
    @Override
    public void setResponses(Object responseForNo, Object responseForYes) {

    }

    /**
     * Sees whether the current node contains an answer.
     *
     * @return True if the current node is a leaf, or false if it is a nonleaf.
     */
    @Override
    public boolean isAnswer() {
        return false;
    }

    /**
     * Sets the current node to its left child.
     * If the child does not exist, sets the current node to null.
     * Precondition: The current node is not null.
     */
    @Override
    public void advanceToNo() {

    }

    /**
     * Sets the current node to its left child.
     * If the child does not exist, sets the current node to null.
     * Precondition: The current node is not null.
     */
    @Override
    public void advanceToYes() {

    }

    /**
     * Makes the root of the tree the current node.
     */
    @Override
    public void resetCurrentNode() {

    }

    /**
     * Sets this binary tree to a new one-node binary tree.
     *
     * @param rootData The object that is the data for the new tree's root.
     */
    @Override
    public void setTree(Object rootData) {

    }

    /**
     * Sets this binary tree to a new binary tree.
     *
     * @param rootData  The object that is the data for the new tree's root.
     * @param leftTree  The left subtree of the new tree.
     * @param rightTree The right subtree of the new tree.
     */
    @Override
    public void setTree(Object rootData, BinaryTreeInterface leftTree, BinaryTreeInterface rightTree) {

    }

    @Override
    public T getRootData() {
        return null;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getNumberOfNodes() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator getPreorderIterator() {
        return null;
    }

    @Override
    public Iterator getPostOrderIterator() {
        return null;
    }

    @Override
    public Iterator getInorderIterator() {
        return null;
    }

    @Override
    public Iterator getLevelOrderIterator() {
        return null;
    }
}
