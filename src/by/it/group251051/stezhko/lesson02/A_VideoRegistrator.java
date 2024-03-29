package by.it.group251051.stezhko.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class A_VideoRegistrator {

    public static void main(String[] args)  {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration)  {
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        double sortedEvents[] = events.clone();
        Arrays.sort(sortedEvents);
        List<Double> result;
        result = new ArrayList<>();
        for(int i = 0; i < sortedEvents.length; i++) {
            double start = sortedEvents[i];
            double end = start + workDuration;
            result.add(start);
            while(i < sortedEvents.length && sortedEvents[i] <= end) {
                i++;
            }
            i--;
        }
        return result;
    }
}
