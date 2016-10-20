package charCounter;

/**
 * Created by Ilgiz on 20.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            String text = MyReaderWriter.read("input.txt");
            CharacterCounter counter = new CharacterCounter();
            counter.count(text);
            MyReaderWriter.writeToFile("output.txt", counter.giveStatistic());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
