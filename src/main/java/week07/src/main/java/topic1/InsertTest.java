package topic1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**

 * @description:
 */
public class InsertTest {
    public static void main(String[] args) throws SQLException {
      insertTest();
    }

    private static void insertTest() throws SQLException {
        String sql = "insert into t_order(`id`, `orderNo`, `clientId`, `desc`, `addressId`, `createTime`, `state`) values(?,?,?,?,?,?,?)";
        Connection con = DataSourceConfig.getConnection();
        con.setAutoCommit(false);
        PreparedStatement stmt =  con.prepareStatement(sql);
        for (int i = 1; i <=10000; i++) {
            stmt.setInt(1,i);
            stmt.setString(2,"orderNo" );
            stmt.setString(3,"clientId" );
            stmt.setString(4,"desc");
            stmt.setString(5,"address: shanghai");
            stmt.setDate(6,new Date(System.currentTimeMillis()));
            stmt.setString(7,"init");
            stmt.addBatch();
            if (i % 10000 ==0) {
                stmt.executeBatch();
                stmt.clearBatch();
            }
        }
        con.commit();
        stmt.close();
        con.close();
    }
}
