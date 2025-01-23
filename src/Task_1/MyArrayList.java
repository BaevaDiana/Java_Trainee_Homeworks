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

    // размер списка
    public int size() {
        return size;
    }

    //проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }

    // очистка списка
    public void clear() {
        int i;
        for (i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // изменение размера списка
    private void increaseCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length * 2;
            if (newCapacity < minCapacity) { // проверка, достаточно ли нового размера для добавления элемента
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    //добавление элемента в список
    public void add (T element) {
        increaseCapacity(size + 1);
        array[size++] = element;
    }


    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        //System.out.println(list);  // [10, 20, 30]
        System.out.println(list.size());  // 2
    }
}

