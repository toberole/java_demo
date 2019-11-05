package com.zw.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Sender {
	private static boolean sw = false;
	private static int MAX_COUNT = 3;
	private static int PORT = 9090;
	private static String IP = "255.255.255.255";

	public static void main(String[] args) throws Exception {
		if (true) {
			start_client();
		}
		if (args == null) {
			System.out.println("args==null");
			return;
		}

		if (args.length < 1) {
			System.out.println("请传递参数：-s or -c");
			return;
		}

		String arg = args[0];
		System.out.println("args[0]: " + args[1]);
		if (arg.equalsIgnoreCase("-c")) {
			start_client();
		} else {
			start_server();
		}
	}

	private static void start_server() throws SocketException {
		System.out.println("**** start_server ****");

		// 建立udp的服务
		DatagramSocket datagramSocket = new DatagramSocket();
		// 准备数据，把数据封装到数据包中。
		String data = "hello client...";
		// 创建了一个数据包
		DatagramPacket packet;
		int count = 0;
		try {
			while (true) {
				Thread.sleep(1500);
				count++;
				if (count > MAX_COUNT && sw) {
					break;
				}
				packet = new DatagramPacket(data.getBytes(), data.getBytes().length, 
						InetAddress.getByName(IP), PORT);
				System.out.println("server send data ...");
				datagramSocket.send(packet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源 ---实际上就是释放占用的端口号
			if (datagramSocket != null) {
				datagramSocket.close();
			}
		}
	}

	private static void start_client() throws SocketException {
		System.out.println("**** start_client ****");

		// 建立udp的服务 ，并且要监听一个端口。
		DatagramSocket socket = new DatagramSocket(PORT);

		// 准备空的数据包用于存放数据。
		byte[] buf = new byte[1024];
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length); // 1024

		// 调用udp的服务接收数据
		try {
			int count = 0;
			while (true) {
				count++;
				if (count > MAX_COUNT && sw) {
					break;
				}
				System.out.println("client receive data ...");
				socket.receive(datagramPacket);
				System.out.println(datagramPacket.getAddress().getHostAddress() + " sys "
						+ new String(buf, 0, datagramPacket.getLength())); // getLength()
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
}
