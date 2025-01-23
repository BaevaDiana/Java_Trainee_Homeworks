import java.util.Arrays;

public class MyArrayList<T> {
    private Object[] array;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 10;

    // конструктор
    public MyArrayList() {
        array = new Object[INITIAL_CAPACITY];
    }


    // Добавление элемента в конец
    public void add(T element) {
        ensureCapacity(size + 1);
        array[size++] = element;
    }

    // Получение элемента по индексу
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    // Удаление элемента по индексу
    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null; // Убираем ссылку на удалённый элемент
    }

    // Изменение размера массива, если нужно
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    // Проверка индекса
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Получение размера списка
    public int size() {
        return size;
    }

    // Проверка, пустой ли список
    public boolean isEmpty() {
        return size == 0;
    }

    // Очистка списка
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // Печать списка
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
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
        list.add(30);
        System.out.println(list);  // [10, 20, 30]
        list.remove(1);
        System.out.println(list);  // [10, 30]
        System.out.println(list.get(0));  // 10
        System.out.println(list.size());  // 2
    }
}

