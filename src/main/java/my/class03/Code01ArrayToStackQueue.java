package my.class03;

/**
 * 用数组结构实现大小固定的队列和栈.
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code01ArrayToStackQueue {

    public static class ArrayStack {
        // 数组实现栈

        // 栈的特点: 先进后出
        // 实现初始化 查看堆顶 弹栈 压栈
        private int[] arr;
        private int index;

        // 初始化堆
        public ArrayStack(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("This init size is less than 0");
            }
            arr = new int[initSize];
            index = 0;
        }
        // 查看堆顶
        public Integer peek() {
            if (index == 0) {
                return null;
            }
            return arr[index - 1];
        }

        // 弹栈
        public int pop() {
            if (index == 0) {
                throw new IllegalArgumentException("the stack is empty");
            }
            return arr[--index];
        }

        // 压栈
        public void push(int num) {
            if (index == arr.length){
                throw new IllegalArgumentException("the stack is full");
            }
            arr[index++] = num;
        }

    }

    public static class ArrayQueue {
        // 数组实现队列
        // 队列的特点: 先进先出
        // 三个指针, 其中一个是用于确认队列中元素大小
        // 这样就可以不用去考虑指针的情况
        // 比较方便
        /** 可存入的位置指针 */
        private int last = 0;
        /** 可取出元素的位置指针 */
        private int first = 0;
        /** 队列大小 */
        private int size = 0;
        /** 数组 */
        private int[] arr;

        public ArrayQueue(int initSize) {
            arr = new int[initSize];
        }

        public int peek() {
            if (size == 0) {
                throw new IllegalArgumentException("队列为空");
            }
            return arr[first];
        }

        public int poll() {
            if (size == 0) {
                throw new IllegalArgumentException("队列为空");
            }
            size--;
            int i = arr[first];
            first = first == arr.length - 1 ? 0 : first + 1;
            return i;
        }

        public void push(int obj) {
            if (size == arr.length) {
                throw new IllegalArgumentException("队列满了");
            }
            size++;
            arr[last] = obj;
            last = last == arr.length - 1 ? 0 : last + 1;
        }
    }

}
