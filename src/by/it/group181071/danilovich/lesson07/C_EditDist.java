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

        String temp = one;
        one = two;
        two = temp;

        StringBuilder result = new StringBuilder();

        int [][] matrix = new int[one.length() + 1][two.length() + 1];

        for (int j = 1; j < matrix[0].length; j++) {
            matrix[0][j] = j;

        }

        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = i;
            for (int j = 1; j < matrix[i].length; j++) {
                int cost = (one.charAt(i - 1) != two.charAt(j - 1)) ? 1 : 0;
                int add = matrix[i - 1][j] + 1;
                int delete = matrix[i][j - 1] + 1;
                int change = matrix[i - 1][j - 1];
                matrix[i][j] = Math.min(Math.min(add, delete), change + cost);
            }
        }


        int i = matrix.length-1;
        int j = matrix[i].length - 1;
        while (i >= 0 && j >= 0) {

            if (i == 0 && j == 0) {
                break;
            }

            int cost = i > 0 && j > 0 && (one.charAt(i - 1) != two.charAt(j - 1)) ? 1 : 0;
            int add = i > 0 ? matrix[i - 1][j] : Integer.MAX_VALUE;
            int delete = j > 0 ? matrix[i][j - 1] : Integer.MAX_VALUE;
            int change = i > 0 && j > 0 ? matrix[i - 1][j - 1] : Integer.MAX_VALUE;
            int min = Math.min(Math.min(add, delete), change);

            if (min == change) {
                if (cost == 1) {
                    result.insert(0,"~" + one.charAt(i - 1) + ",");
                } else {
                    result.insert(0,"#,");
                }
                i--;
                j--;
            } else if (min == add) {
                result.insert(0,"+" + one.charAt(i - 1) + ",");
                i--;
            } else if (min == delete) {
                result.insert(0,"-" + two.charAt(j - 1) + ",");
                j--;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
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