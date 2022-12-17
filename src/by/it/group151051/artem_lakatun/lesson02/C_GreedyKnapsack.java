package by.it.group151051.artem_lakatun.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack
{
    private static class Item implements Comparable<Item>
    {
        int cost;
        int weight;

        Item(int cost, int weight)
        {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString()
        {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            return o.cost / o.weight - this.cost / this.weight;
        }
    }

    double calc(File source) throws FileNotFoundException
    {
        Scanner input = new Scanner(source);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];

        for(int i = 0; i < n; ++i)
        {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        Item[] var11 = items;
        int var7 = items.length;

        for(int var8 = 0; var8 < var7; ++var8)
        {
            Item item = var11[var8];
            System.out.println(item);
        }

        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);
        double result = 0.0;
        Arrays.sort(items);
        double currWeight = 0.0;

        for(int i = 0; currWeight < (double)W && i < items.length; ++i)
        {
            if ((double)W - currWeight > (double)items[i].weight)
            {
                result += (double)items[i].cost;
                currWeight += (double)items[i].weight;
            }
            else
            {
                result += (double)items[i].cost * ((double)W - currWeight) / (double)items[i].weight;
                currWeight += (double)W - currWeight;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}