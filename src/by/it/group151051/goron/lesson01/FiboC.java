package by.it.group151051.goron.lesson01;

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
        int n = 1;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Integer> period = new ArrayList<Integer>(2);
        period.add(0); //Первые 2 значения периода Пизано 0 и 1
        period.add(1);
        int k = 2; //Количество чисел в периоде Пизано
        boolean period_is_found = false;
        int i = 2;

        while (!period_is_found) {
            period.add((period.get(i-1) + period.get(i-2)) % m); //Добавление следующего числа периода в конец массива
            ++k;
            if (period.get(i) == 1 && period.get(i-1) == 0) //Посл. эл. - 1, предп. эл. - 0, значит период найден
                period_is_found = true; //Период найден, цикл прерывается
            else
                ++i; //Переход к поиску следующего числа в периоде
        }
        return period.get((int)n % (k - 2));
    }

}

