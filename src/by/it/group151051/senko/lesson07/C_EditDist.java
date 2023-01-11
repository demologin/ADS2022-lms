package by.it.group151051.senko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

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
        String result = "";

        int[][] matrix = new int[one.length() + 1][two.length() + 1];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = step_distance(i,j,one,two,matrix);
            }
        }

        int i = matrix.length - 1;
        int j = matrix[i].length - 1;
        StringBuilder op = new StringBuilder();

        while(i > 0 && j > 0){
            int current = matrix[i][j];
            int remove = matrix[i-1][j];
            int insert = matrix[i][j-1];
            int replace = matrix[i-1][j-1];
            if(replace <= remove && replace <= insert && replace <= current) {
                i--;
                j--;
                if(replace == current){
                    op.insert(0, "#,");
                }
                if(replace == current - 1){
                    op.insert(0, "~,");
                }

                else if (insert <= remove && insert <= current) {
                    j--;
                    op.insert(0, "+,");
                }
                else if (remove <= insert && remove <= current) {
                    i--;
                    op.insert(0, "-,");
                }
            }
            else if(insert <= remove && insert <= current) {
                j--;
                op.insert(0, "+,");
            }
            else {
                i--;
                op.insert(0, "-,");
            }
        }
        result = op.toString();

        System.out.println(result);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    public static int step_distance(int i, int j, String a, String b, int[][] matrix){
        if(i == 0 && j == 0) return 0;
        if(i > 0 && j == 0) return i;
        if(i == 0 && j > 0) return j;

        int m = (a.charAt(i-1) == b.charAt(j-1)) ? 0 : 1;
        int min_i = matrix[i][j-1]+1;
        int min_j = matrix[i-1][j]+1;
        int min_ij = matrix[i-1][j-1]+m;

        int min = Arrays.stream(new int[]{min_i, min_j, min_ij}).min().getAsInt();
        return min;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/senko/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}