package my.demo.mytestemployees.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import my.demo.mytestemployees.R;
import my.demo.mytestemployees.adapters.EmployeeAdapter;
import my.demo.mytestemployees.pojo.Employee;
import my.demo.mytestemployees.pojo.Speciality;

public class MVVMListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeViewModel viewModel;
    private Spinner spinnerSpeciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployees = findViewById(R.id.RecyclerViewEmployees);
        spinnerSpeciality = findViewById(R.id.spinnerSpeciality);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(EmployeeViewModel.class);
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
                if (employees != null) {
                    for (Employee employee : employees) {
                        List<Speciality> specialties = employee.getSpecialities();
                        for (Speciality speciality : specialties) {
                            Log.i ("Name",speciality.getName());
                            //specialtyDao.findSpecialtiesForEmployee

                        }
                    }
                }
            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(MVVMListActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });
        viewModel.loadData();
    }

    public String showDescription(View view) {
        int position = spinnerSpeciality.getSelectedItemPosition(); //Нашли индекс выбранного цвета
        return getDescriptionByPosition(position);
        //view.(description);
    }

    private String getDescriptionByPosition (int position) {
        String [] descriptions = getResources().getStringArray(R.array.speciality);
        return descriptions[position];

    }
}