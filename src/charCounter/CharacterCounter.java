package charCounter;

/**
 * Created by Ilgiz on 20.10.2016.
 */
public class CharacterCounter {

    private final String EXCLUDE_CHARS = ":;<=>?@[\\]^_` .-*";
    private RBTree<Character, Integer> tree;

    public CharacterCounter() {
        tree = new RBTree();
    }

    private void removeExclude() {
        for (int i = 0; i < EXCLUDE_CHARS.length(); i++) {
            char c = EXCLUDE_CHARS.charAt(i);
            tree.delete(c);
        }
    }

    private void addToTree(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            c = Character.toLowerCase(c);

            Integer old = tree.insert(c, 1);

            if (old != null)
                tree.insert(c, ++old);
        }
    }

    public void count(String s) {
        addToTree(s);
        removeExclude();
    }

    public String giveStatistic() {
        StringBuilder sb = new StringBuilder();
        tree.traverse((character, integer) -> {
            sb.append(character);
            sb.append(":");
            sb.append(integer);
            sb.append(" ");
        });
        return sb.toString();
    }
}
