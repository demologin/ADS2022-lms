package by.it.group151051.birulia.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        ArrayList<Integer> pisanoMods = new ArrayList<>();

        pisanoMods.add(0);
        pisanoMods.add(1);

        int i = 2;

        // Период пизано всегда начинается с 0,1.
        // Собираем период Пизано в список пока не начнется новый период 0,1
        while (!( pisanoMods.get(i - 2) == 0 && pisanoMods.get(i - 1) == 1 ) || i == 2) {
            pisanoMods.add((pisanoMods.get(i - 2) + pisanoMods.get(i - 1)) % m);
            i++;
        }

        // исключим последние 0,1 из следующего периода
        int pisanoPeriod = i - 2;

        int index = (int)(n % pisanoPeriod);

        return pisanoMods.get(index);
    }
}