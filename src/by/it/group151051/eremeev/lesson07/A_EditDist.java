package by.it.group151051.eremeev.lesson07;

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

    private class Levenshtein {
        String str1;
        String str2;

        Levenshtein(String str1, String str2) {
            this.str1 = str1;
            this.str2 = str2;
        }

        public int calculate() {
            return diff(str1.length(), str2.length());
        }

        private int diff(int i, int j) {
            if (i == 0 && j == 0) {
                return 0;
            }

            if (j == 0 && i > 0) {
                return i;
            }

            if (i == 0 && j > 0) {
                return j;
            }

            return min(
                diff(i, j - 1) + 1,
                diff(i - 1, j) + 1,
                diff(i - 1, j - 1) + mediator(i, j)
            );
        }

        private int min(int a, int b, int c) {
            if (a < b) {
                return (a < c) ? a : c;
            } else {
                return (b < c) ? b : c;
            }
        }

        private int mediator(int i, int j) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                return 0;
            }

            return 1;
        }
    }


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Levenshtein levenshtein = new Levenshtein(one, two);

        int result = levenshtein.calculate();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/eremeev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
