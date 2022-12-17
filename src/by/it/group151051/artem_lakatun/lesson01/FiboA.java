package by.it.group151051.artem_lakatun.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args)
    {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n)
    {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        return n < 2 ? n : this.calc(n - 1) + this.calc(n - 2);
    }


    public BigInteger slowA(Integer n)
    {
        if (n == 0)
        {
            return BigInteger.ZERO;
        } else
        {
            return n == 1 ? BigInteger.ONE : this.slowA(n - 1).add(this.slowA(n - 2));
        }
    }
}

