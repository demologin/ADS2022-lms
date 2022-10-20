package by.it.group151051.eremeev.lesson01;

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

        n = 1;
        m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());

        n = 999999999;
        m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        // максимальный период m * m + 1

        // период всегда четный для m > 2

        // минимальный период равен 6 для m > 2

        if (m == 1) {
          return 0;
        }

        if (m == 2) {
          // Последовательность 1-1-0
          if (n % 3 == 0) {
            return 0;
          } else {
            return 1;
          }
        }

        int period = 6;
        int maxPeriod = m * m + 1;
        long[] pizanoModulos = new long[maxPeriod];

        pizanoModulos[0] = 1;
        pizanoModulos[1] = 1;

        for (int i = 2; i < maxPeriod; i++) {
          pizanoModulos[i] = (pizanoModulos[i - 1] + pizanoModulos[i - 2]) % m;
        }

        int index = 0;
        while(true) {
          if (pizanoModulos[index] != pizanoModulos[index + period]) {
            index = 0;
            period += 2;
          }

          index++;

          if (index > period) {
            break;
          }

          if (index > maxPeriod - period) {
            break;
          }
        }

        int moduloIndex = ((int)n % period) - 1;

        return pizanoModulos[moduloIndex];
    }


}
