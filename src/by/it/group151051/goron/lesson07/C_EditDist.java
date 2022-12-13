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

    String getDistanceEditing(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = wagnerFischer(matrix, i, j, one, two);
            }
        }

        // Получаем редакционное предписание путем обхода матрицы из правого нижнего угла в левый верхний.
        // Ищем минимальный элемент среди элементов матрицы под индексами [i][j-1], [i-1][j] и [i-1][j-1].
        // Если индекс [i][j-1] - это вставка, [i-1][j] - удаление и [i-1][j-1] - замена (или совпадение)
        // в конце переходим на этот элемент для дальнейшего сравнения
        StringBuilder result = new StringBuilder();
        int i = matrix.length - 1;
        int j = matrix[i].length - 1;
        while (i != 0 || j != 0) {
            int m = 0;
            if (j != 0 && i != 0)
                m = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;

            // Поиск минимального элемента среди элементов матрицы под индексами [i][j-1], [i-1][j] и [i-1][j-1]
            int min = matrix[i][j];
            if (j != 0)
                min = matrix[i][j - 1] + 1;
            if (i != 0 && matrix[i - 1][j] + 1 < min)
                min = matrix[i - 1][j] + 1;
            if (i != 0 && j != 0 && matrix[i - 1][j - 1] + m < min)
                min = matrix[i - 1][j - 1] + m;

            // В зависимости какой элемент матрицы минимальный,
            // определяем редакционное предписание и изменяем соответствующие индексы
            if (min == matrix[i][j - 1] + 1) {
                result.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            }
            else if (min == matrix[i - 1][j] + 1) {
                result.insert(0, "-" + one.charAt(i - 1) + ", ");
                i--;
            }
            else if (min == matrix[i - 1][j - 1] + m) {
                if (m == 1)
                    result.insert(0, "~" + one.charAt(i - 1) + ",");
                else
                    result.insert(0, "#,");
                i--;
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
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
    }
}