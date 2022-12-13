package by.it.group151051.goron.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозрастающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] X = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            X[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result;
        int[] P = new int[n];   // Массив индексов предпоследних элементов для наидлиннейшей возрастающей подпоследовательности, оканчивающейся в i-й позиции
        int[] M = new int[n+1]; // Массив индексов наименьших последних значений полученных подпоследовательностей длины i
        // (напр.: массив = {1, 3, 2, 5}, М[2] = 2, т.к. последний элем. подпослед. {1, 2}, меньше чем у подпослед. {1, 3} )
        int L = 0; // Текущая длина подпоследовательности
        for (int i = n-1; i >= 0; i--) { // Просматривая массив с конца получим неубывающую последовательность
            int low = 1;
            int high = L;

            // Бинарный поиск среди последних элементов найденных подпоследовательностей минимального элемента,
            // который больше чем i-й элемент исходного массива.
            // Это даст нам последний элемент найденной раньше подпоследовательности, с которой будем работать дальше
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (X[M[mid]] > X[i])
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            int newL = low; // Новая длина подпоследовательности
            // В позицию newL найденной подпоследовательности записывается элемент X[i], который меньше чем элемент в позиции newL
            P[i] = M[newL - 1]; // Это осуществляется путем изменения хранящегося индекса в массивах P и M
            M[newL] = i;

            if (newL > L) // Если длина найденной подпоследовательности больше чем предыдущей, то она будет самой длинной на данный момент
                L = newL;
        }

        // В начале массива P находится индекс предпоследнего элемента неубывающей подпоследовательности
        // Или индекс следующего элемента искомой невозрастающей последовательности
        int[] indexesOfSubSeq = new int[L];
        int k = M[L];
        for (int i = 0; i < L; i++) {
            indexesOfSubSeq[i] = k + 1;
            k = P[k];
        }

        // Вывод массива индексов на экран
        for (int i = 0; i < indexesOfSubSeq.length; i++) {
            System.out.print(indexesOfSubSeq[i] + " ");
        }
        System.out.println();

        result = L;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
}