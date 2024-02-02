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

    public Response(int code, T object) {
        this.code = code;
        this.object = object;
    }
}
