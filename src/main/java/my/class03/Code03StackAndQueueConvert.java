package my.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 如何仅用队列结构实现栈结构？
 * 如何仅用栈结构实现队列结构？
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code03StackAndQueueConvert {
    public class TwoStacksQueue {
        // 负责存数据
        Stack<Integer> stackPush = new Stack<>();
        // 负责弹出数据
        Stack<Integer> stackPop = new Stack<>();

        public void push(Integer newValue) {
            stackPush.push(newValue);
        }

        public Integer poll() {

            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            } else if (stackPop.empty()) {
                // 要满足pop为空, 都弹完的时候才能操作
                // 不然会导致顺序错误

                // 把所有的数一次倒完
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        public Integer peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            } else if (stackPop.empty()) {
                while (stackPush.size() > 0) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }

    }


    public class TwoQueuesStack {
        // 队列实现栈
        // 由一个先进先出的结构, 改为先进后出
        // TODO 准备两个队列
        // 先存数据的队列, 再调用之后会改为只有一个数据(模拟栈顶)
        Queue<Integer> data = new LinkedList<>();
        // 其他数据
        Queue<Integer> help = new LinkedList<>();

        // TODO 第一个一直出, 直到剩下最后一个(这个返回)
        // 之前出的, 存入第二个队列
        public Integer peek() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            for (int i = 0; i < data.size() - 1; i++) {
                Integer poll = data.poll();
                help.offer(poll);
            }
            Integer res = data.poll();
            help.offer(res);
            myQueueSwap(data, help);
            return res;
        }

        private void myQueueSwap(Queue<Integer> data, Queue<Integer> help) {
            Queue<Integer> tmp = data;
            data = help;
            help = tmp;
        }

        public void push(Integer newValue) {
            data.add(newValue);
        }

        public Integer pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            for (int i = 0; i < data.size() - 1; i++) {
                Integer poll = data.poll();
                help.offer(poll);
            }
            Integer res = data.poll();
            myQueueSwap(data, help);
            return res;
        }


    }

}
