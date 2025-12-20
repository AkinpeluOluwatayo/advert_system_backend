package enterprise.elroi.exceptions.chatServiceExceptions;

public class DeleteChatNoChatFoundException extends GetChatByIdChatNotFoundException {
    public DeleteChatNoChatFoundException(String message) {
        super(message);
    }
}
