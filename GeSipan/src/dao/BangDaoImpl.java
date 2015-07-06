package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.BangBean;
import util.DBmanager;

public class BangDaoImpl implements CommonDAO{
	Connection conn = null;
	PreparedStatement pstmt = null;  
	Statement stmt = null;  
	ResultSet rs = null; 
	String sql = "";
	BangBean bean = new BangBean();
	
	private static BangDaoImpl bangDAO = new BangDaoImpl();

	private BangDaoImpl() {
		// 단위 테스트가 끝나고 프로젝트가 완성되면 걷어 낼 부분
		conn = DBmanager.getConnection();
	}

	public static BangDaoImpl getInstance() {
		return bangDAO;
	}

	// 현재는 작동하지 않지만 위 DBmanager 를 걷어내는 순간
	// 작동함. 미리 설정함.
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		return conn;
	}
	
	@Override
	public int insert(Object obj) {
		int result = 0;
		String sql = "insert into GUESTBOOK (REGISTER,NAME,EMAIL,PASSWORD,CONTENT) "+
            "values (?,?,?,?,?)";
		try {            
            pstmt = conn.prepareStatement(sql);
            
            
            pstmt.setString(1, String.valueOf(bean.getRegister()));
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getEmail());
            pstmt.setString(4, bean.getPassword());
            pstmt.setString(5, null);
            result = pstmt.executeUpdate();
            
        } catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
            if (conn != null) try { conn.close(); } catch(Exception ex) {}
        }
		return result;
	}

	@Override
	public int count() {
		int count = 0;  // try내부에는 변수 선언하지 않는다(반복문도 마찬가지)
		try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select count(*) from GUESTBOOK");
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch(Exception ex) {
           ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception ex) {}
            if (stmt != null) try { stmt.close(); } catch(Exception ex) {}
            if (conn != null) try { conn.close(); } catch(Exception ex) {}
        }
		return count;
	}

	@Override
	public Object getElementById(String id) {
		Object obj = null;
		 try {
	            pstmt = conn.prepareStatement(
	            "select * from GUESTBOOK where GUESTBOOK_ID = ? ");
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                bean.setId(rs.getInt("GUESTBOOK_ID"));
	            //  bean.setRegister(rs.getTimestamp("REGISTER"));
	                bean.setName(rs.getString("NAME"));
	                bean.setEmail(rs.getString("EMAIL"));
	                bean.setPassword(rs.getString("PASSWORD"));
	            } 
	        } catch(Exception ex) {
	        	ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(Exception ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return obj;
	}



	@Override
	public List<Object> list() {
		List<Object> list = new ArrayList<Object>();
		int startRow=0, endRow=0;
		int pageParam = endRow-startRow+1;
		try {
            pstmt = conn.prepareStatement(
            "select * from GUESTBOOK order by GUESTBOOK_ID desc "+
            "limit ?, ?" );
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, pageParam);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            
            	// 페이징 처리 필요
            
            } else {

            }
        } catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
            if (conn != null) try { conn.close(); } catch(Exception ex) {}
        }
		return list;
	}

	@Override
	public int update(Object obj) {
		int result = 0;
		 try {
	            pstmt = conn.prepareStatement(
	            "update GUESTBOOK set NAME=?,EMAIL=?,PASSWORD=?,CONTENT=? "+
	            "where GUESTBOOK_ID = ?");
	            
	            pstmt.setString(1, bean.getName());
	            pstmt.setString(2, bean.getEmail());
	            pstmt.setString(3, bean.getPassword());
	            pstmt.setString(4, null);
	            pstmt.setString(5, String.valueOf(bean.getId()));
	            
	            pstmt.executeUpdate();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return result;
	}

	@Override
	public int delete(String id) {
		int result = 0;
		 try {
	            pstmt = conn.prepareStatement(
	            "delete from GUESTBOOK where GUESTBOOK_ID = ?");
	            pstmt.setString(1, String.valueOf(bean.getId()));
	            pstmt.executeUpdate();  
	        } catch(Exception ex) {
	        	ex.printStackTrace();
	        } finally {
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return result;
	}

	@Override
	public List<Object> getElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
