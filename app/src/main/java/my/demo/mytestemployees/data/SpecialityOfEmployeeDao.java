package my.demo.mytestemployees.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import my.demo.mytestemployees.pojo.Speciality;

@Dao
public interface SpecialityOfEmployeeDao {

    @Query("SELECT * FROM speciality")
    List<Speciality> getAllSpecialty();

    @Transaction
    @Query("SELECT * FROM speciality WHERE specialityId = :employeeId")
    List<Speciality> findSpecialtiesForEmployee(long employeeId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Speciality specialty);

    @Delete
    void delete(Speciality specialty);

}
