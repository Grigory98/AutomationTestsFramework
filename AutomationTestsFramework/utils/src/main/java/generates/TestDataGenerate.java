package generates;

import java.util.Random;

public class TestDataGenerate {
    public static String generateString(int length) {
        Random random = new Random();
        int leftLimit = 97; // буква 'a'
        int rightLimit = 122; // буква 'z'

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int generateInt(final int min, final int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1);
    }
}
