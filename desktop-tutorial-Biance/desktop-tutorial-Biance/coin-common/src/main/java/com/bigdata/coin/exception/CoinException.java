package com.bigdata.coin.exception;


import com.bigdata.coin.common.CoinCode;
import lombok.Getter;

@Getter
public class CoinException extends RuntimeException {

    private String errorCode;
    private String message;
    private Object[] params;

    public CoinException() {
    }

    public CoinException(Throwable cause) {
        super(cause);
    }

    public CoinException(String message) {
        super(message);
        this.message = message;
    }

    public CoinException(String errorCode, String message) {
        this(errorCode, message, null, null);
    }

    public CoinException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public CoinException(String errorCode, String message, Throwable cause) {
        this(errorCode, message, null, cause);
    }

    public CoinException(String message, Object[] params, Throwable cause) {
        this(ErrorCode.GENERAL.getCode(), message, params, cause);
    }

    public CoinException(String errorCode, String message, Object[] params, Throwable cause) {
        super(message, cause);
        this.params = params;
        this.errorCode = errorCode;
        this.message = message;
    }

    public CoinException(CoinCode errCode) {
        this(errCode.getCode(), null, null, null);
    }

    public CoinException(CoinCode errCode, String message) {
        this(errCode.getCode(), message, null, null);
    }

    public CoinException(CoinCode errCode, String message, Throwable cause) {
            this(errCode.getCode(), message, null, cause);
    }

    public CoinException(CoinCode errorCode, String message, Object[] params, Throwable cause) {
        this(errorCode.getCode(), message, params, cause);
    }
}
