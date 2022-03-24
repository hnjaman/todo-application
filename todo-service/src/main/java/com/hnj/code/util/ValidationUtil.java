package com.hnj.code.util;

import com.hnj.code.exception.AppException;
import org.springframework.http.HttpStatus;

public class ValidationUtil {
    private ValidationUtil() {}

    public static void todoIdValidation(Integer id){
        if(id == null){
            throw new AppException(StringConst.TODO_ID_CAN_NOT_BE_NULL, HttpStatus.BAD_REQUEST);
        }
    }
}
