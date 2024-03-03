package generates;

import java.util.Random;

public class TestDataGenerate {
    private static final Random random = new Random();

    public static String generateString(int length) {
        int leftLimit = 'a';
        int rightLimit = 'z';

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int generateInt(final int min, final int max) {
        return random.nextInt(max - min + 1);
    }
}
