package by.it.group151051.artem_lakatun.lesson01;

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

    public static void main(String[] args)
    {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m)
    {
        int k = 6 * m + 1;
        long[] arr = new long[k];
        arr[0] = 0L;
        arr[1] = 1L;
        long total = arr[0] + arr[1];

        int i;
        for (i = 1; (arr[i] != 0L || total % (long) m != 0L) && i < k; total += arr[i])
        {
            ++i;
            arr[i] = arr[i - 1] + arr[i - 2];
            arr[i] %= (long) m;
        }

        if (total % (long) m != 0L && arr[i] != 0L)
        {
            return arr[i];
        } else {
            int j = (int) (n % (long) i);
            return arr[j];
        }
    }
}

