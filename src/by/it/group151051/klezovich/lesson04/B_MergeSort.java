package by.it.group151051.klezovich.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException
    {
        //подготовка к чтению данных

        Scanner scanner = new Scanner(stream);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива

        int n = scanner.nextInt();

        //сам массив

        int[] a = new int[n];
        for (int i = 0; i < n; i++)
        {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        MergeSort(a, n);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public void MergeSort(int[] arr ,int length)
    {
        if (length < 2)
            return;

        int middle = length / 2;
        int[] b = Arrays.copyOf(arr, middle);
        int[] c = Arrays.copyOfRange(arr, middle, length);

        MergeSort(b, middle);
        MergeSort(c, length - middle);
        Merge(arr, b, c, middle, length - middle);
    }

    public void Merge(int[] a, int[] first, int[] second, int n1, int n2)
    {
        int i = 0;
        int j = 0;
        int k = 0;

        while ((i < n1) && (j < n2))
        {
            if (first[i] <= second[j])
            {
                a[k] = first[i];
                k++;
                i++;
            }
            else
            {
                a[k] = second[j];
                k++;
                j++;
            }
        }

        while (i < n1)
        {
            a[k] = first[i];
            k++;
            i++;
        }

        while (j < n2)
        {
            a[k] = second[j];
            k++;
            j++;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
