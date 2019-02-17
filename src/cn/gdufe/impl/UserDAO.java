package cn.gdufe.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.gdufe.dao.IUserDAO;
import cn.gdufe.vo.User;

public class UserDAO extends BaseDAO implements IUserDAO{

	@SuppressWarnings("unchecked")
	@Override
	public User validateUser(String username, String password) {
		// 1.��ȡsession
		Session session = getSession();
		// 2.�������ݿ�Ĳ�ѯ[username+password]-->��ѯ����User,���������ݿ��еı�
		Query query = session.createQuery("from User where username = ? and password = ?");
		query.setParameter(0, username);
		query.setParameter(1, password);
		List<User> list = query.list();
		if(list.size()!=0){
			return list.get(0);
		}
		// 3.���ز�ѯ������ر�session
		session.close();
		return null;
	}

}
