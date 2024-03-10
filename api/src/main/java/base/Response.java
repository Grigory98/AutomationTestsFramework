package base;

public class Response {
    public static void checkResultCode(int code) {
        switch (code) {
            case 200: case 201: case 202: case 203: case 204: case 205: case 206:
                break;
            default:
                throw new RuntimeException("Код ответа: " + code);
        }
    }
}
