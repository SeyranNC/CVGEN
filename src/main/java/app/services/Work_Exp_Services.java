package app.services;


import app.models.Work_Exp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.services.CreaterService.getConnection;

public class Work_Exp_Services {

    public boolean save(int id, Work_Exp work_exp) {

        try(Connection connection= getConnection()){

            PreparedStatement statement = connection.prepareStatement("INSERT INTO WXP (NAME , DATEFROM ,DATETO, USERID ) Values (?, ?, ?, ? )");

            statement.setString(1,work_exp.getName());
            statement.setDate(2, Date.valueOf(work_exp.getData_from()==null? LocalDate.now():work_exp.getData_from()));
            statement.setDate(3, Date.valueOf(work_exp.getData_to()==null? LocalDate.now():work_exp.getData_to()));
            statement.setInt(4, id);


            int row =   statement.executeUpdate();

            return row>0;
        }catch (Exception e){
            ///LOG+++
            System.err.println("XP SAVE ERROR  : "+e.getMessage());
            return false;

        }





    }

    public List<Work_Exp> getWorK_ExpByUserID(int user_id) {

        List<Work_Exp>  list = new ArrayList<>();


        try (Connection connection = getConnection()){

            Statement statement = connection.createStatement();
            /*NAME VARCHAR(64), DATEFROM DATE , DATETO DATE,USERID INTEGER */
            ResultSet resultSet= statement.executeQuery("SELECT * From WXP WHERE USERID = "+user_id);

            while(resultSet.next()){
                Work_Exp xp= new Work_Exp();
                xp.setName(resultSet.getString("NAME"));
                xp.setData_from(resultSet.getDate("DATEFROM").toLocalDate());
                xp.setData_to(resultSet.getDate("DATETO").toLocalDate());

                list.add(xp);
            }

        }catch (Exception e){
            //LOG++
            System.err.println("  ERROR "+this.getClass().getName()+","+e.getMessage());
        }
        return list;
    }









}
