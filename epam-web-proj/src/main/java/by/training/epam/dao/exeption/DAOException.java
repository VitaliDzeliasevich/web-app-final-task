package by.training.epam.dao.exeption;

public class DAOException extends Exception{

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Exception e){
        super(e);
    }

    public DAOException(String message, Exception e){
        super(message, e);
    }
}
