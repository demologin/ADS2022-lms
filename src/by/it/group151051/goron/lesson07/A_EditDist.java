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
    int wagnerFischer (int[][] matrix, int i, int j, String str1, String str2) {
        if (i == 0 || j == 0) {     // Первые условия рекуррентной формулы:
            if (i == 0 && j == 0)   // Когда i = 0, j = 0;
                matrix[i][j] = 0;
            else if (i > 0)         // j = 0, i > 0
                matrix[i][j] = i;
            else if (j > 0)
                matrix[i][j] = j;   // i = 0, j > 0
        }
        else {
            int m = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1; // m(S1[i], S2[j]) (m равно 0, если S1[i] == S2[j], иначе равно 1)
            matrix[i][j] = matrix[i][j - 1] + 1; // D(i, j-1) + 1
            if (matrix[i - 1][j] + 1 < matrix[i][j])
                matrix[i][j] = matrix[i - 1][j] + 1;  // D(i-1, j) + 1
            if (matrix[i - 1][j - 1] + m < matrix[i][j])
                matrix[i][j] = matrix[i - 1][j - 1] + m; // D(i-1, j-1) + m(S1[i], S2[j])
        }
        // Переходы по матрице
        if (j < matrix[i].length-1)
            j++;
        else if (j == matrix[i].length-1 && i < matrix.length-1) {
            i++;
            j = 0;
        }
        else if (i == matrix.length-1) {  // Условие прекращения рекурсии
            return matrix[i][j];
        }

        return wagnerFischer(matrix, i, j, str1, str2);
    }
    int getDistanceEditing(String str1, String str2) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        int i = 0, j = 0;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return wagnerFischer(matrix, i, j, str1, str2);
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/goron/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
    }
}
