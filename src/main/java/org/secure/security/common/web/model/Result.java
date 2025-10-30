package org.secure.security.common.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * 响应信息主体
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    public static final String SUCCESS_CODE = "success";
    public static final String FAIL_CODE = "fail";
    private String code;
    private String message;
    private Object data;

    public static Result success() {
        return Result.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_CODE)
                .data(null)
                .build();
    }

    public static Result success(String message) {
        return Result.builder()
                .code(SUCCESS_CODE)
                .message(message)
                .data(null)
                .build();
    }

    public static Result row(int row) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("row", row);
        if (row > 0) {
            return data(data);
        } else {
            return fail(data);
        }
    }

    public static Result data(Object data) {
        return Result.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_CODE)
                .data(data)
                .build();
    }

    public static Result data(Object data, String responseMessage) {
        return Result.builder()
                .code(SUCCESS_CODE)
                .message(responseMessage)
                .data(data)
                .build();
    }

    public static Result fail(Object data, String msg) {
        return Result.builder()
                .code(FAIL_CODE)
                .message(msg)
                .data(data)
                .build();
    }

    public static Result fail(String msg) {
        return Result.builder()
                .code(FAIL_CODE)
                .message(msg)
                .data(null)
                .build();
    }

    public static Result fail(Object data) {
        return Result.builder()
                .code(FAIL_CODE)
                .message(FAIL_CODE)
                .data(data)
                .build();
    }
}
