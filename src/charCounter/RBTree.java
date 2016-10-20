package charCounter;

/**
 * Created by Ilgiz on 17.10.2016.
 */
public class RBTree<K extends Comparable, V> {

    private enum Color {
        RED, BLACK;
    }

    private class Node {
        Color color;
        K key;
        V value;
        Node left;
        Node right;

        Node parent;

        public Node(K key, V value, Color color) {
            this.color = color;
            this.key = key;
            this.value = value;
            this.left = nil;
            this.right = nil;
            this.parent = nil;
        }

        public Node(K key, V value) {
            this(key, value, Color.RED);
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
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

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

    }

    private Node nil;
    private Node root;
    private int size;

    public RBTree() {
        nil = new Node(null, null, Color.BLACK);
        root = nil;
    }

    private boolean notNull(Node node) {
        return !node.equals(nil);
    }

    /**
     * Search node with key K from @param node
     * @param node from node
     * @param key
     * @return node with key key or null otherwise
     */
    private Node search(Node node, K key) {
        while (notNull(node)) {
            int comp = compareTo(key, node.key);
            switch (comp) {
                case 0:
                    return node;
                case -1:
                    node = node.left;
                    break;
                case 1:
                    node = node.right;
            }
        }
        return node;
    }

    /**
     * Search min node from @param node
     * @param node from node
     * @return min node with
     */
    private Node min(Node node) {
        while (notNull(node.left))
            node = node.left;
        return node;
    }

    /**
     * Search max node from @param node
     * @param node from node
     * @return max node
     */
    private Node max(Node node) {
        while (notNull(node.right))
            node = node.right;
        return node;
    }

    /**
     * Search previous node of @param node
     * @param node
     * @return previous node or null otherwise
     */
    private Node predecessor(Node node) {
        if (notNull(node.left))
            return max(node.left);

        Node parent = node.parent;
        while (notNull(parent) && parent.left.equals(node)) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }

    /**
     * Search next node after @param node
     * @param node
     * @return next node or null otherwise
     */
    private Node successor(Node node) {
        if (notNull(node.right))
            return min(node.right);

        Node parent = node.parent;
        while (notNull(parent) && parent.right.equals(node)) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }

    private void leftRotate(Node node) {
        Node rightChild = node.right;
        node.setRight(rightChild.left);

        if (notNull(rightChild.left)) {
            rightChild.left.setParent(node);
        }

        rightChild.setParent(node.parent);

        if (node.parent.equals(nil)) {
            root = rightChild;
        } else if (node.equals(node.parent.left)) {
            node.parent.setLeft(rightChild);
        } else {
            node.parent.setRight(rightChild);
        }

        rightChild.setLeft(node);
        node.setParent(rightChild);
    }

    private void rightRotate(Node node) {
        Node leftChild = node.left;
        node.setLeft(leftChild.right);

        if (notNull(leftChild.right)) {
            leftChild.right.setParent(node);
        }

        leftChild.setParent(node.parent);

        if (node.parent.equals(nil)) {
            root = leftChild;
        } else if (node.parent.right.equals(node)) {
            node.parent.setRight(leftChild);
        } else {
            node.parent.setLeft(leftChild);
        }

        leftChild.setRight(node);
        node.setParent(leftChild);
    }

    /**
     * @param k1
     * @param k2
     * @return -1 if n1 less than n2, 0 if equals, 1 if greater.
     */
    private int compareTo(K k1, K k2) {
        int comp = k1.compareTo(k2);

        if (comp == 0)
            return 0;

        return comp > 0 ? 1 : -1;
    }

    private V insert(Node node) {
        Node currentNode = root;
        Node parent = nil;
        int comp = 0;

        while (notNull(currentNode)) {
            parent = currentNode;
            comp = compareTo(node.key, currentNode.key);
            switch (comp) {
                case 0:
                    V old = currentNode.getValue();
                    currentNode.setValue(node.getValue());
                    return old;
                case 1:
                    currentNode = currentNode.right;
                    break;
                case -1:
                    currentNode = currentNode.left;
            }
        }

        size++;
        node.setParent(parent);

        if (parent.equals(nil))
            root = node;
        else
            comp = compareTo(node.key, parent.key);

        if (comp == -1)
            parent.setLeft(node);
        else if (comp == 1)
            parent.setRight(node);

        insertFixUp(node);

        return null;
    }

