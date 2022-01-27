package topic02.io.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public boolean filter(FullHttpResponse response) {
       // response.headers().set("kk", "java-1-nio");
        return response.headers().contains("kk");
    }
}
