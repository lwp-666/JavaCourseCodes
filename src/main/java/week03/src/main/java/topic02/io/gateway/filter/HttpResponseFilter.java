package topic02.io.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpResponseFilter {

    boolean filter(FullHttpResponse response);

}
