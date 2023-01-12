package com.summer.enums;


import java.lang.reflect.Method;

/**
 * 描述:订单支付状态
 *
 * @author tubo
 * @date 2022/1/11 10:34
**/
public enum OdrderPayStatus {

    NO("未支付", 0),

    YES("已支付", 1);

    private String nodeName;
    private Integer nodeCode;

    OdrderPayStatus(String nodeName, Integer nodeCode){
        this.nodeName = nodeName;
        this.nodeCode = nodeCode;
    }

    /**
     * 通过code 获取值
     * @param clazz
     * @param code
     * @return
     */
    public static Object getEnumValue(Class clazz, Object code){
        Object[] enumConstants = clazz.getEnumConstants();
        try {
            for(Object object : enumConstants){
                //获取对象的公开方法，参数标识和方法名称
                Method codeMethod = clazz.getMethod("getNodeCode");
                Method nameMethod = clazz.getMethod("getNodeName");
                if(code.equals(codeMethod.invoke(object))){
                    return nameMethod.invoke(object);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String getNodeName() {
        return nodeName;
    }
    public Integer getNodeCode() {
        return nodeCode;
    }
}
