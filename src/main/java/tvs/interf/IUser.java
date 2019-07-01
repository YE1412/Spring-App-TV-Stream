package tvs.interf;

import org.springframework.stereotype.Service;

import tvs.daohibernate.model.User;
@Service
public interface IUser {
	public void addUser(User user);
	public User getUser(String name, String pass);
	public User findUser(String name);
	public User newUser();
}
