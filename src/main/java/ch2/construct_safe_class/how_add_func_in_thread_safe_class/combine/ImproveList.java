package ch2.construct_safe_class.how_add_func_in_thread_safe_class.combine;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Author:Tamako
 * @Date:2024/3/25 16:43
 * @Description:组合方式实现
 */
public class ImproveList<E> implements List<E> {
    private final List<E> list;

    public ImproveList(List<E> list) {
        this.list = list;
    }

    @Override
    public synchronized int size() {
        return 0;
    }

    @Override
    public synchronized boolean isEmpty() {
        return false;
    }

    @Override
    public synchronized boolean contains(Object o) {
        return false;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return null;
    }

    @Override
    public synchronized Object[] toArray() {
        return new Object[0];
    }

    @Override
    public synchronized <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public synchronized boolean add(E e) {
        return false;
    }

    @Override
    public synchronized boolean remove(Object o) {
        return false;
    }

    @Override
    public synchronized boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public synchronized boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public synchronized boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public synchronized void clear() {

    }

    @Override
    public synchronized E get(int index) {
        return null;
    }

    @Override
    public synchronized E set(int index, E element) {
        return null;
    }

    @Override
    public synchronized void add(int index, E element) {

    }

    @Override
    public synchronized E remove(int index) {
        return null;
    }

    @Override
    public synchronized int indexOf(Object o) {
        return 0;
    }

    @Override
    public synchronized int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public synchronized ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public synchronized ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public synchronized List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
