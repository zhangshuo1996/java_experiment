package collection_;

import java.util.PriorityQueue;

public class T_priorityQueue {
    public static void main(String[] args) {
        PriorityQueue<Integer> min_priority = new PriorityQueue<>(5);
        min_priority.add(1);
        min_priority.add(2);
        min_priority.add(3);
        min_priority.add(4);
        min_priority.add(5);

        min_priority.add(6);
        System.out.println();
    }
}
