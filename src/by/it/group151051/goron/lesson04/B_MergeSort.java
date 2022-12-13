package by.it.group151051.goron.lesson04;

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

    private static void merge(int[] source, int leftStart, int rightStart, int size,
                              int[] destination, int destStart) {

        int leftIndex = leftStart;   // Индексы левого и правого подмассива
        int rightIndex = rightStart;

        int leftEnd = Math.min(leftStart + size, source.length);  // Индексы последних элементов в подмассивах
        int rightEnd = Math.min(rightStart + size, source.length);

        if (leftStart + size > source.length) {                // Если получена только левая часть, а правой нет
            for (int i = leftStart; i < leftEnd; i++) {
                destination[i] = source[i];             // То в итоговый массив, записывается вся левая часть
            }
            return;
        }

        int iterationCount = (leftEnd - leftStart) + (rightEnd - rightStart); // Сумма всех итераций по левой и правой частях

        for (int i = destStart; i < destStart + iterationCount; i++) {  // Слияние отсортированных массивов
            if (leftIndex < leftEnd && (rightIndex >= rightEnd || source[leftIndex] < source[rightIndex])) {
                destination[i] = source[leftIndex];
                leftIndex++;
            }
            else {
                destination[i] = source[rightIndex];
                rightIndex++;
            }
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        int[] source = a.clone();
        int size = 1; // Размер объединяемых массивов
        while (size < n) {
            for (int i = 0; i < n; i += size * 2) {
                merge(source, i,i + size, size, a, i);
            }
            source = a.clone(); // В следующей итерации для объединения будет использоваться частично отсортированный массив a
            // Размер объединяемых массивов увеличивается в 2 раза,
            // т.к. их размер складывается из двух предыдущих объединенных массивов размером 1x1, 2x2, 4x4
            size *= 2;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return source;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/goron/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
