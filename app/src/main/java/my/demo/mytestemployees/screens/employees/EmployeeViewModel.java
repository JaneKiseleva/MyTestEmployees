package my.demo.mytestemployees.screens.employees;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import my.demo.mytestemployees.api.ApiFactory;
import my.demo.mytestemployees.api.ApiService;
import my.demo.mytestemployees.data.AppDatabase;
import my.demo.mytestemployees.pojo.Employee;
import my.demo.mytestemployees.pojo.EmployeeResponse;
import my.demo.mytestemployees.pojo.Speciality;

public class EmployeeViewModel extends AndroidViewModel {

    private static AppDatabase db;
    private LiveData<List<Employee>> employees;
    private MutableLiveData<Throwable> errors;
    private CompositeDisposable compositeDisposable;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        employees = db.employeeDao().getAllEmployees();
        errors = new MutableLiveData<>();
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void clearErrors () {
        errors.setValue(null);
    }

    @SuppressWarnings("unchecked")
    private void insertEmployees(List<Employee> employees) {
        new InsertEmployeesTask().execute(employees);
    }

    private static class InsertEmployeesTask extends AsyncTask<List<Employee>, Void, Void> {
        @SuppressWarnings("unchecked")
        @Override
        protected Void doInBackground(List<Employee>... lists) {
            if (lists != null && lists.length > 0) {
                db.employeeDao().insertEmployees(lists[0]);

                lists[0].forEach(c -> {
                    c.getSpecialities().forEach(s -> {
                        db.specialityOfEmployeeDao().insert(s);
                    });
                });

            }
            return null;
        }
    }

    private void deleteAllEmployees() {
        new DeleteAllEmployeesTask().execute();
    }

    private void deleteAllSpecialities() {
        new DeleteAllSpecialitiesTask().execute();
    }

    private static class DeleteAllEmployeesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            db.employeeDao().deleteAllEmployees();
            return null;
        }
    }

    private static class DeleteAllSpecialitiesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            db.specialityOfEmployeeDao().deleteAll();
            return null;
        }
    }

    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        deleteAllEmployees();
                        deleteAllSpecialities();
                        insertEmployees(employeeResponse.getEmployees());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.setValue(throwable);
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}

