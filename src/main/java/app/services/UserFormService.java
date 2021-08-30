package app.services;

import app.models.User;
import app.view_models.UserForm;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class UserFormService {

    UserService userService = new UserService();

  public final   static ObservableList<UserForm>  list = FXCollections.observableArrayList();

    public UserFormService() {
        list.addListener((ListChangeListener<UserForm>) event -> {
               if( event.next() && event.wasRemoved() && (!event.wasReplaced()) )  {
                        event.getRemoved().stream().forEach( users -> userService.deleteUserByID(users.getId()));
                    }
               }
        );
    }

    public ObservableList<UserForm>  getUserList()  {

        List<User>    users = new ArrayList<>();
        try {
          users=  userService.getAllUsers();
        }catch (Exception e){
            //LOG++
            System.err.println("ERROR : "+ e.getMessage());;
        }
        for(User user : users ){

            UserForm userForm = new UserForm(user.getId(),user.getFirstName()+" "+user.getLastName(), user.getDepartment(), user.getPosition());

           list.add(userForm);


        }

        return list;

    }


}
