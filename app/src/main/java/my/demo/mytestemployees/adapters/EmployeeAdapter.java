package my.demo.mytestemployees.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.demo.mytestemployees.R;
import my.demo.mytestemployees.pojo.Employee;
import my.demo.mytestemployees.pojo.Speciality;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;
    private List<Speciality> specialties;
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    public void setSpecialtyList (List<Speciality> specialties) {
        this.specialties= specialties;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_item, viewGroup, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int position) {
        Employee employee = employees.get(position);
        Speciality specialtyId = specialties.get(position);
        employeeViewHolder.textViewName.setText(employee.getName().substring(0, 1).toUpperCase() + employee.getName().substring(1));
        employeeViewHolder.textViewLastName.setText(employee.getLName().substring(0, 1).toUpperCase() + employee.getLName().substring(1));
        employeeViewHolder.textViewBirthday.setText(employee.getFormattedDate());


//        if (employeeViewHolder.textViewSpeciality != null) {
//            employeeViewHolder.textViewSpeciality.setText(specialtyId.getName());
//        } else {
//            employeeViewHolder.textViewSpeciality.setText("");
//        }


//        for (Employee employeeAll : employees) {
//            List<Specialty> specialties = employee.getSpecialty();
//            employeeViewHolder.textViewSpeciality.setText(specialties(getItemId(0)));
//
//employeeViewHolder.textViewSpeciality.setText(employee.setSpecialty(List<SpecialityOfEmployee>);

        //        if (employee.getAge() == 0) {
//            employeeViewHolder.textViewOld.setText("-");
//        } else {
//            employeeViewHolder.textViewOld.setText(employee.getAge());
//        }
//        employeeViewHolder.textViewOld.setText(String.valueOf(employee.getAge()));


    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewLastName;
        private TextView textViewBirthday;
        private TextView textViewOld;
        private TextView textViewSpeciality;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewBirthday = itemView.findViewById(R.id.textViewBirthday);
            textViewOld = itemView.findViewById(R.id.textViewOld);
            textViewSpeciality = itemView.findViewById(R.id.textViewSpeciality);
        }
    }
}


