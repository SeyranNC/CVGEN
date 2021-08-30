package app.services;

import app.Main;
import app.models.User;
import app.view_models.UserForm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.services.CreaterService.getConnection;


public class UserService{

    public boolean save(User user){
            if(user.getId()>0)  return update(user);

        try(Connection  connection = getConnection()) {
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (FIRST_NAME ,LAST_NAME ,DEPARTMENT, POSIT ) Values (?, ?, ?, ? )");

            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3, user.getDepartment());
            statement.setString(4, user.getPosition());

            statement.executeUpdate();

        } catch (Exception e) {
            //LOG++
            System.err.println("ERROR save : "+ e.getMessage());;
           return false;
        }
        System.out.println("ADDD"+ user);
           return true;
    }

    private boolean update(User user) {
        try(Connection  connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE   Users SET FIRST_NAME = ? ,LAST_NAME=? ,DEPARTMENT =? , POSIT =?" +
                                                                             " WHERE ID= ?");

            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3, user.getDepartment());
            statement.setString(4, user.getPosition());
            statement.setInt(5, user.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            //LOG++
            System.err.println("ERROR save : "+ e.getMessage());;
            return false;
        }
        System.out.println("ADDD"+ user);
        return true;
    }

    public User getUserById(int id) throws Exception {

         User user=new User();

        Connection  connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet= statement.executeQuery("SELECT * From Users where ID= "+id);

        if(resultSet.next()){
            user= new User(id, resultSet.getString("FIRST_NAME") ,resultSet.getString("LAST_NAME"),
                    resultSet.getString("DEPARTMENT"),resultSet.getString("POSIT"));
        }

        connection.close();
        statement.close();
        return user;

    }


    public List<User> getAllUsers() throws Exception {

        List<User> users= new ArrayList<>();

        Connection  connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet= statement.executeQuery("SELECT * From Users");

        while(resultSet.next()){
          User  user= new User(resultSet.getInt("ID"), resultSet.getString("FIRST_NAME") ,resultSet.getString("LAST_NAME"),
                    resultSet.getString("DEPARTMENT"),resultSet.getString("POSIT"));
          users.add(user);

        }
         connection.close();
         statement.close();
        return users;

    }

     public boolean deleteUserByID(int  id) {


        Connection  connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            int  row = statement.executeUpdate("DELETE FROM Users WHERE id="+id);


            return row>0;
        } catch (Exception e) {
            //LOG++
            System.err.println("  ERROR "+this.getClass().getName()+","+e.getMessage());
            return  false;

        }
    }


    public List<String> getAllDepartment(){

        List<String>  list = new ArrayList<>();


        try (Connection  connection = getConnection()){

        Statement statement = connection.createStatement();

        ResultSet resultSet= statement.executeQuery("SELECT * From DEPARTMENTS");

        while(resultSet.next()){

            list.add(resultSet.getString("NAME"));

        }


    }catch (Exception e){
            //LOG++
            System.err.println("  ERROR "+this.getClass().getName()+","+e.getMessage());
        }
        return list;
}



    public List<String> getAllPosition(){

        List<String>  list = new ArrayList<>();


        try (Connection  connection = getConnection()){

            Statement statement = connection.createStatement();

            ResultSet resultSet= statement.executeQuery("SELECT * From POSITIONS");

            while(resultSet.next()){

                list.add(resultSet.getString("NAME"));

            }

        }catch (Exception e){
            //LOG++
            System.err.println("  ERROR "+this.getClass().getName()+","+e.getMessage());
        }
        return list;
    }


    public boolean clean_old(int id) {

        try (Connection  connection = getConnection()) {

            Statement statement = connection.createStatement();

             statement.executeUpdate("DELETE FROM EDU WHERE USERID = "+id);

            statement.executeUpdate("DELETE FROM WXP WHERE USERID = "+id);

        }catch (Exception e){
            //LOG++
            System.err.println("CLEAN ERROR "+this.getClass().getName()+","+e.getMessage());
            return false;
        }
        return true;

    }

    public boolean save(UserForm userForm) {

        User user = new User(userForm.getId(), userForm.getName().split(" ")[0],userForm.getName().split(" ")[1] ,
                userForm.getDepartment(), userForm.getPosition());
        return this.save(user);
    }
}