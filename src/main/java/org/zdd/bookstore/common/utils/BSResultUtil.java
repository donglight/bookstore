package org.zdd.bookstore.common.utils;

import org.zdd.bookstore.common.pojo.BSResult;

public class BSResultUtil {

    public static BSResult success(Object data){
        return new BSResult<>(data);
    }

    public static BSResult success(){
        return new BSResult<>(null);
    }

    public static BSResult build(Integer code,String message,Object data){
        return new BSResult(code,message,data);
    }

    public static BSResult build(Integer code,String message){
        return new BSResult(code,message,null);
    }
}