    private void insertFixUp(Node node) {
        while (node.parent.color == Color.RED) {
            Node grand = node.parent.parent;

            if (node.parent.equals(grand.left)) {
                Node uncle = grand.right;

                if (uncle.color == Color.RED) {
                    node.parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grand.setColor(Color.RED);
                    node = grand;
                } else {
                    if (node.equals(node.parent.right)) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.setColor(Color.BLACK);
                    grand.setColor(Color.RED);
                    rightRotate(grand);
                }
            } else {
                Node uncle = grand.left;

                if (uncle.color == Color.RED) {
                    node.parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grand.setColor(Color.RED);
                    node = grand;
                } else {
                    if (node.equals(node.parent.left)) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.setColor(Color.BLACK);
                    grand.setColor(Color.RED);
                    leftRotate(grand);
                }
            }
        }

        root.setColor(Color.BLACK);
    }

    private void transplant(Node node1, Node node2){
        Node parent1 = node1.parent;

        if (parent1.equals(nil))
            root = node2;
        else if (node1.equals(parent1.left))
            parent1.left = node2;
        else
            parent1.right = node2;

        node2.parent = parent1;
    }

    private void delete(Node node) {
        Node replacement = node;
        Color replacementColor = node.color;
        Node replacementChild = nil;

        if (node.left.equals(nil)){
            replacementChild = node.right;
            transplant(node, replacementChild);
        } else if (node.right.equals(nil)){
            replacementChild = node.left;
            transplant(node, replacementChild);
        } else {
            replacement = successor(node);
            replacementColor = replacement.color;
            replacementChild = replacement.right;

            if (replacement.parent.equals(node)){
                replacementChild.parent = replacement;
            } else {
                transplant(replacement, replacementChild);
                replacement.right = node.right;
                replacement.right.parent = replacement;
            }

            transplant(node, replacement);
            replacement.left = node.left;
            replacement.left.parent = replacement;
            replacement.color = node.color;
        }
        if (replacementColor == Color.BLACK)
            deleteFixUp(replacementChild);

        if (replacementChild.equals(nil))
            nil.setParent(null);
    }

    private void deleteFixUp(Node x) {
        while (!(x.equals(root)) && x.color == Color.BLACK){
            if (x.equals(x.parent.left)){
                Node bro = x.parent.right;

                if (bro.color == Color.RED){
                    bro.setColor(Color.BLACK);
                    x.parent.setColor(Color.RED);
                    leftRotate(x.parent);
                    bro = x.parent.right;
                }

                if (bro.left.color == Color.BLACK && bro.right.color == Color.BLACK){
                    bro.setColor(Color.RED);
                    x = x.parent;
                } else {
                    if (bro.right.color == Color.BLACK){
                        bro.left.setColor(Color.BLACK);
                        bro.setColor(Color.RED);
                        rightRotate(bro);
                        bro = x.parent.right;
                    }
                    bro.setColor(x.parent.color);
                    x.parent.setColor(Color.BLACK);
                    bro.right.setColor(Color.BLACK);
                    leftRotate(x.parent);
                    x = root;
                }

            } else {

                Node bro = x.parent.left;

                if (bro.color == Color.RED){
                    bro.setColor(Color.BLACK);
                    x.parent.setColor(Color.RED);
                    rightRotate(x.parent);
                    bro = x.parent.left;
                }

                if (bro.right.color == Color.BLACK && bro.left.color == Color.BLACK ){
                    bro.setColor(Color.RED);
                    x = x.parent;
                } else {
                    //right eq nil ??
                    if (bro.left.color == Color.BLACK){
                        bro.right.setColor(Color.BLACK);
                        bro.setColor(Color.RED);
                        leftRotate(bro);
                        bro = x.parent.left;
                    }
                    bro.setColor(x.parent.color);
                    x.parent.setColor(Color.BLACK);
                    bro.left.setColor(Color.BLACK);
                    rightRotate(x.parent);
                    x = root;
                }

            }
        }

        x.color = Color.BLACK;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public V contains(K key){
        Node node = search(root, key);

        return node.equals(nil) ? null : node.value;
    }

    public V insert(K key, V value) {
        Node node = new Node(key, value);
        return insert(node);
    }

    public V delete(K key) {
        Node node = search(root, key);
        V result = node.value;

        if (node.equals(nil))
            return null;

        delete(node);

        size--;

        return result;
    }

    public void traverse(java.util.function.BiConsumer<K, V> visitor) {
        Node node = min(root);

        while (notNull(node)) {
            visitor.accept(node.key, node.value);
            node = successor(node);
        }
    }

    public void traverse(java.util.function.BiConsumer visitor, K from, K to) {
        if (root.equals(nil))
            return;

        Node start = search(root, from);
        Node end = search(root, to);

        if (start.equals(nil))
            throw new RuntimeException("Node with key " + from + " not found.");
        if (end.equals(nil))
            throw new RuntimeException("Node with key " + to + " not found.");

        while (!start.equals(end)){
            visitor.accept(start.key, start.value);
            start = successor(start);
        }

        visitor.accept(end.key, end.value);
    }

}
