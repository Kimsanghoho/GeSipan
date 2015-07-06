package util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import bean.MemberBean;
import oracle.jdbc.OracleDriver;



public class JdbcTest {

	  
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        MemberBean bean = new MemberBean();
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "system";
        String pass = "oracle";
        
        // import 단축키 : CTRL + SHIFT + O
        OracleDriver od = new OracleDriver();
        Properties info = new Properties();
        
        
        info.put("user", user);
        info.put("password", pass);
        
        try {
            conn = od.connect(url, info);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from member");
            while (rs.next()) {
                bean.setId(rs.getString("MEMBERID"));
                bean.setAge(rs.getString("AGE"));
                bean.setPassword(rs.getString("PASSWORD"));
                bean.setName(rs.getString("NAME"));
                bean.setEmail(rs.getString("EMAIL"));
                
                System.out.print(bean.getId()+"\t");
                System.out.print(bean.getAge()+"\t");
                System.out.print(bean.getPassword()+"\t");
                System.out.print(bean.getName()+"\t");
                System.out.print(bean.getEmail()+"\t");
                System.out.println();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
}
