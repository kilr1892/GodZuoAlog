package my.class03;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返
 * 回栈中最小元素的操作。
 * 【要求】
 * 1．pop、push、getMin操作的时间复杂度都是O(1)。
 * 2．设计的栈类型可以使用现成的栈结构。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code02GetMinStack {
    // TODO 返回栈中最小元素 不要遍历
    // 思路:
    // 1. 准备两个大小一样的栈
    // 2. 每次存入一个数
    // 3. 并与另一个栈的栈顶比较
    // 5. 小的话就存这个, 大的话就继续存栈顶
    //    多存一次 是为了弹栈的时候方便
    public class MyStack1 {
        Stack<Integer> dataStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int newNum) {

            dataStack.push(newNum);
            if (dataStack.empty()) {
                minStack.push(newNum);
            } else {
                newNum = newNum < minStack.peek() ? newNum : minStack.peek();
                minStack.push(newNum);
            }
        }

        public int getMin() {
            return minStack.peek();
        }

        public int pop() {
            if (dataStack.empty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            Integer pop = dataStack.pop();
            minStack.pop();
            return pop;
        }
    }
}
