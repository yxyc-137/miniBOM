package com.example.minibom.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    public static final String SUCCESS = "200";
    public static final String ERROR = "500";

    private String code;//运行结果
    private String msg;//告诉前端错误的原因
    private Object data;//后台所携带的数据

    public static Result success(){//无参返回正确
        return new Result(SUCCESS, "成功",null);
    }
    public static Result success(Object data){//有参返回正确
        return new Result(SUCCESS, "成功",data);
    }
    public static Result error(String code,String msg){ //返回失败
        return new Result(code, msg,null);
    }
    public static Result error(String msg){ //默认一个错误类
        return new Result(ERROR, msg,null);
    }
}
