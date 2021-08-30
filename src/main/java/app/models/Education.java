package app.models;

import java.time.LocalDate;
import java.util.Date;

public class Education {


    private String name;
    private LocalDate date;
    private boolean cert_check;
    private String cert_id;

    public Education() {
    }


    public Education( String name, LocalDate date, boolean cert_check, String cert_id) {

        this.name = name;
        this.date = date;
        this.cert_check = cert_check;
        this.cert_id = cert_id;
    }

    /* convertor  LocalDate to DATE */
    public Date getDatex() {
        return  java.sql.Date.valueOf(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isCert_check() {
        return cert_check;
    }

    public void setCert_check(boolean cert_check) {
        this.cert_check = cert_check;
    }

    public String getCert_id() {
        return cert_id;
    }

    public void setCert_id(String cert_id) {
        this.cert_id = cert_id;
    }

    @Override
    public String toString() {
        return "Education{" +
                        ", name='" + name + '\'' +
                ", date=" + date +
                ", cert_check=" + cert_check +
                ", cert_id='" + cert_id + '\'' +
                '}';
    }
}