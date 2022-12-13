package by.it.group151051.goron.lesson04;

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

    public static int merge_inv_cnt(int[] arr, int[] aux, int low, int midInd, int high) {
        int auxInd = low, leftInd = low, rightInd = midInd + 1;
        int inversionCount = 0;

        // Пока есть элементы в левой и правой части массива
        while (leftInd <= midInd && rightInd <= high) {
            if (arr[leftInd] <= arr[rightInd]) {
                aux[auxInd++] = arr[leftInd++];
            }
            else {
                aux[auxInd++] = arr[rightInd++];
                // Т.к. левая часть массива отсортирована, то все её элементы после arr[leftInd],
                // а также центральный элемент образуют инверсии с элементом arr[rightInd] правой части
                inversionCount += (midInd - leftInd + 1);
            }
        }

        // Копируем оставшиеся элементы левой части, если в правой части элементов нет
        while (leftInd <= midInd) {
            aux[auxInd++] = arr[leftInd++];
        }

        // Отразим отсортированный подмассив в основном массиве
        for (auxInd = low; auxInd <= high; auxInd++) {
            arr[auxInd] = aux[auxInd];
        }

        return inversionCount;
    }

    public static int mergesort(int[] arr, int[] aux, int low, int high) {
        if (high <= low) {  // Если размер части <= 1
            return 0;
        }

        // Поиск центрального элемента
        int mid = (low + high) >>> 1;
        int inversionCount = 0;

        // Разделить/объединить левую часть
        inversionCount += mergesort(arr, aux, low, mid);
        // Разделить/объединить правую часть
        inversionCount += mergesort(arr, aux, mid + 1, high);

        // Затем половины объединяются и подсчитываются инверсии
        inversionCount += merge_inv_cnt(arr, aux, low, mid, high);

        return inversionCount;
    }

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
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        int[] aux = a.clone();
        return mergesort(a, aux, 0, a.length - 1);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/goron/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
