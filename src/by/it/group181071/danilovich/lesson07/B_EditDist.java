package by.it.group181071.danilovich.lesson07;

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

public class B_EditDist
{


    int getDistanceEdinting(String one, String two)
    {
        int[][] gcc = new int[2][two.length() + 1];
        gcc[0][0] = 0;

        for(int i = 0; i <= one.length(); ++i)
        {
            for(int j = 0; j <= two.length(); ++j)
            {
                if (j == 0 && i > 0)
                {
                    gcc[1][0] = i;
                } else if (i == 0)
                {
                    gcc[0][j] = j;
                }
                else
                {
                    int R = gcc[0][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);
                    gcc[1][j] = gcc[1][j - 1] + 1 < gcc[0][j] + 1 ? Math.min(gcc[1][j - 1] + 1, R) : Math.min(gcc[0][j] + 1, R);
                }
            }

            if (i > 0)
            {
                System.arraycopy(gcc[1], 0, gcc[0], 0, two.length());
            }
        }

        return gcc[1][two.length()];
    }



    public static void main(String[] args) throws FileNotFoundException
    {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}