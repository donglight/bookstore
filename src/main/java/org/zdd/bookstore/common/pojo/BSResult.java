package org.zdd.bookstore.common.pojo;

public class BSResult<T> {

    private Integer code;
    private String message;
    private T data;

    public BSResult() {
    }

    public BSResult(T data) {
        this(200,"OK",data);
    }

    public BSResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BSResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
