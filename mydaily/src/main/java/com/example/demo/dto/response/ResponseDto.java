package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;

    // 메서드에서 제너릭타입을 매개변수로 사용할 때는 반환타입 앞에 제너릭을 꼭 적어줘야함
    public static <D> ResponseDto<D> setSuccess(D data) {
        return ResponseDto.set(true, "Success", data);
    }

    public static <D> ResponseDto<D> setFail(String message) {
        return ResponseDto.set(false, message, null);
    }
}
