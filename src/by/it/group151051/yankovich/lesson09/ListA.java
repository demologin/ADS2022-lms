package by.it.group151051.yankovich.lesson09;

import java.util.Arrays;

public class ListA<T> {
    private int size = 2;
    private T[] list;
    private int count = 0;

    public ListA(){
        list = (T[]) new Object[size];
    }

    public void add(T value){
        resize(count);
        list[count++] = value;
    }

    public void remove(int index){
        if (index < 0 || index > count){
            return;
        }
        count--;
        for (int i=index; i<count; i++){
            list[i] = list[i+1];
        }
        list[count] = null;
    }

    public T get(int index){
        if (index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    public void resize(int cnt){
        if (cnt < size){
            return;
        }
        size *= 1.5;
        list = Arrays.copyOf(list, size);
    }
}