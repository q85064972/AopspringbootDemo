package com.sms.msgsend.demo.aliyunMsg;

/**
 * @author ${user}
 * @date 2020/12/18
 */
public class Util {

    public static void main(String[] args) {
        generateColumn();
    }

    /**
     * 生成列名，代替* 号
     */
    public static void generateColumn(){
        //
        String str = "organization_code\tid\torganization_name\tpassword\tencryption_method\tsend_method\tip\tcreate_by\tupdate_by\tcreate_date\tupdate_date\tis_validity\tis_delete";
        str = str.replaceAll("\t",",\n");
        System.out.println(str);
    }
}
