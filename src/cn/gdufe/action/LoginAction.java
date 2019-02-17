package cn.gdufe.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.gdufe.dao.IUserDAO;

public class LoginAction extends ActionSupport{

	private static final long serialVersionUID = 8274180634947664192L;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		/*
		 * JDBC��ʽ�������ݿ�
		 * 
		String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
		boolean validated = false;
		DbBean dbBean = new DbBean();
		dbBean.openConnection();
		ResultSet rs = dbBean.executeQuery(sql);
		if(rs.next()){
			validated = true;
		}
		dbBean.closeConnection();
		if(validated){
			return SUCCESS;
		}else{
			return ERROR;
		}
		 *
		 *
		 */

		//2.Hibernate�־û���ʽ�������ݿ�
		
		boolean validated = false; //��֤��ʶ
		//2.1 ��ȡ�����ļ�applicationContext.xml--->�õ�DAO����
		//ApplicationContext ac = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserDAO iUserDAO= (IUserDAO) ac.getBean("userDAO");
		//2.2���ݲ�����UserDAO,�õ����ص�User����
		//2.3����User�����Ƿ�Ϊnull���ؽ����struts.xml
		if(iUserDAO.validateUser(username, password)!=null){
			validated = true;
		}
		if(validated){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
}
