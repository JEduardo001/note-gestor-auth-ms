package com.SwSOFTWARE.authMs.dto.api;

public record DtoResponseApiWithData<T>(
    Integer status,
    String message,
    T data
) {
}
