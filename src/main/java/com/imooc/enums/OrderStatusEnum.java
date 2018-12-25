package com.imooc.enums;


public enum OrderStatusEnum implements  CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CALCEL(2,"已取消");
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    OrderStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode(){
        return this.code;
    }
}
