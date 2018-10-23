package cn.anyoufang.test;

import java.util.Stack;

/**
 * @author daiping
 */
public class StackTest {

    private static final Stack<Integer> stackA = new Stack<>();
    private static final Stack<Integer> stackB = new Stack<>();

    /**
     * 入队列
     * @param data
     */
    public static void addDataToStack(Integer data) {
        stackA.push(data);
    }


    /**
     * 出队列
     * @return
     */
    public static Integer getDataFromStack() {
      if(stackB.isEmpty()) {
          if(stackA.isEmpty()) {
              return null;
          }
          transfer();
      }
      return stackB.pop();
    }

    /**
     * 传输数据
     */
    private static void transfer() {
         while(!stackA.isEmpty()) {
             stackB.push(stackA.pop());
         }
    }

    public static void main(String[] args){
        for(int i =0;i<5;i++) {
            addDataToStack(i);
        }

        System.out.println(getDataFromStack());
        System.out.println(getDataFromStack());
        System.out.println(getDataFromStack());
        System.out.println(getDataFromStack());
        System.out.println(getDataFromStack());
        System.out.println(getDataFromStack());

    }
}
