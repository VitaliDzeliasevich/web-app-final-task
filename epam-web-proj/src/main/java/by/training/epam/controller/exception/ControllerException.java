package by.training.epam.controller.exception;

public class ControllerException extends Exception{

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Exception e){
        super(e);
    }

    public ControllerException(String message, Exception e){
        super(message, e);
    }
}
