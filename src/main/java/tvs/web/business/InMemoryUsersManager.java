package tvs.web.business;

import org.springframework.stereotype.Service;

import tvs.daohibernate.Dao;
import tvs.daohibernate.model.User;
import tvs.interf.IUser;
@Service("userManager")
public class InMemoryUsersManager implements IUser {
	static Dao dao;
	
	public InMemoryUsersManager() {
		super();
		dao = new Dao();
        dao.init();
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.addUser(user);
	}
	@Override
	public User findUser(String name) {
		// TODO Auto-generated method stub
		return dao.findUser(name);
	}
	@Override
	public User getUser(String name, String pass) {
		// TODO Auto-generated method stub
		return dao.getUser(name, pass);
	}
	@Override
	public User newUser() {
		// TODO Auto-generated method stub
		return dao.createUser();
	}

}
