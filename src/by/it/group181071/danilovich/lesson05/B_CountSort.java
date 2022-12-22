package by.it.group181071.danilovich.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.
При сортировке реализуйте метод со сложностью O(n)
Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort
{


    public static int[] Sort(int[] arr)
    {
        int min;
        int max = min = arr[0];

        for(int i = 1; i < arr.length; ++i)
        {
            if (arr[i] < min) {
                min = arr[i];
            }

            if (arr[i] > max)
            {
                max = arr[i];
            }
        }

        return Count(arr, min, max);
    }
    public static int[] Count(int[] arr, int min, int max)
    {
        int[] count = new int[max - min + 1];

        int idx;
        for(idx = 0; idx < arr.length; ++idx)
        {
            ++count[arr[idx] - min];
        }

        idx = 0;

        for(int i = 0; i < count.length; ++i)
        {
            for(int j = 0; j < count[i]; ++j)
            {
                arr[idx++] = i + min;
            }
        }

        return arr;
    }
    int[] countSort(InputStream stream) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] points = new int[n];

        for(int i = 0; i < n; ++i)
        {
            points[i] = scanner.nextInt();
        }

        Sort(points);
        return points;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result)
        {
            System.out.print(index+" ");
        }
    }

}