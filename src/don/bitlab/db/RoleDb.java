package don.bitlab.db;

import don.bitlab.models.Role;

import java.sql.ResultSet;

public class RoleDb extends DbConnector {

    public static Role getRoleById(Long id) {
        Role role = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM final.roles"
            );
            ResultSet resultSet = statement.executeQuery();
             if(resultSet.next()){
                 role = new Role();

                 role.setId(id);
                 role.setName(resultSet.getString("name"));
             }
             statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
