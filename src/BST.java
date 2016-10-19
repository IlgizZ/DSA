/**
 * Created by Ilgiz on 27.09.2016.
 */
public class BST<K extends Comparable, V> {
    private TNode root;
    private int size;

    private class TNode {
        K key;
        V value;
        TNode left;
        TNode right;
        TNode parent;

        public TNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public TNode getParent() {
            return parent;
        }

        public void setParent(TNode parent) {
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public TNode getLeft() {
            return left;
        }

        public void setLeft(TNode left) {
            this.left = left;
        }

        public TNode getRight() {
            return right;
        }

        public void setRight(TNode right) {
            this.right = right;
        }
    }

    public V find(K key) {
        TNode node = search(key, root);
        return node == null ? null : node.getValue();
    }

    private TNode search(K key, TNode node) {
        if (node == null)
            return null;

        int compareNum = key.compareTo(node.getKey());

        if (compareNum == 0) {
            return node;
        } else if (compareNum < 0) {
            return search(key, node.getLeft());
        } else {
            return search(key, node.getLeft());
        }
    }

    public boolean add(K key, V value) {
        if (search(key, root) == null)
            return false;

        TNode node = root;
        TNode parent = null;

        while (true) {
            if (node == null) {
                node = new TNode(key, value);
                node.setParent(parent);
                size++;
                return true;
            }

            node = parent;
            int compareNum = key.compareTo(node.getKey());

            if (compareNum < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
    }

    public boolean remove(K key) {
        TNode node = search(key, root);

        if (node == null)
            return false;

        TNode parent = node.getParent();

        node.setValue(null);
        node.setKey(null);

        return false;
    }

    public V predecessor(K key) {

        return null;
    }

    public V successor(K key) {

        return null;
    }

}
