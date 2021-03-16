package Serializable_;

import java.io.*;

class Person implements Serializable {
    private String name;
    private int age;

    //不提供无参构造器

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Teacher implements Serializable {

    private String name;
    private Person person;

    public Teacher(String name, Person person) {
        this.name = name;
        this.person = person;
    }



    public static void main(String[] args) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"))) {
            Person person = new Person("路飞", 20);
            Teacher teacher = new Teacher("雷利", person);
            oos.writeObject(teacher);
        }
    }

    public Person getPerson() {
        return person;
    }
}




public class SerializableTest1 {
    public static void main(String[] args) throws Exception {
//        writeObject();
//        readObject();
//        writeTeacher();
        readTeacher();
    }

    //-------------------------------------普通的序列化------------------------------------------------------------------------------

    /**
     * 通过序列化将一个java对象保存到文件
     */
    public static void writeObject() {
        try (//创建一个ObjectOutputStream输出流
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            //将对象序列化到文件s
            Person person = new Person("9龙", 23);
            oos.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反序列化将保存在文件中的对象 读取出来
     */
    public static void readObject() {
        try (//创建一个ObjectInputStream输入流
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
            Person brady = (Person) ois.readObject();
            System.out.println(brady);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------普通的序列化------------------------------------------------------------------------------

    //-------------------------------------同一个对象序列化多次------------------------------------------------------------------------------

    public static void writeTeacher() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"))) {
            Person person = new Person("路飞", 20);
            Teacher t1 = new Teacher("雷利", person);
            Teacher t2 = new Teacher("红发香克斯", person);
            //依次将4个对象写入输入流
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(person);
            oos.writeObject(t2); // 同一对象序列化多次，会将这个对象序列化多次吗？答案是否定的。
        }
    }

    /**
     * 同一对象序列化多次， 反序列化后仍是一个对象
     * Java序列化算法
     * 所有保存到磁盘的对象都有一个序列化编码号
     * 当程序试图序列化一个对象时，会先检查此对象是否已经序列化过，只有此对象从未（在此虚拟机）被序列化过，才会将此对象序列化为字节序列输出。
     * 如果此对象已经序列化过，则直接输出编号即可。
     */
    public static void readTeacher(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))) {

            // 反序列化的顺序与序列化时的顺序一致。
            Teacher t1 = (Teacher) ois.readObject();
            Teacher t2 = (Teacher) ois.readObject();
            Person p = (Person) ois.readObject();
            Teacher t3 = (Teacher) ois.readObject();
            System.out.println(t1 == t2); // false
            System.out.println(t1.getPerson() == p); // true
            System.out.println(t2.getPerson() == p); // true
            System.out.println(t2 == t3); // true
            System.out.println(t1.getPerson() == t2.getPerson()); // true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------同一个对象序列化多次------------------------------------------------------------------------------

    // TODO: 可以自定义的序列化 transient  https://www.cnblogs.com/9dragon/p/10901448.html

    
}
