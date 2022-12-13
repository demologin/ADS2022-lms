package by.it.group151051.goron.lesson02;
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
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            //тут может быть ваш компаратор


            return 0;
        }

        public static void quickSortR(Item[] items, int low, int high) {
            if (items.length == 0)
                return;
            if (low >= high)
                return;

            int middle = low + (high - low) / 2;
            Item pivot = items[middle];

            int i = low, j = high;
            while (i <= j) {
                while ( (items[i].cost / items[i].weight) > (pivot.cost  / pivot.weight) ) {
                    i++;
                }

                while ( (items[j].cost / items[j].weight) < (pivot.cost / pivot.weight) ) {
                    j--;
                }

                if (i <= j) {
                    Item temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j)
                quickSortR(items, low, j);

            if (high > i)
                quickSortR(items, i, high);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        //Сортировка по убыванию предметов по удельной ценности
        Item.quickSortR(items,0, items.length-1);

        //Заполнение рюкзака
        int currentWeigh = 0;
        int i = 0;
        double result = 0;
        while (currentWeigh <= 60 && i < items.length) { //Пока есть место в рюкзаке и предметы не кончились
            if (currentWeigh + items[i].weight <= 60) { //Если весь предмет помещается в рюкзак
                result += items[i].cost; // Весь предмет помещается в рюкзак
                currentWeigh += items[i].weight;
            }
            else {  // Иначе места на целый предмет недостаточно
                while (i < items.length) {
                    double emptySpace = 60 - currentWeigh; // Оставшееся место в рюкзаке
                    if (items[i].weight < emptySpace) { // Если предмет помещается в пустое место
                        result += items[i].cost; // Весь предмет помещается в рюкзак
                        currentWeigh += items[i].weight;
                        i++;
                    }
                    else { // Иначе предмет разбивается на части и необходимая часть помещается в рюкзак
                        result += ((double)items[i].cost / (double)items[i].weight) * emptySpace;
                        break; // Рюкзак заполнен
                    }
                }
                break; // Рюкзак заполнен
            }
            i++;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir")+"/src/";
        File f = new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}