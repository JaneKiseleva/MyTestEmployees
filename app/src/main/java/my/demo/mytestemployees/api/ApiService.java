package my.demo.mytestemployees.api;

import io.reactivex.Observable;
import my.demo.mytestemployees.pojo.EmployeeResponse;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}

/*public interface ApiService {
    @GET("testTask.json/{id}/video")
    Observable<EmployeeResponse> getEmployees(@Part ("id") int id, @Query("api_key") int apiKey);
}*/