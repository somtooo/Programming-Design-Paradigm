package listadt;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

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
    // TODO: Implement me!
    return list;
  }

  public static void main(String[] args) {
      String[] data = new String[]{"1","2","3"};
      System.out.println(ListADTUtilities.toList(data).toString());
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
    for (T value : elements) {
      if (value == null) {
        throw new IllegalArgumentException("No nulls");
      }
      list.addBack(value);
    }
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
    ListADT<T> filteredList = list.filter((T value) -> value.equals(element));
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

    return one.map(
            (s) -> {
              if (s == null) {
                throw new IllegalArgumentException(" No null allowed");
              }
              return String.valueOf(s);
            })
        .fold("", (String firstElement, String secondElement) -> firstElement + secondElement)
        .equals(
            two.map(
                    (s) -> {
                      if (s == null) {
                        throw new IllegalArgumentException(" No null allowed");
                      }
                      return String.valueOf(s);
                    })
                .fold(
                    "",
                    (String firstElement, String secondElement) -> firstElement + secondElement));
  }
}
