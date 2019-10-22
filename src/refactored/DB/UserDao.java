package refactored.DB;

import refactored.DB.models.INeedThisForSomeReason.User;

public interface UserDao {
    User getUserByName(String name);
    User getUserById(int id);
    User createUser(User user);
    User updateUser(User user);
    boolean userExists(String name);
    boolean userExists(int id);
}
