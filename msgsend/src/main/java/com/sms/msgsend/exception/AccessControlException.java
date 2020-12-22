package com.sms.msgsend.exception;

/**
 * desc 权限异常
 * @author ${user}
 * @date 2020/12/18
 */
public class AccessControlException extends RuntimeException {
    public AccessControlException() { super();
    }
    public AccessControlException(String message) {
        super(message);
    }
    public AccessControlException(String message, Throwable cause) {
        super(message, cause);
    }
}
