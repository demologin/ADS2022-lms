package by.it.group151051.senko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if(this.start == o.start) return 0;
            return this.start < o.start ? -1: 1;
        }
        public boolean hasIntersection(int point) {
            return this.start <= point && this.stop >= point;
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        // sort in place && search for the first element
        result[0] = Q_count(segments, 0, n-1, points[0]);

        // search for the rest
        for (int i = 1; i < m; i++) {
            int count = 0;
            for (Segment s : segments){
                if(s.start <= points[i] && s.stop >= points[i]) count++;
            }
            result[i] = count;
            i++;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    static int Q_count(Segment[] a, int left, int right, int point)
    {
        int count = 0;
        while (left <= right)
        {
            int pi = partition(a, left, right);
            if(a[pi].hasIntersection(point)) count++;
            if (pi - left < right - pi)
            {
                count += Q_count(a, left, pi - 1, point);
                left = pi + 1;
            }
            else
            {
                count += Q_count(a, pi + 1, right, point);
                right = pi - 1;
            }
        }
        return count;
    }

    static int partition(Segment[] a, int left, int right) {
        Segment pivot = a[left];
        while(left < right){
            left++;

            while(left <= right && a[left].compareTo(pivot) < 0){
                left++;
            }
            while(right >= left && a[right].compareTo(pivot) < 0){
                right--;
            }
            if(left < right){
                swap(a, left, right);
            }
        }
        return left;
    }
    static void swap(Segment[] a, int i, int j)
    {
        Segment temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/senko/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
