package httpserver;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class APPHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();
		System.out.println("method: " + method);

		if ("GET".equalsIgnoreCase(method)) {
			doGet(exchange);
		} else {
			doPost(exchange);
		}
	}

	private void doPost(HttpExchange exchange) throws IOException {
		//获得表单提交数据(post)
        String postString = IOUtils.toString(exchange.getRequestBody());
        
		String response = "hello doGet";
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}

	private void doGet(HttpExchange exchange) throws IOException {
		//获得查询字符串(get)
		String queryString = exchange.getRequestURI().getQuery();
		System.out.println("queryString: " + queryString);
		
		String response = "hello doGet";
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}
}
