package bean;
/*
@ Date : 2015.06
@ Author : ITB-1
@ Story : 회원관리 Bean
*/
public class MemberBean {
    private String name; // 이름
    private String addr; // 주소
    private String age; // 나이
    private String id; // ID
    private String password; // 비번
    private String email; // 이메일
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    

    
}