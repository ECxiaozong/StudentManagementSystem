import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public static @NotNull ArrayList<ClassGroup> getClassGroupsList() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system", "root", "123456");

        String sql = "select * from class_group";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<ClassGroup> classGroups = new ArrayList<ClassGroup>();

        while (rs.next()) {
            ClassGroup classGroup = new ClassGroup();
            classGroup.setId(rs.getInt("id"));
            classGroup.setName(rs.getString("name"));
            classGroup.setNumber(rs.getInt("number"));
            classGroups.add(classGroup);
        }

        rs.close();
        ps.close();
        conn.close();
        return classGroups;
    }
}
