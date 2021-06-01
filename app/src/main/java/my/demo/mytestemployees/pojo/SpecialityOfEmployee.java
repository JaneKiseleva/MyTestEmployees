package my.demo.mytestemployees.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SpecialityOfEmployee {

    @Embedded
    public Employee employee;

    @Relation(parentColumn = "employee_id", entityColumn = "specialty_id", entity = Speciality.class)
    protected List<Speciality> specialities;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialties) {
        this.specialities = specialties;
    }
}
