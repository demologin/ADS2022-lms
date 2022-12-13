package by.it.group151051.goron.lesson07;

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

public class B_EditDist {
    int wagnerFischer (int[][] matrix, int i, int j, String str1, String str2) {
        // Первые условия рекуррентной формулы:
        if (i == 0 && j == 0)       // Когда i = 0, j = 0 - в matrix[i][j] записывается 0
            return 0;
        else if (i > 0 && j == 0)   // j = 0, i > 0 - записывается i
            return i;
        else if (j > 0 && i == 0)   // i = 0, j > 0 - записывается j
            return j;

        int m = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1; // m(S1[i], S2[j]) (m равно 0, если S1[i] == S2[j], иначе равно 1)
        int min = matrix[i][j - 1] + 1;     // D(i, j-1) + 1
        if (matrix[i - 1][j] + 1 < min)
            min = matrix[i - 1][j] + 1;     // D(i-1, j) + 1
        if (matrix[i - 1][j - 1] + m < min)
            min = matrix[i - 1][j - 1] + m; // D(i-1, j-1) + m(S1[i], S2[j])

        return min;
    }

    int getDistanceEditing(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = wagnerFischer(matrix, i, j, one, two);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return matrix[one.length()][two.length()];
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
    }
}