package main;

import dao.AbstractDAO;
import dao.AnalysisDAO;
import dao.DAOFactory;
import dao.UserDAO;
import dao.exeption.DAOException;
import dao.impl.AnalysisDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Analysis;
import entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();

        UserDAO userDAO = daoFactory.getUserDAO();
//        User user = new User("login1","pass","name", "surname");
//        user.setId(1);
//        user.setRoleId(1);
//        user.setDepartmentsId(1);
        List<User> users= userDAO.getAll();           ////GETTING ALL USERS
        User user = userDAO.getEntityById(1);            ////GETTING BY ID
//        boolean created = dao.create(user);       ////CREATING USER
//        boolean deleted = dao.delete(5);          ////DELETING USER
//          boolean updated = dao.update(user);      ////UPDATING USER
//        for (User x : users) {
//            System.out.println(x.toString());
//        }
        daoFactory.disposePool();
        System.out.println(user.toString());
        System.out.println(users.toString());





  }
}
