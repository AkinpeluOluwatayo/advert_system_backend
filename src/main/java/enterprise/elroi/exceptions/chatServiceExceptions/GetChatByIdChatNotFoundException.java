package enterprise.elroi.exceptions.chatServiceExceptions;

public class GetChatByIdChatNotFoundException extends RuntimeException {
    public GetChatByIdChatNotFoundException(String message) {
        super(message);
    }
}
