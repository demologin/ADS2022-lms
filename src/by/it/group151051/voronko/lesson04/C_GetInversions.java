package by.it.group151051.voronko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
        int[] a_duplicate = Arrays.copyOf(a, n);
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        if(n > 0){
            result = mergesort(a, a_duplicate, 0, n-1);
        }
//        System.out.println(result);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static int merge(int[] arr, int[] aux, int low, int mid, int high)
    {
        int k = low, i = low, j = mid + 1;
        int inversionCount = 0;

        while (i <= mid && j <= high)
        {
            if (arr[i] <= arr[j]) {
                aux[k++] = arr[i++];
            }
            else {
                aux[k++] = arr[j++];
                inversionCount += (mid - i + 1);
            }
        }

        while (i <= mid) aux[k++] = arr[i++];
        for (i = low; i <= high; i++) arr[i] = aux[i];

        return inversionCount;
    }

    public static int mergesort(int[] arr, int[] aux, int low, int high)
    {
        if (high <= low) return 0;
        int mid = (low + ((high - low) >> 1));
        int inversionCount = 0;

        inversionCount += mergesort(arr, aux, low, mid);
        inversionCount += mergesort(arr, aux, mid + 1, high);
        inversionCount += merge(arr, aux, low, mid, high);

        return inversionCount;
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
