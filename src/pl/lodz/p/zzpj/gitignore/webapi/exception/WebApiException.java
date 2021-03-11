package pl.lodz.p.zzpj.gitignore.webapi.exception;

public class WebApiException extends Exception {
    public WebApiException() {
        super();
    }

    public WebApiException(String message) {
        super(message);
    }

    public WebApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebApiException(Throwable cause) {
        super(cause);
    }

    protected WebApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
