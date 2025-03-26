package com.github.solanej.view;

/**
 * ResponseData
 *
 * @since 2025/3/20 08:03
 */
public class ResponseData {

    private String message;

    private short code;

    private Object data;

    public ResponseData() {
    }

    public ResponseData(String message, short code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static ResponseData success(Object data) {
        return new ResponseData("success", (short) 200, data);
    }

    public static ResponseData failed(String message) {
        return new ResponseData(message, (short) 500, null);
    }

    public static ResponseData error() {
        return new ResponseData("error", (short) 500, null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
