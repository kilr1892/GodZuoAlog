package my.class07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目
 * 的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数
 * 组，里面 是一个个具体的项目)，你来安排宣讲的日程，要求会
 * 议室进行 的宣讲的场次最多。返回这个最多的宣讲场次。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code06BestArrange {
    public static class Program {
        private int start;
        private int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class MyComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new MyComparator());
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (start <= programs[i].end) {
                result++;
                start = programs[i].end;
            }
        }
        return result;
    }

}
