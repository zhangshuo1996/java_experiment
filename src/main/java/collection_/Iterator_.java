package collection_;

import java.util.*;

public class Iterator_ {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }



    /**
     * 遍历同时remove list中的元素
     */
    private static void test1() {
        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("helloworld");
        for (int i = 0; i < list.size(); i++) { // list.size() 不断变化
            list.remove(i);
            /**
             * 第一次执行： i = 0 list : ["hello", "world", "helloworld"] --> ["world", "helloworld"]
             * 第二次执行： i = 1 list : ["world", "helloworld"] --> ["world"]
             */
        }
        System.out.println(list);
    }

    /**
     * 遍历iterator 同时remove() list 中的元素
     */
    private static void test2() {
        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("helloworld");
        for (Iterator<String> ite = list.iterator(); ite.hasNext();) {
            String next = ite.next();
            list.remove(next);
        }

        /**程序出现以下异常：
         * Exception in thread "main" java.util.ConcurrentModificationException
         * 当使用迭代器对ArrayList进行迭代的时候，不能在迭代中使用list.remove方法来删除元素，否则会抛出该异常。
         * 本质原因是当调用ite.iterator()方法后，会生成一个Iterator接口的实例对象，该对象内部维护一个变量ExpectedModCount，该变量的初始值是集合的modCount，
         * 在迭代进行中使用集合的remove方法的时候会导致modCount值+1而ExpectedModCount还是原先的值，
         * 调用ite.next()方法内部会对ExpectedModCount与modCount进行比较操作，如果不等就会抛出ConcurrentModficationException。
         */
    }

    /**
     *遍历iterator 同时remove() iterator中的元素
     */
    private static void test3() {
        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("helloworld");

        for (Iterator<String> ite = list.iterator(); ite.hasNext();) {
            String next = ite.next();
            System.out.println(next);
            ite.remove(); //关键看此处
        }
        System.out.println(list.size());
        /**
         *  在迭代过程中虽然不能使用集合ArrayList的remove操作或者add操作(会致使modCount改变的操作)，但是使用迭代器自带的remove方法是可以的，
         *  ite.remove内部会对ExceptedModCount的值进行修正，所以在调用ite.next()方法的时候，ExceptedModCount == ModCount的判断结果为true，
         *  故不会导致抛出ConCurrentModificationException
         */
    }


    private static void test4() {

        List<String> staff = new LinkedList<>();
        staff.add("zhuwei");
        staff.add("xuezhangbin");
        staff.add("taozhiwei");
        ListIterator<String> iter = staff.listIterator();
        String first = iter.next();

        //删除zhuwei
        iter.remove();

        //把zhuwei改为simei
        //iter.set("simei");
        System.out.println("first:"+first);

        iter.add("xiaobai");

        //遍历List元素
        System.out.println("遍历List中元素，方法一：");
        for(String str : staff)
            System.out.println(str+"   ");

        iter = staff.listIterator();
        System.out.println("遍历List中元素，方法二：");
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }
    }
}
