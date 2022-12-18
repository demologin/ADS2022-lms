package by.it.group151051.birulia.lesson04;

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

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием


        class MergeSort {

            public static int[] sort(int[] array, int startIndex, int endIndex) {
                if (startIndex == endIndex) {
                    return new int[] { array[startIndex] };
                }
                int middleIndex = (int) Math.floor(startIndex + (endIndex - startIndex) / 2);

                return merge(sort(array,startIndex,middleIndex), sort(array,middleIndex + 1, endIndex));
            }

            static int[] merge(int[] array1, int[] array2) {
                int index1 = 0;
                int index2 = 0;
                int[] merged = new int[array1.length + array2.length];
                int mergedIndex = 0;

                while (index1 < array1.length && index2 < array2.length) {

                    if (array1[index1] <= array2[index2])
                        merged[mergedIndex++] = array1[index1++];
                    else {
                        merged[mergedIndex++] = array2[index2++];
                    }
                }

                while (index1 < array1.length)
                    merged[mergedIndex++] = array1[index1++];

                while (index2 < array2.length)
                    merged[mergedIndex++] = array2[index2++];

                return merged;
            }
        }

        return MergeSort.sort(a, 0,a.length - 1);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
