package by.it.group151051.klezovich.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int[][] matrix = new int[one.length() + 1][two.length() + 1];

        for (int i = 0; i <= one.length(); i++)
            matrix[i][0] = i;

        for (int i = 0; i <= two.length(); i++)
            matrix[0][i] = i;

        for (int i = 1; i <= one.length(); i++)
            for (int j = 1; j <= two.length(); j++)
            {
                int min = Math.min(matrix[i-1][j] + 1, matrix[i][j-1] + 1);
                int c = 0;

                if (one.charAt(i - 1) != two.charAt(j - 1))
                    c = 1;

                matrix[i][j] = Math.min(min, matrix[i-1][j-1] + c);
            }

        String result = "";

        int i = one.length();
        int j = two.length();

        while (i != 0 && j != 0)
        {
            result += ",";
            int c = 0;

            if (one.charAt(i - 1) != two.charAt(j - 1))
                c = 1;

            if (matrix[i][j] == (matrix[i-1][j-1] + c) && c== 1)
            {
                result += two.charAt(j - 1);
                result += "~";
                i--;
                j--;
            }
            else
                if (matrix[i][j] == (matrix[i-1][j-1] +c) && c == 0)
                {
                    result += "#";
                    i--;
                    j--;
                }
            else
                if (matrix[i][j] == matrix[i-1][j] + 1)
                {
                    result += one.charAt(i - 1);
                    result += "-";
                    i--;
                }
            else
                if (matrix[i][j] == matrix[i][j-1] + 1)
                {
                    result += two.charAt(j - 1);
                    result += "+";
                    j--;
            }
        }

        if (j != 0 || i != 0)
        {
            result += ",";

            if (i != 0)
            {
                result += one.charAt(i - 1);
                result += "-";
            }

            if (j != 0)
            {
                result += two.charAt(j - 1);
                result += "+";
            }
        }

        String temp_result = "";

        for (int x = result.length() - 1; x >= 0; x--)
            temp_result += result.charAt(x);

        result = temp_result;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}