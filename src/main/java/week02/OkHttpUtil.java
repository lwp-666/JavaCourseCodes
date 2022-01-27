package week02;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author: luwei
 * @Date: 2022/1/22
 * @description:  OkHttp 访问  http://localhost:8801
 */
public class OkHttpUtil {

    public static void main(String[] args) {

        String url = "http://localhost:8808";

        String msg = okHttpTest(url);
        System.out.println("msg = " + msg);

    }

    private static String okHttpTest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get().build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
