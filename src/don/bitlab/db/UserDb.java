package don.bitlab.db;

import don.bitlab.models.Category;
import don.bitlab.models.Role;
import don.bitlab.models.User;
import don.bitlab.models.constant.ColumnConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDb extends DbConnector {
    public static User getUserByEmail(String email) {
        User user = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT u.*, r.name FROM final.users u " +
                            "INNER JOIN final.roles r ON u.role_id = r.id " +
                            "WHERE email = ?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(email);
                user.setId(resultSet.getLong(ColumnConstant.USER_ID));
                user.setPassword(resultSet.getString(ColumnConstant.USER_PASSWORD));
                user.setFullName(resultSet.getString(ColumnConstant.USER_FULL_NAME));

                Role role = new Role();
                role.setId(resultSet.getLong(ColumnConstant.USER_ROLE_ID));
                role.setName(resultSet.getString(ColumnConstant.ROLE_NAME));
                user.setRole(role);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserById(long userId) {
        User user = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM final.users WHERE id = ?"
            );
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString(ColumnConstant.USER_ID));
                user.setId(userId);
                user.setPassword(resultSet.getString(ColumnConstant.USER_PASSWORD));
                user.setFullName(resultSet.getString(ColumnConstant.USER_FULL_NAME));

                Role role = RoleDb.getRoleById(resultSet.getLong(ColumnConstant.USER_ROLE_ID));
                user.setRole(role);

                user.setRole(role);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
