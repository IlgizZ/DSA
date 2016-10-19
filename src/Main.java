import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilgiz on 27.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        RBTree<Character, Integer> rbTree = new RBTree();
        int diff = 'z' - 'a' + 1;
        char a = 'a';
        List<Character> alphabet = new ArrayList();
        for (char i = 'a'; i <= 'z' ; i++) {
            alphabet.add(i);

        }


        while (!alphabet.isEmpty()){
            char rand = (char)(a + (char)(diff * Math.random()));

            while (!alphabet.contains(rand))
                rand = (char)(a + (char)(diff * Math.random()));

            rbTree.insert(rand, (int)(100000 * Math.random()));

            alphabet.remove(new Character(rand));
        }

//        System.out.println(rbTree.size());

//        rbTree.traverse((character, integer) -> {
//            System.out.println(character + ":" + integer);
//        });

//        rbTree.delete('f');
//        for (char c : new char[]{'k', 's', 'b', 't', 'a', 'h', 'x', 'j'}) {
//            rbTree.delete(c);
//        }

        while (!rbTree.isEmpty()){
            char rand = (char)(a + (char)(diff * Math.random()));

            while (rbTree.contains(rand) == null)
                rand = (char)(a + (char)(diff * Math.random()));

            System.out.println(rbTree.size() + " deliting " + rand);
            rbTree.delete(rand);
        }



    }
}
