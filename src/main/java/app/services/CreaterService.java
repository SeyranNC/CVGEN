package app.services;

import app.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreaterService {



    public  boolean createEDU(){


        try(Connection connection= getConnection()){
            Statement statement = connection.createStatement();

            String  creatDb="create table EDU  (  ID INTEGER generated always as identity constraint EDU_PK primary key," +
                    "  NAME VARCHAR(64), DATEX DATE , ISCHECK BOOLEAN ,CERT VARCHAR(64) ,USERID INTEGER )";
            statement.execute(creatDb);
        }catch (Exception e){
            ///LOG+++
            System.err.println("ERROR : "+e.getMessage());
            return false;
        }
        return true;
    }
    public  boolean createWXP(){


        try(Connection connection= getConnection()){
            Statement statement = connection.createStatement();

            String  creatDb="create table WXP  (  ID INTEGER generated always as identity constraint WXP_PK primary key," +
                    "  NAME VARCHAR(64), DATEFROM DATE , DATETO DATE,USERID INTEGER )";
            statement.execute(creatDb);
        }catch (Exception e){
            ///LOG+++
            System.err.println("ERROR : "+e.getMessage());
            return false;
        }
        return true;
    }


    public  boolean createUserDATA(){


        try(Connection connection= getConnection()){
            Statement statement = connection.createStatement();

            String  creatDb= "create table Users(  ID INTEGER generated always as identity constraint USERS_PK primary key, " +
                    " LAST_NAME VARCHAR(64),  FIRST_NAME VARCHAR(64), DEPARTMENT  VARCHAR(64), POSIT  VARCHAR(64))";
            statement.execute(creatDb);
        }catch (Exception e){
            ///LOG+++
            System.err.println("ERROR : "+e.getMessage());
            return false;
        }
        return true;
    }

    public  boolean createAndinitDepapartment(){


        try(Connection connection= getConnection()){
           

            Statement statement = connection.createStatement();

            String  creatDb="create table DEPARTMENTS  (  ID INTEGER generated always as identity constraint DEPARTMENTS_PK primary key,  NAME VARCHAR(64) )";
            statement.execute(creatDb);
            creatDb ="insert into DEPARTMENTS(NAME) values('dep1'), ('dep2'), ('dep3')";

            statement.execute(creatDb);
        }catch (Exception e){
            ///LOG+++
            System.err.println("ERROR : "+e.getMessage());
            return false;
        }
        return true;
    }
    public  boolean createAndinitPostions(){


        try(Connection connection= getConnection()){


            Statement statement = connection.createStatement();


             String creatDb="create table POSITIONS (ID INTEGER generated always as identity constraint POSITION_PK primary key,  NAME VARCHAR(64))";
            statement.execute(creatDb);
            creatDb ="insert into POSITIONS(NAME) values('opt1'), ('opt2'), ('opt3')";
            statement.execute(creatDb);
        }catch (Exception e){
            ///LOG+++
            System.err.println("ERROR : "+e.getMessage());
            return false;
        }
        return true;
    }
    
    
    
    

    
    
    

    static   public Connection getConnection() throws Exception {


        final String dbURL = Main.context.getProperty("dbUrl");

        Class.forName(Main.context.getProperty("driver")).newInstance();

        return DriverManager.getConnection(dbURL);


    }



}
