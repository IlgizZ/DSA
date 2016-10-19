/**
 * Created by Ilgiz on 27.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        RBTree<Character, Integer> rbTree = new RBTree();
        char c = 'n';

        for (int i = 0; i < 10; i++) {
            rbTree.insert(c, i);
            c--;
        }

        rbTree.traverse((character, integer) -> {
            System.out.println(character + ":" + integer);
        });
    }
}
