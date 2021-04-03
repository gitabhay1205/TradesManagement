package com.config.boot.exception;

public class InvalidTradeException extends RuntimeException {


	private static final long serialVersionUID = -2137038525776599235L;

    public InvalidTradeException(final String id) {
        super(id);
    }

}
