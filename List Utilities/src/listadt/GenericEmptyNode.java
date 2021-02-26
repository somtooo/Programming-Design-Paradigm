package listadt;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This represents an empty node of the generic list implementation.
 * 
 * @param <T> the type of element in this node
 */
public class GenericEmptyNode<T> implements GenericListAdtNode<T> {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public GenericListAdtNode<T> addFront(T object) {
    return new GenericElementNode<>(object, this);
  }

  @Override
  public GenericListAdtNode<T> addBack(T object) {
    return addFront(object);
  }

  @Override
  public GenericListAdtNode<T> add(int index, T object) throws IllegalArgumentException {
    if (index == 0) {
      return addFront(object);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  @Override
  public GenericListAdtNode<T> remove(T object) {
    return this; // cannot remove from nothing!
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }

  @Override
  public <R> GenericListAdtNode<R> map(Function<T, R> converter) {
    return new GenericEmptyNode<>();
  }

  @Override
  public GenericListAdtNode<T> filter(Predicate<T> predicate) {
    return new GenericEmptyNode<>();
  }

  @Override
  public T fold(T identity, BinaryOperator<T> accumulator) {
    return identity;
  }

  @Override
  public GenericListAdtNode<T> addFromArrayToEmptyList(T[] arr, int index) {
    if (this.count() > 0) {
      throw new IllegalStateException("List isn't empty");
    }
    return addFront(arr[arr.length - 1]).addFromArrayToEmptyList(arr, arr.length - 1);
  }

  @Override
  public GenericListAdtNode<T> addFromArrayToNonEmptyList(T[] arr, int index) {
    return addFromArrayToEmptyList(arr, index);
  }

  @Override
  public String toString() {
    return "";
  }
}
