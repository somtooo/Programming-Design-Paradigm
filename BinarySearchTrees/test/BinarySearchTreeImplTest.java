import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import bst.BinarySearchTree;
import bst.BinarySearchTreeImpl;
import org.junit.Before;
import org.junit.Test;




/** Tests the binary search tree implementation for correctness. */
public class BinarySearchTreeImplTest {

  public BinarySearchTree<Integer> bst;
  BinarySearchTree<Integer> tree;

  @Before
  public void setUp() throws Exception {
    bst = new BinarySearchTreeImpl<>();
    tree = new BinarySearchTreeImpl<>();
    bst.add(7);
    bst.add(3);
    bst.add(1);
    bst.add(10);
    bst.add(8);
    bst.add(12);
    bst.add(9);
    bst.add(13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNull() {
    tree.add(40);
    tree.add(null);
  }

  @Test
  public void addWhenEmpty() {
    tree.add(40);
    assertEquals("[40]", tree.toString());
  }

  @Test
  public void add() {
    assertEquals("[1 3 7 8 9 10 12 13]", bst.toString());
  }

  @Test
  public void addLeft() {
    tree.add(40);
    tree.add(30);
    assertEquals("[30 40]", tree.toString());
  }

  @Test
  public void addRight() {
    tree.add(40);
    tree.add(50);
    assertEquals("[40 50]", tree.toString());
  }

  @Test
  public void addLeftMoreThanOnce() {
    tree.add(40);
    tree.add(30);
    tree.add(20);
    tree.add(10);
    assertEquals("[10 20 30 40]", tree.toString());
  }

  @Test
  public void addRightMoreThanOnce() {
    tree.add(40);
    tree.add(50);
    tree.add(60);
    tree.add(70);
    assertEquals("[40 50 60 70]", tree.toString());
  }

  @Test
  public void addLeftThenAddRight() {
    tree.add(40);
    tree.add(30);
    tree.add(60);
    assertEquals("[30 40 60]", tree.toString());
  }

  @Test
  public void addRightThenAddLeft() {
    tree.add(40);
    tree.add(60);
    tree.add(30);
    assertEquals("[30 40 60]", tree.toString());
  }

  @Test
  public void addLeftThenAddRightMoreThanOnce() {
    tree.add(40);
    tree.add(30);
    tree.add(20);
    tree.add(60);
    tree.add(70);
    assertEquals("[20 30 40 60 70]", tree.toString());
  }

  @Test
  public void addRightThenAddLeftMoreThanOnce() {
    tree.add(40);
    tree.add(60);
    tree.add(70);
    tree.add(30);
    tree.add(20);
    assertEquals("[20 30 40 60 70]", tree.toString());
  }

  @Test
  public void addDuplicates() {
    tree.add(40);
    tree.add(60);
    tree.add(70);
    tree.add(30);
    tree.add(20);
    tree.add(70);
    tree.add(30);
    tree.add(20);
    assertEquals("[20 30 40 60 70]", tree.toString());
  }

  @Test
  public void size() {
    assertEquals(8, bst.size());
    bst.add(40);
    bst.add(20);
    assertEquals(10, bst.size());
  }

  @Test
  public void sizeEmpty() {
    assertEquals(0, tree.size());
  }

  @Test
  public void height() {
    tree.add(7);
    assertEquals(1, tree.height());
  }

  @Test
  public void heightNestedTree() {
    assertEquals(4, bst.height());
  }

  @Test
  public void heightEmpty() {
    assertEquals(0, tree.height());
  }

  @Test
  public void present() {
    assertTrue(bst.present(13));
    assertFalse(bst.present(0));
    assertFalse(tree.present(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void presentNull() {
    assertTrue(bst.present(13));
    assertFalse(bst.present(null));
  }

  @Test
  public void minimum() {
    assertEquals(Integer.valueOf(1), bst.minimum());
  }

  @Test
  public void minimumLeft() {
    tree.add(40);
    tree.add(20);
    tree.add(10);
    tree.add(5);
    assertEquals(Integer.valueOf(5), tree.minimum());
  }

  @Test
  public void minimumRight() {
    tree.add(40);
    tree.add(50);
    tree.add(60);
    tree.add(70);
    assertEquals(Integer.valueOf(40), tree.minimum());
  }

  @Test
  public void minimumOneNode() {
    tree.add(40);
    assertEquals(Integer.valueOf(40), tree.minimum());
  }

  @Test
  public void minimumEmpty() {
    assertNull(tree.minimum());
  }


  @Test
  public void maximum() {
    assertEquals(Integer.valueOf(13), bst.maximum());
  }

  @Test
  public void maximumLeft() {
    tree.add(40);
    tree.add(20);
    tree.add(10);
    tree.add(5);
    assertEquals(Integer.valueOf(40), tree.maximum());
  }

  @Test
  public void maximumRight() {
    tree.add(40);
    tree.add(50);
    tree.add(60);
    tree.add(70);
    assertEquals(Integer.valueOf(70), tree.maximum());
  }

  @Test
  public void maximumOneNode() {
    tree.add(40);
    assertEquals(Integer.valueOf(40), tree.maximum());
  }

  @Test
  public void maximumEmpty() {
    assertNull(tree.maximum());
  }


  @Test
  public void preOrder() {
    assertEquals("[7 3 1 10 8 9 12 13]", bst.preOrder());
  }

  @Test
  public void preOrderSpecialCase() {
    bst = new BinarySearchTreeImpl<>();
    bst.add(4);
    bst.add(6);
    bst.add(2);
    bst.add(1);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    tree = new BinarySearchTreeImpl<>();
    tree.add(6);
    tree.add(4);
    tree.add(2);
    tree.add(1);
    tree.add(3);
    tree.add(5);
    tree.add(7);
    assertEquals(tree.inOrder(), bst.inOrder());
  }

  @Test
  public void preOrderEmpty() {
    assertEquals("[]", tree.preOrder());
  }

  @Test
  public void inOrder() {
    assertEquals("[1 3 7 8 9 10 12 13]", bst.inOrder());
  }

  @Test
  public void inOrderSpecialCase() {
    bst = new BinarySearchTreeImpl<>();
    bst.add(4);
    bst.add(6);
    bst.add(2);
    bst.add(1);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    tree = new BinarySearchTreeImpl<>();
    tree.add(6);
    tree.add(4);
    tree.add(2);
    tree.add(1);
    tree.add(3);
    tree.add(5);
    tree.add(7);
    assertEquals(tree.inOrder(), bst.inOrder());
    assertNotEquals(tree.postOrder(), bst.postOrder());
  }

  @Test
  public void postOderSpecialCase() {
    bst = new BinarySearchTreeImpl<>();
    bst.add(4);
    bst.add(6);
    bst.add(2);
    bst.add(1);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    tree = new BinarySearchTreeImpl<>();
    tree.add(6);
    tree.add(4);
    tree.add(2);
    tree.add(1);
    tree.add(3);
    tree.add(5);
    tree.add(7);
    assertEquals("[1 3 2 5 7 6 4]", bst.postOrder());
    assertEquals("[1 3 2 5 4 7 6]", tree.postOrder());
    assertEquals(tree.inOrder(), bst.inOrder());
  }

  @Test
  public void inOrderEmpty() {
    assertEquals("[]", tree.inOrder());
  }

  @Test
  public void postOrder() {
    assertEquals("[1 3 9 8 13 12 10 7]", bst.postOrder());
  }

  @Test
  public void postOrderEmpty() {
    assertEquals("[]", tree.postOrder());
  }

  @Test
  public void testToString() {
    assertEquals("[1 3 7 8 9 10 12 13]", bst.toString());
  }
}
