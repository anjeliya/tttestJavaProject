package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
//자바에서는 동적인 길이로 여러 데이터형을 저장하기 위해 'Vector 클래스'를 제공한다.
//'Vector 클래스'는 가변 길이의 배열이라고 할 수 있다.

public class GListDAO {
	//실행후 테이블에 전체 품목이 보이도록
	public Vector listGList() {
		//ArrayList는 JTable과 잘 호환이 안되므로 Vector 사용
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null; //지정된 sql문 실행
		ResultSet rs = null;
		try {
			conn = DB.oraConn();
			String sql = "select no,local,name,company,redate,price,quant,"
					+ "(price * quant) total from glist";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//executeQuery() : DB에 명령
			//ResultSet : 그 명령에 대한 반환값
			while (rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("no"));
				row.add(rs.getString("local"));
				row.add(rs.getString("name"));
				row.add(rs.getString("company"));
				row.add(rs.getDate("redate"));
				row.add(rs.getInt("price"));
				row.add(rs.getInt("quant"));
				row.add(rs.getInt("total"));
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return items;
	}//listEmp	

	
	//날짜로 품목 검색하기
	public Vector searchGList(String no) {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.oraConn();
			String sql = "select no,local,name,"
					+ " company,redate,price,quant,"
					+ " (price*quant) total "
					+ " from glist where no like ? ";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + no + "%");
			rs = pstmt.executeQuery();
			while (rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("no"));
				row.add(rs.getString("local"));
				row.add(rs.getString("name"));
				row.add(rs.getString("company"));
				row.add(rs.getDate("redate"));
				row.add(rs.getInt("price"));
				row.add(rs.getInt("quant"));
				row.add(rs.getInt("total"));
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return items;
	}
	
	//품목 추가하기
	public int insertGList(GListDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.oraConn();
			String sql = "insert into glist values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNo());
			pstmt.setString(2, dto.getLocal());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getCompany());
			pstmt.setDate(5, dto.getRedate());
			pstmt.setInt(6, dto.getPrice());
			pstmt.setInt(7, dto.getQuant());
			pstmt.setInt(8, dto.getTotal());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}
	
	//품목 삭제
	public int deleteGList(String no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.oraConn();
			String sql = "delete from glist where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
		
	}//deleteProduct()	
	
	//품목 수정하기
	public int updateGList(GListDTO dto){
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.oraConn();
			String sql = "update glist set "
					+ " local=?,name=?,company=?,redate=?,price=?,quant=?,total=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getLocal());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getCompany());
			pstmt.setDate(4, dto.getRedate());
			pstmt.setInt(5, dto.getPrice());
			pstmt.setInt(6, dto.getQuant());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}
	

	//품목번호만 받아서, 편집창에서 출력이되도록
		public GListDTO viewGList(String no) {
			GListDTO dto=null;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=DB.oraConn();
				
//				StringBuilder sb=new StringBuilder();
//				sb.append("select no,local,name,company,redate,price,quant");
//				sb.append(",(price+quant) tot");
//				sb.append(" from glist ");
//				sb.append(" where no=?");
				String sql = "select no,local,name,company,redate,price,quant,"
						+ "(price+quant) tot from glist where no=?";
				
				//pstmt=conn.prepareStatement(sb.toString());
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()) {//레코드가 한개이기 때문에 if문만 씀
					String local = rs.getString("local");
					String name = rs.getString("name");
					String company = rs.getString("company");
					Date redate = rs.getDate("redate");
					int price=rs.getInt("price");
					int quant=rs.getInt("quant");
					dto=new GListDTO(no,local,name,company,redate,price,quant);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					if(pstmt!=null) pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					if(conn!=null) conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}//finally
			return dto;
		}
	
	
	
}


