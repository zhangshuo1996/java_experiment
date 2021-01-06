package collections_;

import java.util.HashMap;
import java.util.Map;

public class T_HashMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 200; i++){
            map.put(i, i);
        }
        map.put(1, 0);
    }

    /**
     * 下面是HashMap的大致源码，包括put和get，红黑树部分没有
     * @param <K>
     * @param <V>
     */
    static class Source_code<K, V> {
        private static final int TREEIFY_THRESHOLD = 8;

        static class Node<K, V> implements Map.Entry<K,V>{
            int hash;
            K key;
            V value;
            Node<K, V> next;

            Node(int hash, K key, V value, Node<K, V> next) {
            }

            @Override
            public K getKey() {
                return null;
            }

            @Override
            public V getValue() {
                return null;
            }

            @Override
            public V setValue(V value) {
                return null;
            }
        }

        class TreeNode<K, V> extends Node<K, V>{
            TreeNode(int hash, K key, V value, Node<K, V> next) {
                super(hash, key, value, next);
            }

            public Node<K,V> getTreeNode(int hash, Object key) {
                return null;
            }

            public Node<K,V> putTreeVal(Source_code<K, V> kvSource_code, Node<K, V>[] tab, int hash, K key, V value) {
                return new Node<K, V>(hash, key, value, tab[0]);
            }

            public void split(Source_code<K, V> kvSource_code, Node<K, V>[] newTab, int j, int oldCap) {
            }
        }

        Node<K, V>[] table;
        int MAXIMUM_CAPACITY = Integer.MAX_VALUE;
        int threshold = 8;
        int DEFAULT_INITIAL_CAPACITY = 16;
        double DEFAULT_LOAD_FACTOR = 0.75;
        float loadFactor = 0.75f;

        static final int hash(Object key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }

        final Node<K,V> getNode(int hash, Object key) {  // //根据key搜索节点的方法。记住判断key相等的条件：hash值相同 并且 符合equals方法。
            Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
            if ((tab = table) != null && (n = tab.length) > 0 && // //根据输入的hash值，可以直接计算出对应的下标（n - 1）& hash，缩小查询范围，如果存在结果，则必定在table的这个位置上。
                    (first = tab[(n - 1) & hash]) != null) {
                if (first.hash == hash && // always check first node 判断第一个存在的节点的key是否和查询的key相等。如果相等，直接返回该节点。
                        ((k = first.key) == key || (key != null && key.equals(k))))
                    return first;
                if ((e = first.next) != null) { // //遍历该链表/红黑树直到next为null。
                    if (first instanceof TreeNode) //在内部遍历红黑树节点，查看是否有匹配的TreeNode。
                        return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                    do {
                        if (e.hash == hash && // //当这个table节点上存储的是链表结构时，用跟第11行同样的方式去判断key是否相同。
                                ((k = e.key) == key || (key != null && key.equals(k))))
                            return e;
                    } while ((e = e.next) != null);
                }
            }
            return null;
        }

        final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
            Node<K, V>[] tab;
            Node<K, V> p;
            int n, i;
            // 第一部分：数组为null 使用resize() 方法创建新的数组
            if ((tab = table) == null || (n = tab.length) == 0)
                n = (tab = resize()).length;
            // 第二部分：i表示插入的位置， 判断插入的位置是否出现了hash冲突，没有出现冲突直接插入，否则执行第三部
            if ((p = tab[i = (n - 1) & hash]) == null)
                tab[i] = new Node(hash, key, value, null);
                // 第三部分：出现了Hash冲突
            else {
                Node<K, V> e;
                K k;
                // 3.1 ： 判断插入的val与table的元素是否相同， 相同的话直接替换掉旧的值
                if (p.hash == hash &&
                        ((k = p.key) == key || (key != null && key.equals(k))))
                    e = p;
                    // 3.2 ：判断插入的结构是否 是红黑树， 若是，直接put到红黑树中
                else if (p instanceof TreeNode)
                    e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
                    // 3.3 ： 插入的结构是链表
                else {
                    for (int binCount = 0; ; ++binCount) {
                        if ((e = p.next) == null) {
                            // 3.3.1 ：这个地方有小问题？ p的初始位置是在链表头部，还是在待插入的位置
                            p.next = new Node(hash, key, value, null);
                            // 3.3.2 ： 链表长度大于阈值，转为红黑树
                            if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                                treeifyBin(tab, hash);
                            break;
                        }
                        if (e.hash == hash && // 链表中原本就存在相同值
                                ((k = e.key) == key || (key != null && key.equals(k))))
                            break;
                        p = e;
                    }
                }
                if (e != null) { // existing mapping for key
                    V oldValue = e.value;
                    if (!onlyIfAbsent || oldValue == null)
                        e.value = value;
                    afterNodeAccess(e);
                    return oldValue;
                }
            }
            int modCount = 0;
            ++modCount;
            // 第四部分： 插入成功之后， 判断实际存在的键值对数量是够大于阈值，大于的话扩容
            int size = 0;
            if (++size > threshold)
                resize();
            afterNodeInsertion(evict);
            return null;
        }

        private void afterNodeInsertion(boolean evict) {
        }

        private void afterNodeAccess(Node<K,V> e) {
        }

        private void treeifyBin(Node<K,V>[] tab, int hash) {
        }


        final Node<K, V>[] resize() {
            Node<K, V>[] oldTab = table;
            int oldCap = (oldTab == null) ? 0 : oldTab.length;
            int oldThr = threshold;
            int newCap, newThr = 0;
            // 第一部分：扩容
            if (oldCap > 0) {
                if (oldCap >= MAXIMUM_CAPACITY) {
                    threshold = Integer.MAX_VALUE;
                    return oldTab;
                } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                        oldCap >= DEFAULT_INITIAL_CAPACITY)
                    newThr = oldThr << 1; // double threshold
            } // 第二部分：设置阈值
            else if (oldThr > 0) // 阈值如果初始化过，直接使用即可
                newCap = oldThr;
            // 没有初始化过，就初始化一个默认的
            else {               // zero initial threshold signifies using defaults
                newCap = DEFAULT_INITIAL_CAPACITY;
                newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
            }
            if (newThr == 0) {
                float ft = (float) newCap * loadFactor;
                newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                        (int) ft : Integer.MAX_VALUE);
            }
            threshold = newThr;
            Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
            table = newTab;
            // 第三部分： 将旧的数据保存在新的数组中
            if (oldTab != null) {
                for (int j = 0; j < oldCap; ++j) {
                    Node<K, V> e;
                    if ((e = oldTab[j]) != null) {
                        oldTab[j] = null;
                        // 只有一个节点，通过索引位置直接映射
                        if (e.next == null)
                            newTab[e.hash & (newCap - 1)] = e;
                        // 是红黑树的话，将树拆分后进行映射
                        else if (e instanceof TreeNode)
                            ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
                        else { // 如果是多个链表， 将原链表拆分为两个链表
                            Node<K, V> loHead = null, loTail = null;
                            Node<K, V> hiHead = null, hiTail = null;
                            Node<K, V> next;
                            do {
                                next = e.next;
                                if ((e.hash & oldCap) == 0) {
                                    if (loTail == null)
                                        loHead = e;
                                    else
                                        loTail.next = e;
                                    loTail = e;
                                } else {
                                    if (hiTail == null)
                                        hiHead = e;
                                    else
                                        hiTail.next = e;
                                    hiTail = e;
                                }
                            } while ((e = next) != null);
                            // 链表1存储于原索引
                            if (loTail != null) {
                                loTail.next = null;
                                newTab[j] = loHead;
                            }
                            // 链表2存储于原索引+原Hash桶的偏移量
                            if (hiTail != null) {
                                hiTail.next = null;
                                newTab[j + oldCap] = hiHead;
                            }
                        }
                    }
                }
            }
            return newTab;
        }
    }
}


