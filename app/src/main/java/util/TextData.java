package util;

import java.util.List;

import pojo.Task;
import pojo.Tasks;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/2/23.
 */

public interface TextData {
    @GET("tasks?from=0&max=20")
    Call<List<Task>> getData();
}
