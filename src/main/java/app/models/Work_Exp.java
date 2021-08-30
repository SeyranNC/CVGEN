package app.models;

import java.time.LocalDate;
import java.util.Date;

public class Work_Exp {


    private String name;
    private LocalDate data_from;
    private LocalDate data_to;

    public Work_Exp() {
    }


    public Work_Exp(String name, LocalDate data_from, LocalDate data_to) {

        this.name = name;
        this.data_from = data_from;
        this.data_to = data_to;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   /* convertor  LocalDate to DATE */
    public Date getDatafrom() {
        return java.sql.Date.valueOf(data_from);
    }

    public void setData_from(LocalDate data_from) {
        this.data_from = data_from;
    }
    /* convertor  LocalDate to DATE */
    public Date getDatato() {
        return java.sql.Date.valueOf(data_to);
    }

    public void setData_to(LocalDate data_to) {
        this.data_to = data_to;
    }

    public LocalDate getData_from() {
        return data_from;
    }

    public LocalDate getData_to() {
        return data_to;
    }

    @Override
    public String toString() {
        return "Work_Exp{" +
                "name='" + name + '\'' +
                ", data_from=" + data_from +
                ", data_to=" + data_to +
                '}';
    }
}
