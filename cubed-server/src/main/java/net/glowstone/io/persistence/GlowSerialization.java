package net.glowstone.io.persistence;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GlowSerialization {

    public static <T> T serialize(ResultSet set, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        T obj = clazz.newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            switch (field.getGenericType().getTypeName()) {
                case "String":
                    obj.getClass().getDeclaredField(field.getName()).set(obj, set.getString(field.getName()));
                    field.set(clazz, set.getString(field.getName()));
                    break;
                case "Integer":
                    field.set(clazz, set.getInt(field.getName()));
                    break;
            }
        }
        return obj;
    }

}
