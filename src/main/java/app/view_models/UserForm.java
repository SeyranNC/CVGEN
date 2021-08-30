package app.view_models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserForm {

    private int id ;
    private SimpleStringProperty name = new SimpleStringProperty("");
    private  SimpleStringProperty department = new SimpleStringProperty("");
    private  SimpleStringProperty position = new SimpleStringProperty("");
    private SimpleObjectProperty<ImageView>   edit= new SimpleObjectProperty<>(new ImageView());
    private SimpleObjectProperty<ImageView>   delete = new SimpleObjectProperty<>(new ImageView());
    public UserForm() {
    }

    public UserForm(int id, String name, String department, String position) {
            this.id=id;
       this.name = new SimpleStringProperty(name);
       this.department=new SimpleStringProperty(department);
       this.position  =new SimpleStringProperty(position);
        ImageView editImage =new ImageView(new Image("imag/edit.png"));
        editImage.setFitHeight(24);
        editImage.setFitWidth(24);
        this.edit= new SimpleObjectProperty<>(editImage);
        ImageView deleteImage =new ImageView(new Image("imag/delete.png"));
        deleteImage.setFitHeight(24);
        deleteImage.setFitWidth(24);
       this.delete= new SimpleObjectProperty<>(deleteImage);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageView getDelete() {
        return delete.get();
    }

    public SimpleObjectProperty<ImageView> deleteProperty() {
        return delete;
    }

    public void setDelete(ImageView delete) {
        this.delete.set(delete);
    }

    public ImageView getEdit() {
        return edit.get();
    }

    public SimpleObjectProperty<ImageView> editProperty() {
        return edit;
    }

    public void setEdit(ImageView edit) {
        this.edit.set(edit);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

}
