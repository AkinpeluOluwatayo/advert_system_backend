package enterprise.elroi.exceptions.chatServiceExceptions;

public class NotAllowedToDeleteChatException extends GetChatByIdChatNotFoundException {
    public NotAllowedToDeleteChatException(String message) {
        super(message);
    }
}
