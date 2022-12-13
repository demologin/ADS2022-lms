package by.it.group151051.goron.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {


    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
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

        int[] divSubSeq = new int[n]; // Массив для хранения длин всех кратных подпоследовательностей
        divSubSeq[0] = 1;
        for (int i = 1; i < n; i++) {  // Снизу вверх находим длины всех кратных подпоследовательностей
            divSubSeq[i] = 1;
            for (int j = 0; j < i; j++) {
                if (X[i] % X[j] == 0) {            // Если текущий элемент делится без остатка на предыдущий
                    divSubSeq[i] = divSubSeq[j]+1; // То текущий элемент образует подпоследовательность со всеми элементами
                                                   // подпоследовательности предыдущего элемента
                }
            }
        }

        // Длиной наибольшей кратной подпоследовательности будет максимальное число в массиве длин всех подпоследовательностей
        int longestDivSubSeq = divSubSeq[0];
        for (int i = 1; i < n; i++) {
            if (divSubSeq[i] > longestDivSubSeq)
                longestDivSubSeq = divSubSeq[i];
        }

        result = longestDivSubSeq;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/goron/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}