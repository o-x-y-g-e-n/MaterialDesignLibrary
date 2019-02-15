package com.gc.materialdesigndemo.temp;
public class AppiumException extends Exception {

    public AppiumException() {
        super();
    }

    public AppiumException(String reason) {
        super(reason);
    }

    public AppiumException(Throwable e) {
        super(e);
    }

    public AppiumException(String reason, Throwable e) {
        super(reason, e);
    }
}