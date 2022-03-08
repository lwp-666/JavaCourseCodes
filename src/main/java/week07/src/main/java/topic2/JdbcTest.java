package topic2;

import topic1.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**

 * @description:
 */
public class JdbcTest {

    public static void main(String[] args) throws SQLException {
      add();
    }

    public static void add() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

     try {
         String sql = "insert into person(name, age) value(?,?);";
          conn = DataSourceConfig.getMsConnection();
         if (conn != null) {
             stmt = conn.prepareStatement(sql);
             stmt.setString(1, "zhangsan");
             stmt.setInt(2, 20);
             stmt.execute();
         }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         try {
           if (stmt != null) {
               stmt.close();
           }
            if (conn != null) {
                conn.close();
            }
         } catch ( Exception e) {
         }
     }
    }

    private static void getAll() {
        String sql = "select * from student";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DataSourceConfig.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);
                int num = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    for (int i = 0; i < num; i++) {
                        System.out.println(rs.getString(i));
                    }
                }
            }
        } catch (Exception e) {

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch ( Exception e) {
            }
        }
    }
}
