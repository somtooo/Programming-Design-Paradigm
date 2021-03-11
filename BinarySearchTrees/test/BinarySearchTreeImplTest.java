import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import bst.BinarySearchTree;
import bst.BinarySearchTreeImpl;



/** Tests the binary search tree implementation for correctness. */
public class BinarySearchTreeImplTest {

  public BinarySearchTree<Integer> bst;

  @org.junit.Before
  public void setUp() throws Exception {
    bst = new BinarySearchTreeImpl<>();
  }

  @org.junit.Test
  public void add() {
    bst.add(40);
    bst.add(60);
    bst.add(20);
    bst.add(10);
    bst.add(30);
    bst.add(50);
    bst.add(70);
    assertEquals("[10 20 30 40 50 60 70]", bst.toString());
  }

  @org.junit.Test
  public void size() {
    bst.add(40);
    bst.add(60);
    bst.add(20);
    bst.add(10);
    bst.add(30);
    bst.add(50);
    assertEquals(6, bst.size());
  }

  @org.junit.Test
  public void height() {
    bst.add(7);
    bst.add(3);
    bst.add(1);
    bst.add(10);
    bst.add(8);
    bst.add(12);
    bst.add(9);
    bst.add(13);
    assertEquals(4, bst.height());
  }

  @org.junit.Test
  public void present() {
    bst.add(7);
    bst.add(3);
    bst.add(1);
    bst.add(10);
    bst.add(8);
    bst.add(12);
    bst.add(9);
    bst.add(13);

    assertTrue(bst.present(13));
  }

  @org.junit.Test
  public void minimum() {
    bst.add(7);
    bst.add(3);
    bst.add(1);
    bst.add(10);
    bst.add(8);
    bst.add(12);
    bst.add(9);
    bst.add(13);
    assertEquals(Integer.valueOf(1), bst.minimum());
  }

  @org.junit.Test
  public void maximum() {
    bst.add(7);
    bst.add(3);
    bst.add(1);
    bst.add(10);
    bst.add(8);
    bst.add(12);
    bst.add(9);
    bst.add(13);
    assertEquals(Integer.valueOf(13), bst.maximum());
  }

  @org.junit.Test
  public void preOrder() {
    bst.add(80);
    bst.add(40);
    bst.add(120);
    bst.add(100);
    bst.add(90);
    bst.add(20);
    bst.add(60);
    bst.add(30);
    bst.add(50);
    bst.add(70);
    assertEquals("[80 40 20 30 60 50 70 120 100 90]", bst.preOrder());
  }

  @org.junit.Test
  public void inOrder() {
    bst.add(80);
    bst.add(40);
    bst.add(120);
    bst.add(100);
    bst.add(90);
    bst.add(20);
    bst.add(60);
    bst.add(30);
    bst.add(50);
    bst.add(70);
    bst.add(35);
    bst.add(36);
    assertEquals("[20 30 35 36 40 50 60 70 80 90 100 120]", bst.inOrder());
  }

  @org.junit.Test
  public void postOrder() {
    bst.add(80);
    bst.add(40);
    bst.add(120);
    bst.add(100);
    bst.add(90);
    bst.add(20);
    bst.add(60);
    bst.add(30);
    bst.add(50);
    bst.add(70);
    assertEquals("[30 20 50 70 60 40 90 100 120 80]", bst.postOrder());
  }

  @org.junit.Test
  public void testToString() {
    bst.add(40);
    bst.add(60);
    bst.add(20);
    bst.add(10);
    bst.add(30);
    bst.add(50);
    bst.add(70);
    assertEquals("[10 20 30 40 50 60 70]", bst.toString());
  }
}
