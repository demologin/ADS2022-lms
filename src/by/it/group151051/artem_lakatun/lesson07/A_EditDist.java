package by.it.group151051.artem_lakatun.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

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

public class A_EditDist
{


    int getDistanceEdinting(String one, String two)
    {
        if (one.length() == 0)
        {
            return two.length();
        } else if (two.length() == 0)
        {
            return one.length();
        }
        else
        {
            int D_D = this.getDistanceEdinting(one, two.substring(0, two.length() - 1)) + 1;
            int D_I = this.getDistanceEdinting(one.substring(0, one.length() - 1), two) + 1;
            int D_R = this.getDistanceEdinting(one.substring(0, one.length() - 1), two.substring(0, two.length() - 1)) + (one.charAt(one.length() - 1) == two.charAt(two.length() - 1) ? 0 : 1);
            return D_D < D_I ? Math.min(D_D, D_R) : Math.min(D_I, D_R);
        }
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
