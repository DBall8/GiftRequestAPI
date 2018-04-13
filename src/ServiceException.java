public class ServiceException extends Throwable {

    String message;

    public ServiceException(String message) {
        this.message = message;
    }
}
