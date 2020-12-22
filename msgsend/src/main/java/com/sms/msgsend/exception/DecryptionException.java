package com.sms.msgsend.exception;

/**
 * desc 解密异常
 * @author ${user}
 * @date 2020/12/18
 */
public class DecryptionException extends RuntimeException {
    public DecryptionException() { super();
    }
    public DecryptionException(String message) {
        super(message);
    }
    public DecryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
