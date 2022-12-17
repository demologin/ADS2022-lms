package by.it.group151051.artem_lakatun.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(A_QSort a_qSort, int start, int stop){
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.start, o.start);
        }
    }
    public void QuickSort(Segment[] a, int l, int r) {
        int tam = l;
        int gcc = r;
        Segment x = a[(l + r) / 2];

        while(tam <= gcc) {
            while(x.compareTo(a[tam]) > 0) {
                ++tam;
            }

            while(a[gcc].compareTo(x) > 0) {
                --gcc;
            }

            if (tam <= gcc) {
                Segment temp = a[tam];
                a[tam] = a[gcc];
                a[gcc] = temp;
                ++tam;
                --gcc;
            }
        }

        if (tam < r) {
            this.QuickSort(a, tam, r);
        }

        if (gcc > l) {
            this.QuickSort(a, l, gcc);
        }

    }


    public int[] getAccessory(InputStream stream) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] result = new int[m];
        int[] p = new int[m];

        int i;
        for(i = 0; i < n; ++i)
        {
            segments[i] = new Segment(this, scanner.nextInt(), scanner.nextInt());
        }

        for(i = 0; i < m; ++i)
        {
            p[i] = scanner.nextInt();
        }

        this.QuickSort(segments, 0, n - 1);

        for(i = 0; i < m; ++i)
        {
            for(int j = 0; j < n && p[i] >= segments[j].start; ++j)
            {
                if (segments[j].start <= p[i] && segments[j].stop >= p[i]) {

                    int var10002 = result[i]++;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
