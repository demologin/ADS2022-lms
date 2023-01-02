package by.it.group151051.klezovich.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

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

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        int result = 0;

        for (int i = 0; i <= one.length(); i++)
            Arrays.fill(matrix[i], -1);

        int length1 = one.length();
        int length2 = two.length();

        for (int i = 0; i <= one.length(); i++)
            distance(matrix, length1, length2, one, two);

        result = matrix[one.length()][two.length()];

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        return result;
    }

    int distance(int matrix[][], int i, int j, String one, String two)
    {
        if (matrix[i][j] == -1)
            if (i == 0)
                matrix[i][j] = j;
            else
                if (j == 0)
                    matrix[i][j] = i;
                else
                {
                    int previousTop = distance(matrix, i - 1, j, one, two) + 1;
                    int previousBack = distance(matrix, i, j-1, one, two) + 1;

                    int c = 0;

                    if (one.charAt(i - 1) != two.charAt(j - 1))
                        c = 1;

                    int previousDiagonal = distance(matrix, i -1, j - 1, one, two) + c;
                    int min = Math.min(previousTop, previousBack);

                    matrix[i][j] = Math.min(min, previousDiagonal);
                }

        return matrix[i][j];
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
