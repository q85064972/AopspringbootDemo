package com.sms.msgsend.exception;

/**
 * desc 加密异常
 * @author ${user}
 * @date 2020/12/18
 */
public class EncryptionException extends RuntimeException {
    public EncryptionException() { super();
    }
    public EncryptionException(String message) {
        super(message);
    }
    public EncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
