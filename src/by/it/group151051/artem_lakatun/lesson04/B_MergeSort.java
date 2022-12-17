package by.it.group151051.artem_lakatun.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];

        for(int i = 0; i < n; ++i)
        {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        return a;
    }
    public static void main(String[] args) throws FileNotFoundException
    {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        int[] var5 = result;
        int var6 = result.length;
        //long finishTime = System.currentTimeMillis();
        for(int var7 = 0; var7 < var6; ++var7) {
            int index = var5[var7];
            System.out.print("" + index + " ");
        }
    }


}
