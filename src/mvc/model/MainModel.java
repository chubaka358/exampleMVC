package mvc.model;

import mvc.bean.User;
import mvc.model.service.UserService;
import mvc.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model{
	private UserService userService = new UserServiceImpl();
	private ModelData modelData = new ModelData();

	@Override
	public ModelData getModelData() {
		return modelData;
	}

	public void loadUsers(){
		List<User> users = userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));
		modelData.setDisplayDeletedUserList(false);
		modelData.setUsers(users);
	}

	public void loadDeletedUsers() {
		List<User> users = userService.getAllDeletedUsers();
		modelData.setDisplayDeletedUserList(true);
		modelData.setUsers(users);
	}

	public void loadUserById(long userId) {
		User user = userService.getUsersById(userId);
		List<User> a = new ArrayList<User>();
		a.add(user);
		List<User> userList = userService.filterOnlyActiveUsers(a);
		if (userList.size() != 0)
			modelData.setActiveUser(user);
	}

	public void deleteUserById(long id){
		userService.deleteUser(id);
		modelData.setUsers(getAllUsers());
	}

	private List<User> getAllUsers(){
		return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));
	}

	public void changeUserData(String name, long id, int level){
		userService.createOrUpdateUser(name, id, level);
		modelData.setUsers(getAllUsers());
	}
}
