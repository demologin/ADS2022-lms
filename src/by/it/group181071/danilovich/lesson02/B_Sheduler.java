package by.it.group181071.danilovich.lesson02;


import java.util.ArrayList;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {

    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    private static void qsort(Event[] events, int l, int r, boolean flag) {
        int gcc = l;
        int dam = r;
        Event x = events[(l + r) / 2];

        while (gcc <= dam) {
            if (flag) {
                while (events[gcc].start < x.start) {
                    ++gcc;
                }

                while (events[dam].start > x.start) {
                    --dam;
                }
            } else {
                while (events[gcc].stop < x.stop) {
                    ++gcc;
                }

                while (events[dam].stop > x.stop) {
                    --dam;
                }
            }

            if (gcc <= dam) {
                Event temp = events[gcc];
                events[gcc] = events[dam];
                events[dam] = temp;
                ++gcc;
                --dam;
            }
        }

        if (dam > l) {
            qsort(events, l, dam, flag);
        }

        if (gcc < r) {
            qsort(events, gcc, r, flag);
        }

    }

    List<Event> calcStartTimes(Event[] events, int from, int to)
    {
        List<Event> result = new ArrayList();
        int minut = 0;
        int st = 0;
        if (events.length == 0)
        {
            return result;
        } else {
            while (from < to) {
                qsort(events, st, events.length - 1, true);
                boolean fl = false;

                for (int i = st; i < events.length; ++i)
                {
                    if (events[i].start >= from)
                    {
                        minut = i;
                        st = i + 1;
                        fl = true;
                        break;
                    }
                }

                qsort(events, st - 1, events.length - 1, false);
                if (events[minut].stop > to || !fl)
                {
                    break;
                }

                result.add(events[minut]);
                from = events[minut].stop;
            }

            return result;                       //вернем итог
        }
    }
}