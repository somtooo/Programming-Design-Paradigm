package bst;

/**
 * This is the implementation of a generic binary search tree. Specifically it implements the
 * BinarySearchTree interface.
 *
 * @param <T> the type of element in the list.
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

  private BinarySearchNode<T> root;

  /** Default constructor. */
  public BinarySearchTreeImpl() {
    root = new EmptyNode<>();
  }

  @Override
  public void add(T data) {
    nullChecker(data);
    root = root.add(data);
  }

  @Override
  public int size() {
    return root.size();
  }

  @Override
  public int height() {
    return root.height();
  }

  @Override
  public boolean present(T data) {
    nullChecker(data);
    return root.present(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  @Override
  public String preOrder() {
    return "[" + root.preOrder() + "]";
  }

  @Override
  public String inOrder() {
    return "[" + root.inOrder() + "]";
  }

  @Override
  public String postOrder() {
    return "[" + root.postOrder() + "]";
  }

  @Override
  public String toString() {
    return "[" + root.toString() + "]";
  }

  /**
   * Checks if data passed is null.
   * @param data the data to check if null.
   */
  private void nullChecker(T data) {
    if (data == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
  }
}
