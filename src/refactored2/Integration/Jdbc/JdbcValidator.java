package refactored2.Integration.Jdbc;

import refactored2.Integration.Exceptions.*;

import java.util.function.Function;

public class JdbcValidator {

    public boolean isUsedName(String name, Function<String, Object> getObject) {
        try {
            Object o = getObject.apply(name);
            return o != null;
        } catch (MisuseOfDBController e) {
            return false;
        }
    }

    public boolean isUsedId(int id, Function<Integer, Object> getObject) {
        if (id < 1) return false;
        try {
            Object o = getObject.apply(id);
            return o != null;
        } catch (MisuseOfDBController e) {
            return false;
        }
    }
}
