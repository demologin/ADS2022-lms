package by.it.group151051.goron.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {

    int getMaxSum(InputStream stream ) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int i = 0;
        int[] stairsSum = new int[n+1];
        while (i <= n) {
            if (i == 0) {   // Если ступенек нет, то нет и суммы
                stairsSum[i] = 0;
                i++;
            }
            else if (i == 1) {  // Если ступенька одна, то прыгаем на ее
                stairsSum[i] = stairs[i-1];
                i++;
            }
            else {
                stairsSum[i] = stairsSum[i-1] + stairs[i-1];    // Иначе, просто прыгаем на следующую ступеньку с текущей и записываем эту сумму
                // Но если перейти на предыдущую ступеньку и перепрыгнуть текущую ступеньку сумма окажется больше
                int jumpOverSum = stairsSum[i - 2] + stairs[i - 1];
                if (jumpOverSum > stairsSum[i])
                    stairsSum[i] = jumpOverSum;    // то перепрыгиваем её и записываем эту сумму
                i++;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return stairsSum[n];
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/goron/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        System.out.println(res);
    }

}
