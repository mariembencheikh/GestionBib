package dao;

import dao.entities.User;

public interface IgestionUser {
	public void saveUser(User u);
	public User getUser(int id);
	public User  verifier(String email,String password);
}
