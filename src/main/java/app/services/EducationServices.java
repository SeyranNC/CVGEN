package app.services;

import app.Main;
import app.models.Education;
import app.models.Work_Exp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.services.CreaterService.getConnection;

public class EducationServices {




    public boolean save(int id, Education education) {

        try(Connection connection= getConnection()){

            PreparedStatement statement = connection.prepareStatement("INSERT INTO EDU (NAME ,DATEX ,ISCHECK, CERT,USERID ) Values (?, ?, ?, ? ,?)");

            statement.setString(1,education.getName());
            statement.setDate(2, Date.valueOf(education.getDate()==null? LocalDate.now():education.getDate()));
            statement.setBoolean(3,education.isCert_check());
            statement.setString(4, education.getCert_id());
            statement.setInt(5, id);


        int row =   statement.executeUpdate();

          return row>0;
        }catch (Exception e){
            ///LOG+++
            System.err.println("EDU SAVE ERROR  : "+e.getMessage());
            return false;

        }

    }


    public List<Education> getEduByUserID(int user_id) {

        List<Education>  list = new ArrayList<>();


        try (Connection  connection = getConnection()){

            Statement statement = connection.createStatement();
          /*NAME VARCHAR(64), DATEX DATE , ISCHECK BOOLEAN ,CERT VARCHAR(64) ,USERID INTEGER */
            ResultSet resultSet= statement.executeQuery("SELECT * From EDU WHERE USERID = "+user_id);

            while(resultSet.next()){
                Education education = new Education();
                education.setName(resultSet.getString("NAME"));
                education.setDate(resultSet.getDate("DATEX").toLocalDate());
                education.setCert_check(resultSet.getBoolean("ISCHECK"));
                education.setCert_id(resultSet.getString("CERT"));
                list.add(education);
            }

        }catch (Exception e){
            //LOG++
            System.err.println("  ERROR "+this.getClass().getName()+","+e.getMessage());
        }
        return list;
    }










}
