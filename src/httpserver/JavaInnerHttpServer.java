package httpserver;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpServer;

public class JavaInnerHttpServer {
	public static Executor executor  = new ThreadPoolExecutor(100, 100, 60, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
			
	public static final int SERVER_PORT = 8001;

	public static void main(String[] args) {
		try {
			//创建http服务器，绑定本地端口
			HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
			//创建上下文监听,拦截包含/test的请求
			/**
			 * 创建监听的上下文, 请求 URI 根路径的匹配, 根据不同的 URI 根路径选择不同的 HttpHandler 处理请求,
			 * 路径必须以 "/" 开头。路径 "/" 表示匹配所有的请求 URI（没有其他更具体的匹配路径除外）。
			 */
			server.createContext("/test", new APPHandler());
			server.createContext("/test1", new APPHandler());
			
			// 设置请求处理的线程池 默认是单线程
			server.setExecutor(executor);
			
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
