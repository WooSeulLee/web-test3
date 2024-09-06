package org.web.test.vo;

import java.util.List;

public class UserService2 {
	private UserRepository ur = new UserRepository();
	
	public List<UserVO> getUsers(UserVO user){
		return ur.selectUsers(user);
	}
}
