package Task_1;

import java.util.Arrays;

public class MyArrayList<T> {
    private Object[] array;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 10;

    // конструктор
    public MyArrayList() {
        array = new Object[INITIAL_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        int i;
        for (i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    private void ensuranceCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length * 2;
            if (newCapacity < minCapacity) { // проверка, достаточно ли нового размера для добавления элемента
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    public void add (T element) {
        ensuranceCapacity(size + 1);
        array[size++] = element;
    }

    private void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void remove (int index){
        checkIndex(index);
        int countElem = size - index - 1;
        if (countElem > 0) {
            System.arraycopy(array, index + 1, array, index, countElem);
        }
        array[--size] = null;
    }

    public T get(int index){
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i;
        for (i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        list.add(20);
        list.add(0);
        list.add(5);
        list.add(100);
        System.out.println("Размер исходного списка: " + list.size());
        System.out.println("Исходный список: " + list);
        list.remove(3);
        int el1 = list.get(1);
        System.out.println("Элемент под индексом 1: " + el1);
        list.add(52);
        list.add(39);
        System.out.println("Размер нового списка: " + list.size());
        System.out.println("Новый список: " +list);
    }
}

