package don.bitlab.db;

import don.bitlab.models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDb extends DbConnector {

    public static Category getCategoryById(long categoryId) {
        Category category = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM final.categories WHERE id = ?"
            );
            statement.setLong(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setId(categoryId);
                category.setName(resultSet.getString("name"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }


    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM final.categories"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));

                categories.add(category);
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
