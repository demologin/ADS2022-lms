package by.it.group151051.birulia.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {

        int[] prevRow = new int[two.length() + 1];
        int[] currRow = new int[two.length() + 1];

        for (int j = 0; j <= two.length(); j++) {
            currRow[j] = j;
        }

        for (int i = 1; i <= one.length(); i++) {
            System.arraycopy(currRow, 0, prevRow, 0, prevRow.length);

            currRow[0] = i;
            for (int j = 1; j <= two.length(); j++) {
                int cost = (one.charAt(i - 1) != two.charAt(j - 1)) ? 1 : 0;
                int add = prevRow[j] + 1;
                int delete = currRow[j - 1] + 1;
                int change = prevRow[j - 1];
                currRow[j] = Math.min(Math.min(add, delete), change + cost);
            }
        }

        return currRow[currRow.length - 1];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}