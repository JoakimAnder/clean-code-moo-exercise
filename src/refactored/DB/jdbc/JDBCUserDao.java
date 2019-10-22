package refactored.DB.jdbc;

import refactored.DB.UserDao;
import refactored.DB.UserNotFound;
import refactored.DB.models.INeedThisForSomeReason.User;

public class JDBCUserDao implements UserDao {
    private static PreparedStatement getUserByName;
    private static PreparedStatement getUserById;
    private static PreparedStatement createUser;
    private static PreparedStatement updateUser;
    private static PreparedStatement userExistsByName;
    private static PreparedStatement userExistsById;
    private Connection connection;


    public JDBCUserDao() {
        connection = Connection.createConnection("jdbc:mysql://localhost/MooDB","root","root");

        getUserByName = connection.prepareStatement("SELECT * FROM players WHERE name = ?");
        getUserById = connection.prepareStatement("SELECT * FROM players WHERE id = ?");
        createUser = connection.prepareStatement("INSERT INTO players (name) VALUES (?)");
        updateUser = connection.prepareStatement("UPDATE players SET name = ? WHERE id = ?");
        userExistsByName = connection.prepareStatement("SELECT COUNT(*) as count FROM players WHERE name = ?");
        userExistsById = connection.prepareStatement("SELECT COUNT(*) as count FROM players WHERE id = ?");
    }

    private String validateName(String name) {
        String nameCopy = name;

        for (int i = 1; userExists(nameCopy); i++) {
            nameCopy = name+i;
        }

        return nameCopy;
    }


    @Override
    public User getUserByName(String name) {
        ResultSet rs = getUserByName.setString(1, name).executeQuery();
        if(!rs.next())
            throw new UserNotFound();
        return JDBCConverter.toUser(rs);
    }

    @Override
    public User getUserById(int id) {
        ResultSet rs = getUserById.setInt(1, id).executeQuery();
        if(!rs.next())
            throw new UserNotFound();
        return JDBCConverter.toUser(rs);
    }

    @Override
    public User createUser(User user) {
        String name = validateName(user.getName());
        createUser.setString(1, name)
                .executeUpdate();

        return getUserByName(user.getName());
    }

    @Override
    public User updateUser(User user) {
        User oldUser = getUserById(user.getId());

        if(!user.getName().equals(oldUser.getName())) {
            String name = validateName(user.getName());
            updateUser.setString(1, name)
                    .setInt(2, user.getId())
                    .executeUpdate();
        }

        return getUserById(user.getId());
    }

    @Override
    public boolean userExists(String name) {
        ResultSet rs = userExistsByName.setString(1, name).executeQuery();
        return rs.getInt("count") > 0;
    }

    @Override
    public boolean userExists(int id) {
        ResultSet rs = userExistsById.setInt(1, id).executeQuery();
        return rs.getInt("count") > 0;
    }
}
