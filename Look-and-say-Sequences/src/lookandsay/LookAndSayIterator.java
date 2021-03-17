package lookandsay;

import java.util.NoSuchElementException;

public class LookAndSayIterator<T> implements RIterator<T> {

    @Override
    public T prev() throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
