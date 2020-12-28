package base;
import java.util.HashSet;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * 测试equal hashcode ==
 */
public class equal_hashcode_ {
    public static void main(String[] args) {
//        Person p1 = new Person("eee", 100);
//        Person p2 = new Person("eee", 100);
//        Person p3 = new Person("aaa", 200);
//        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
//        System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());

/*
        Main3cumtomizeEquals.Person p1 = new Main3cumtomizeEquals.Person("eee", 100);
        Main3cumtomizeEquals.Person p2 = new Main3cumtomizeEquals.Person("eee", 100);
        Main3cumtomizeEquals.Person p3 = new Main3cumtomizeEquals.Person("aaa", 200);
        // 新建HashSet对象
        HashSet<Main3cumtomizeEquals.Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        // 比较p1 和 p2， 并打印它们的hashCode() p1.equals(p2) : true; p1(460141958) p2(1163157884)
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        // 打印set  set:[basic_.Main3cumtomizeEquals$Person@74a14482, basic_.Main3cumtomizeEquals$Person@4554617c, basic_.Main3cumtomizeEquals$Person@1b6d3586]
        System.out.printf("set:%s\n", set);
*/


        // 新建Person对象，
        Main4cumtomizeEqualsAndhashcode.Person p1 = new Main4cumtomizeEqualsAndhashcode.Person("eee", 100);
        Main4cumtomizeEqualsAndhashcode.Person p2 = new Main4cumtomizeEqualsAndhashcode.Person("eee", 100);
        Main4cumtomizeEqualsAndhashcode.Person p3 = new Main4cumtomizeEqualsAndhashcode.Person("aaa", 200);
        Main4cumtomizeEqualsAndhashcode.Person p4 = new Main4cumtomizeEqualsAndhashcode.Person("EEE", 100);
        // 新建HashSet对象
        HashSet<Main4cumtomizeEqualsAndhashcode.Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        // 比较p1 和 p2， 并打印它们的hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        // 比较p1 和 p4， 并打印它们的hashCode()
        System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
        // 打印set
        System.out.printf("set:%s\n", set);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        // 如果是同一个对象返回true，反之返回false
        if (this == obj) {
            return true;   // 引用相同，返回为true
        }

        // 判断是否类型相同
        if (this.getClass() != obj.getClass()) {
            return false;   // getClass() 不相同，为false
        }
        Person person = (Person) obj;  // 引用赋值
        return name.equals(person.name) && age == person.age;   // name引用相同 && age引用相同
    }
}


class Main3cumtomizeEquals {

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        /**
         * 重写equals方法,当其 name引用相同 && age引用相同 的时候就认为它相同，
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            // 如果是同一个对象返回true，反之返回false
            if (this == obj) {
                return true;   // 引用相同，返回为true
            }

            // 判断是否类型相同
            if (this.getClass() != obj.getClass()) {
                return false;   // getClass() 不相同，为false
            }
            Person person = (Person) obj;  // 引用赋值
            return name.equals(person.name) && age == person.age;   // name引用相同 && age引用相同
        }
    }
}



class Main4cumtomizeEqualsAndhashcode {

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        /**
         * 重写equals方法,当其 name引用相同 && age引用相同 的时候就认为它相同，
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            // 如果是同一个对象返回true，反之返回false
            if (this == obj) {
                return true;   // 引用相同，返回为true
            }

            // 判断是否类型相同
            if (this.getClass() != obj.getClass()) {
                return false;   // getClass() 不相同，为false
            }
            Person person = (Person) obj;  // 引用赋值
            return name.equals(person.name) && age == person.age;   // name引用相同 && age引用相同
        }

        /**
         * 重写hashCode方法,逻辑为  name的哈希值^age
         */
        @Override
        public int hashCode() {
            //  68517 ^ 100 = 68545
            // 64545 ^ 200 =68545
            // 68517 ^ 100 =68545
            int nameHash = name.toUpperCase().hashCode();
            return nameHash ^ age;   //  ^  异或运算，相同为0，不同为1
        }
    }
}