package refactored.business.select;

import refactored.DB.models.INeedThisForSomeReason.User;
import refactored.UI.window.Window;
import refactored.DB.GameDao;
import refactored.DB.jdbc.JDBCGameDao;
import refactored.business.Menu;

public class LoginMenu implements Menu<User> {

    GameDao dao = new JDBCGameDao();

    @Override
    public User select(Window window) {
        return null;
//        String username = window.popup().input("What's your username?");
//        if(username == null)
//            return (User) Menu.NO_SELECTED;
//        if (userExists(username)) {
//            return getUser(username);
//
//        } else {
//            if (window.popup().confirm("User with name '"+username+"' doesn't exist.\nCreate new user?"))
//                return createUser(username);
//            else
//                return select(window);
//        }
    }


//    private boolean userExists(String username) {
//        return dao.userExists(username);
//    }
//
//    private User getUser(String username) {
//        return dao.getUserByName(username);
//    }
//
//    private User createUser(String username) {
//        User user = new User(username);
//        return dao.createUser(user);
//    }


}
