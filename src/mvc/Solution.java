package mvc;

import mvc.controller.Controller;
import mvc.model.MainModel;
import mvc.model.Model;
import mvc.view.EditUserView;
import mvc.view.UsersView;

public class Solution {
	public static void main(String[] args) {
		Model model = new MainModel();
		UsersView usersView = new UsersView();
		Controller controller = new Controller();
		EditUserView editUserView = new EditUserView();

		usersView.setController(controller);
		controller.setModel(model);
		controller.setUsersView(usersView);
		controller.setEditUserView(editUserView);
		editUserView.setController(controller);

		usersView.fireEventShowAllUsers();
		usersView.fireEventOpenUserEditForm(126L);
		editUserView.fireEventUserDeleted(124L);
		editUserView.fireEventUserChanged("Petrov", 125, 77);
		usersView.fireEventShowDeletedUsers();
	}
}