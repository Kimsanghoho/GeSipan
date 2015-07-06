package dao;

import java.util.List;

public interface CommonDAO {

	// 추가 create
	public int insert(Object obj);
	// 전체 요소의 갯수
	public int count();
	// ID로 중복값 없이 출ㄹ력
	public Object getElementById(String id);
	// Name로 중복값 허용하며 출력
	public List<Object> getElementByName(String name);
	// 전체 목록
	public List<Object> list();
	//수정
	public int update(Object obj);
	//삭제
	public int delete(String id);
	
}
