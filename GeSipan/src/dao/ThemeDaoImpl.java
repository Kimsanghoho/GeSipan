package dao;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bean.BangBean;
import bean.ThemeBean;

public class ThemeDaoImpl implements CommonDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	ThemeBean bean = new ThemeBean();
	@Override
	public int insert(Object obj) {
		int result = 0;
		
		
		 try {
	            conn.setAutoCommit(false);
	            
	            if (bean.getParentId() == 0) { 
	                // 답글이 아닌 경우 그룹번호를 새롭게 구한다.
	                stmt = conn.createStatement(); 
	                rs = stmt.executeQuery(
	                    "select max(GROUP_ID) from THEME_MESSAGE"); 
	                int maxGroupId = 0; 
	                if (rs.next()) {
	                    maxGroupId = rs.getInt(1); 
	                }
	                maxGroupId++;
	                
	                bean.setGroupId(maxGroupId);
	                bean.setOrderNo(0);
	            } else {
	                // 특정 글의 답글인 경우,
	                // 같은 그룹 번호 내에서의 출력 순서를 구한다.  
	                pstmt= conn.prepareStatement( 
	                "select max(ORDER_NO) from THEME_MESSAGE "+ 
	                "where PARENT_ID = ? or THEME_MESSAGE_ID = ?"); 
	                pstmt.setInt(1, bean.getParentId());
	                pstmt.setInt(2, bean.getParentId());
	                rs = pstmt.executeQuery();
	                int maxOrder = 0;
	                if (rs.next()) {
	                    maxOrder = rs.getInt(1);
	                }
	                maxOrder ++;
	                bean.setOrderNo(maxOrder); 
	            }
	            
	            // 특정 글의 답변 글인 경우 같은 그룹 내에서
	            // 순서 번호를 변경한다.
	            if (bean.getOrderNo() > 0) {
	                pstmt= conn.prepareStatement(
	                "update THEME_MESSAGE set ORDER_NO = ORDER_NO + 1 "+
	                "where GROUP_ID = ? and ORDER_NO >= ?");
	                pstmt.setInt(1, bean.getGroupId()); 
	                pstmt.setInt(2, bean.getOrderNo()); 
	                pstmt.executeUpdate();
	            }
	            // 새로운 글의 번호를 구한다.
	            bean.setId(0);
	            // 글을 삽입한다.
	            pstmt= conn.prepareStatement( 
	            "insert into bean_MESSAGE values (?,?,?,?,?,?,?,?,?,?,?)");
	            pstmt.setInt(1, bean.getId());
	            pstmt.setInt(2, bean.getGroupId());
	            pstmt.setInt(3, bean.getOrderNo());
	            pstmt.setInt(4, bean.getLevel()); 
	           pstmt.setInt(5, bean.getParentId());
	         //  pstmt.setTimestamp(6, bean.getRegister());
	           pstmt.setString(7, bean.getName());
	           pstmt.setString(8, bean.getEmail());
	           pstmt.setString(9, bean.getImage());
	           pstmt.setString(10, bean.getPassword());
	           pstmt.setString(11, bean.getTitle()); 
	           pstmt.executeUpdate(); 
	            
	            pstmt = conn.prepareStatement( 
	            "insert into THEME_CONTENT values (?,?)");
	            pstmt.setInt(1, bean.getId());
	            pstmt.setCharacterStream(2,null); 
	            
	            conn.commit();
	        } catch(SQLException ex) {
	        	ex.printStackTrace();
	        } finally { 
	        	
	        }
		return result;
	}

	@Override
	public int count() {
		int result = 0;
		try {
            String sql = "select count(*) from THEME_MESSAGE";
            pstmt = conn.prepareStatement(sql);
            
           /* while(keyIter.hasNext()) {
                Integer key = (Integer)keyIter.next();
                Object obj = valueMap.get(key); 
                if (obj instanceof String) {
                    pstmt.setString(key.intValue(), (String)obj);
                } else if (obj instanceof Integer) {
                    pstmt.setInt(key.intValue(), ((Integer)obj).intValue());
                } else if (obj instanceof Timestamp) {
                    pstmt.setTimestamp(key.intValue(), (Timestamp)obj); 
                }            Iterator keyIter =  new hashMap.keySet().iterator();

            }*/
            
            rs = pstmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        } finally { 
            if (rs != null) try { rs.close(); } catch(SQLException ex) {} 
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {} 
            if (conn != null) try { conn.close(); } catch(SQLException ex) {} 
        }
		
		return result;
	}

	@Override
	public Object getElementById(String id) {
		Object obj = null;
		 try {
	            
	            pstmt = conn.prepareStatement(
	                "select * from THEME_MESSAGE "+
	                "where THEME_MESSAGE_ID = ?");
	            pstmt.setString(1, id); 
	            rs = pstmt.executeQuery();
	            if (rs.next()) { 
	                bean = new ThemeBean();
	                bean.setId(rs.getInt("bean_MESSAGE_ID"));
	                bean.setGroupId(rs.getInt("GROUP_ID")); 
	                bean.setOrderNo(rs.getInt("ORDER_NO")); 
	                bean.setLevel(rs.getInt("LEVEL"));
	                bean.setParentId(rs.getInt("PARENT_ID"));
	                bean.setRegister(rs.getTimestamp("REGISTER"));
	                bean.setName(rs.getString("NAME")); 
	                bean.setEmail(rs.getString("EMAIL"));
	                bean.setImage(rs.getString("IMAGE"));
	                bean.setPassword(rs.getString("PASSWORD")); 
	                bean.setTitle(rs.getString("TITLE"));

	                pstmt = conn.prepareStatement(
	                    "select CONTENT from THEME_CONTENT "+
	                    "where THEME_MESSAGE_ID = ?");
	                pstmt.setString(1, id); 
	                rs = pstmt.executeQuery();
	             /*   if (rs.next()) { 
	                    Reader reader = null;
	                    try {
	                        reader = rs.getCharacterStream("CONTENT");
	                        char[] buff = new char[512];
	                        int len = -1;
	                        StringBuffer buffer = new StringBuffer(512);
	                        while( (len = reader.read(buff)) != -1) {
	                            buffer.append(buff, 0, len);
	                        }
	                        bean.setContent(buffer.toString());
	                    */
	            }
	        } catch(SQLException ex) {
	        	ex.printStackTrace();
	        
	        } finally { 
	            if (rs != null)  
	                try { rs.close(); } catch(SQLException ex) {} 
	            if (pstmt != null) 
	                try { pstmt.close(); } catch(SQLException ex) {} 
	             
	        }
		return obj;
	}

	@Override
	public List<Object> getElementByName(String name) {
		List<Object> list = new ArrayList<Object>();
		return list;
	}

	@Override
	public List<Object> list() {
		List<Object> list = new ArrayList<Object>();
		int  endRow=0, startRow=0;
		try {
           
            String sql ="select * from THEME_MESSAGE order by GROUP_ID desc, ORDER_NO asc limit ?, ?";
            
            
            pstmt = conn.prepareStatement(sql); 
         /*   Iterator keyIter = valueMap.keySet().iterator();
            while(keyIter.hasNext()) {
                Integer key = (Integer)keyIter.next();
                Object obj = valueMap.get(key); 
                if (obj instanceof String) {
                    pstmtMessage.setString(key.intValue(), (String)obj);
                } else if (obj instanceof Integer) {
                    pstmtMessage.setInt(key.intValue(), 
                                        ((Integer)obj).intValue()); 
                } else if (obj instanceof Timestamp) {
                    pstmtMessage.setTimestamp(key.intValue(),
                                             (Timestamp)obj);
                }
            }
            */
          /*  pstmt.setInt(valueMap.size()+1, startRow);
            pstmt.setInt(valueMap.size()+2, endRow-startRow+1);
            */
            rs = pstmt.executeQuery();
            if (rs.next()) { 
                 list = new java.util.ArrayList(endRow-startRow+1); 
                
                do {
                    ThemeBean bean = new ThemeBean();
                    bean.setId(rs.getInt("bean_MESSAGE_ID"));
                    bean.setGroupId(rs.getInt("GROUP_ID")); 
                    bean.setOrderNo(rs.getInt("ORDER_NO")); 
                    bean.setLevel(rs.getInt("LEVEL"));
                    bean.setParentId(rs.getInt("PARENT_ID"));
                    bean.setRegister(rs.getTimestamp("REGISTER"));
                    bean.setName(rs.getString("NAME")); 
                    bean.setEmail(rs.getString("EMAIL"));
                    bean.setImage(rs.getString("IMAGE"));
                    bean.setPassword(rs.getString("PASSWORD")); 
                    bean.setTitle(rs.getString("TITLE"));
                    list.add(bean);
                } while(rs.next());
                
                
            }
            
        } catch(SQLException ex) {
        	ex.printStackTrace();
        } finally { 
          
        }
		
		return list;
	}

	@Override
	public int update(Object obj) {
		int result = 0;
		
		  try {
	            conn.setAutoCommit(false);
	            
	            pstmt = conn.prepareStatement( 
	                "update THEME_MESSAGE set NAME=?,EMAIL=?,IMAGE=?,TITLE=? "+ 
	                "where THEME_MESSAGE_ID=?");
	            pstmt= conn.prepareStatement( 
	                "update THEME_CONTENT set CONTENT=? "+
	                "where THEME_MESSAGE_ID=?"); 
	            
	            pstmt.setString(1, bean.getName());
	            pstmt.setString(2, bean.getEmail());
	            pstmt.setString(3, bean.getImage());
	            pstmt.setString(4, bean.getTitle());
	            pstmt.setInt(5, bean.getId());
	            pstmt.executeUpdate(); 
	            
	            pstmt.setCharacterStream(1,null);
	            pstmt.setInt(2, bean.getId());
	            pstmt.executeUpdate();
	            
	            conn.commit();
	        } catch(SQLException ex) {
	          ex.printStackTrace();
	          System.out.println("업데이트 에러");
	        } finally { 
	          
	        }
		
		return result;
	}

	@Override
	public int delete(String id) {
		int result = 0;
		
		 try {
	            conn.setAutoCommit(false);
	            
	            pstmt = conn.prepareStatement(
	                "delete from THEME_MESSAGE where THEME_MESSAGE_ID = ?");
	            pstmt= conn.prepareStatement(
	                "delete from THEME_CONTENT where THEME_MESSAGE_ID = ?");
	            
	            pstmt.setString(1, id); 
	            pstmt.setString(1, id); 
	            
	            int updatedCount1 = pstmt.executeUpdate();
	            int updatedCount2 = pstmt.executeUpdate();
	            
	            if (updatedCount1 + updatedCount2 == 2) {
	                conn.commit();
	            } else {
	                conn.rollback();
	            }
	        } catch(SQLException ex) {
	            try {
	                conn.rollback();
	            } catch(SQLException ex1) {}
	         finally { 
	        	 
	         }
	         
	    } 
		
		return result;
	}

}
