package list.br.com.cwi.list.linked;

import java.util.List;

public interface ILinkedList<T> {

    void addFirst(T value);

    T getFirst();

    T getLast();

    boolean isEmpty();

    List<T> list();

    void addLast(T value);

    void removeFirst();

    void add(int index, T value);

    void remove(int index);
}
