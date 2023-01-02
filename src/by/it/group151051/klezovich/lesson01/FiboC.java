package by.it.group151051.klezovich.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        long[] arrayfib = new long[6 * m + 1];

        int i = 1;
        arrayfib[0] = 0;
        arrayfib[1] = 1;

        long result = arrayfib[0] + arrayfib[1];

        while (((arrayfib[i] != 0) || (result % m != 0)) && (i < (6 * m + 1))) {
            i++;
            arrayfib[i] = arrayfib[i - 1] + arrayfib[i - 2];
            arrayfib[i] = arrayfib[i] % m;
            result = result + arrayfib[i];
        }

        if ((result % m != 0) && (arrayfib[i] != 0))
            return arrayfib[i];
        else
        {
            int exep = (int) (n % i);
            return arrayfib[exep];
        }
    }
}

