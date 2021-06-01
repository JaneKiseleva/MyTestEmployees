package my.demo.mytestemployees.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import my.demo.mytestemployees.pojo.Employee;
import my.demo.mytestemployees.pojo.Speciality;

@Database(entities = {Employee.class, Speciality.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "employees.db";
    private static AppDatabase database;
    private static Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
            return database;
        }
    }

    public abstract EmployeeDao employeeDao();

    public abstract SpecialityOfEmployeeDao specialityOfEmployeeDao();
}