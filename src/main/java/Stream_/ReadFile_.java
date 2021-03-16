package Stream_;


import javax.json.JsonReader;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


class Person {
    private int id;
    private String name;
    private boolean married;

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + '}';
    }
}

public class ReadFile_ {
    public static void main(String[] args) throws IOException {
//        test1();
        test3();
    }

    private static void test1() throws IOException {
        //BufferedReader类同
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream("files_/big.txt"), 8192);
        int bytes = -1;
        do {
            byte[] tmpArray = new byte[8192];
            bytes = reader.read(tmpArray);
            if (bytes != -1) {
                //做事情
                System.out.println();
            }
        } while(bytes > 0);
        reader.close();
    }


    /**
     * 使用字符流分布读取读取大文件，每次读取固定长度的
     * @throws IOException
     */
    private static void test3() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("files_/big.txt"), 8192);
        int bytes = -1;
        do {
            byte[] tmpArray = new byte[8192];
            char[] cbuf = new char[8192];
            bytes = reader.read(cbuf);
            if (bytes != -1) {
                //做事情
                System.out.println(new String(cbuf));
            }
        } while(bytes > 0);
        reader.close();
    }


}
