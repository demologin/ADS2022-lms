package by.it.group151051.birulia.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!


        class Inverse {

            public static int inversions;

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
                        inversions += (array1.length - index1);
                    }
                }

                while (index1 < array1.length)
                    merged[mergedIndex++] = array1[index1++];

                while (index2 < array2.length)
                    merged[mergedIndex++] = array2[index2++];

                return merged;
            }
        }

        Inverse.sort(a,0,a.length - 1);

        result = Inverse.inversions;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
