package com.github.solanej.view;

/**
 * ResponseData
 *
 * @since 2025/3/20 08:03
 */
public class ResponseData {

    private String message;

    private int code;

    private Object data;

    public ResponseData() {
    }

    public ResponseData(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static ResponseData success(Object data) {
        return new ResponseData("success", 200, data);
    }

    public static ResponseData failed(String message, int code) {
        return new ResponseData(message, code, null);
    }

    public static ResponseData error(int code) {
        return new ResponseData("error", code, null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
