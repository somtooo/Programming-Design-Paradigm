package listadt;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/** Implementation of utilities for the ListADT list. */
public class ListADTUtilities {

  /**
   * Create a new list that contains all of the specified elements in the same order as they
   * appeared in the specified array.
   *
   * @param <T> the type of element in the list.
   * @param data the data to store in the list
   * @return a new list that contains all of the specified elements
   */
  public static <T> ListADT<T> toList(T[] data) {
    ListADT<T> list = new ListADTImpl<>();
    GenericListAdtNode<T> head = ((ListADTImpl<T>) list).getHead();
    ((ListADTImpl<T>) list).setHead(head.addFromArrayToEmptyList(data, 0));

    return list;
  }

  /**
   * Adds all of the specified elements to the specified list. Elements to be added may be specified
   * individually or as an array and should be added to the end of the list.
   *
   * @param <T> the type of elements to add to the list
   * @param list the list to insert the elements into
   * @param elements the elements to insert into list
   * @throws IllegalArgumentException if elements contains one or more null values
   */
  @SafeVarargs
  public static <T> void addAll(ListADT<T> list, T... elements) {
    GenericListAdtNode<T> head = ((ListADTImpl<T>) list).getHead();
    ((ListADTImpl<T>) list).setHead(head.addFromArrayToNonEmptyList(elements, 0));
  }

  /**
   * Returns the number of elements in the specified list equal to the specified element. More
   * formally, returns the number of elements in the list such that <code>
   * (o == null ? e == null : o.equals(e))</code>.
   *
   * @param <T> the type of elements in the list.
   * @param list the list to search
   * @param element the element to search for
   * @return the number of elements in the list
   */
  public static <T> int frequency(ListADT<T> list, T element) {
    if (list == null) {
      throw new IllegalArgumentException("Null not allowed");
    }

    ListADT<T> filteredList = list.filter((T value) -> Objects.equals(value, element));
    return filteredList.getSize();
  }

  /**
   * Returns true if the two lists have no elements in common.
   *
   * @param <T> the type of elements in the lists.
   * @param one the first list
   * @param two the second list
   * @return if the two lists have no elements in common
   * @throws IllegalArgumentException if either list is null or if either list contains a null
   *     element
   */
  public static <T> boolean disjoint(ListADT<T> one, ListADT<T> two) {
    if (one == null | two == null) {
      throw new IllegalArgumentException("Null not allowed");
    }

    final ListADT<Boolean> bool;
    AtomicBoolean answer = new AtomicBoolean(true);
    one.map(
        (T first) -> {
          if (first == null) {
            throw new IllegalArgumentException("Null not allowed");
          }
          two.map(
              (T second) -> {
                if (second == null) {
                  throw new IllegalArgumentException("Null not allowed");
                }
                if (first.equals(second)) {
                  answer.set(false);
                }
                return answer;
              });
          return true;
        });

    return answer.get();
  }

  /**
   * Returns true if the two lists are equal. Two lists are equal if they have the same elements in
   * the same order.
   *
   * @param <T> the type of elements in the lists.
   * @param one the first list
   * @param two the second list
   * @return true if the two lists have the same elements in the same order
   * @throws IllegalArgumentException if either list is null or if either collection contains a null
   *     element
   */
  public static <T> boolean equals(ListADT<T> one, ListADT<T> two) {
    if (one == null | two == null) {
      throw new IllegalArgumentException("Null not allowed");
    } else if (one.getSize() != two.getSize()) {
      return false;
    }

    AtomicInteger count = new AtomicInteger();
    return one.map(
        (s) -> {
            if (s == null) {
              throw new IllegalArgumentException(" No null allowed");
            }
            if (two.get(count.get()) == null) {
              throw new IllegalArgumentException("No null allowed");
            }
            boolean condition = s.equals(two.get(count.get()));
            count.getAndIncrement();
            return condition;
        })
      .fold(true, (Boolean a, Boolean b) -> a & b);
  }
}
