package refactored2.Integration.Jdbc;

import refactored2.Integration.DBController;
import refactored2.Integration.Exceptions.GameNotFound;
import refactored2.Integration.Exceptions.NameIsTaken;
import refactored2.Integration.Exceptions.UserNotFound;
import refactored2.Integration.models.Game;
import refactored2.Integration.models.Score;
import refactored2.Integration.models.User;

import java.time.LocalDateTime;
import java.util.List;

public class JdbcController implements DBController {

//    @Inject
    private Dao dao = JdbcDao.createDao();
    private JdbcValidator validator = new JdbcValidator();


    public boolean isUsedGameName(String name) {
        return validator.isUsedName(name, dao::getGame);
    }
    public boolean isUsedUserName(String name) {
        return validator.isUsedName(name, dao::getUser);
    }


    @Override
    public User addUser(User newUser) {
        String name = newUser.getName();
        if(isUsedUserName(name))
            throw new NameIsTaken(name);

        dao.addUser(name);
        return dao.getUser(name);
    }

    @Override
    public User login(User user) {
        String name = user.getName();
        if(isUsedUserName(name))
            return dao.getUser(name);
        else
            throw new UserNotFound();
    }

    @Override
    public Game getGame(String name) {
        if(isUsedGameName(name))
            return dao.getGame(name);
        else
            throw new GameNotFound();
    }

    @Override
    public void addScore(Score score) {
        Game game = score.getGame();
        User user = score.getUser();
        int points = score.getPoints();
        LocalDateTime dateTime = score.getDateTime();

        if(!validator.isUsedId(game.getId(), dao::getGame))
            throw new GameNotFound();

        if (!validator.isUsedId(user.getId(), dao::getUser))
            throw new UserNotFound();

        dao.addScore(game.getId(), user.getId(), points, dateTime);
    }

    @Override
    public List<Score> getScores(Game game) {
        int id = game.getId();
        if(validator.isUsedId(id, dao::getGame))
            return dao.getScores(id);
        else
            throw new GameNotFound();
    }
}
