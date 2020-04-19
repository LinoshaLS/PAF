package DTO;


public class Department {
    protected String id;
    protected String department_Name;


    public Department() {
    }

    public Department(String id, String department_Name) {
        this.id = id;
        this.department_Name = department_Name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment_Name() {
        return department_Name;
    }

    public void setDepartment_Name(String Department_Name) {
        this.department_Name = Department_Name;
    }


}