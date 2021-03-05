package JVM_;
import java.util.ArrayList;
import java.util.List;

/**
 * 内存快照
 * 演示堆内存溢出
 * 配置jvm参数
 * VM Args：
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=f:/test/dump
 * 参数-XX：+HeapDumpOnOutOfMemoryError可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析,文件在项目中
 */
public class MemoryShot {

    static class OOMObject {
        public byte[] byt = new byte[1 * 1024];
    }

    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
            Thread.sleep(1000);
        }
    }
}


