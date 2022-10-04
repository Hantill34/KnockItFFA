package net.problemzone.knockit.exceptions;

public class InvalidConfigException extends RuntimeException {

    public InvalidConfigException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public InvalidConfigException(String errorMessage) {
        super(errorMessage);
    }

}
