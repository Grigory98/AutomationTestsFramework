package base;

public class Response<T> {
    private final int code;

    private final T object;

    public int getCode() {
        return code;
    }

    public T getObject() {
        return object;
    }

    public void checkResultCode() {
        switch (code) {
            case 200: case 201: case 202: case 203: case 204: case 205: case 206:
                break;
            default:
                throw new RuntimeException("Код ответа: " + code);
        }
    }

    public Response(int code, T object) {
        this.code = code;
        this.object = object;
    }
}
