package topic01;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author: luwei
 * @Date: 2022/1/22
 * @description:  整合上次作业的 httpclient/okhttp， 访问  http://localhost:8801
 */
public class OkHttpUtil {

    public static void main(String[] args) {

        String url1 = "http://localhost:8808";
        String url2 = "http://localhost:8808/test";

        String msg1 = okHttpTest(url1);
        System.out.println("msg = " + msg1);

        String msg2 = okHttpTest(url2);
        System.out.println("msg = " + msg2);

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
