package com.zw.test;

import java.net.InetSocketAddress;
import java.nio.IntBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Test17 {
	public static void main(String[] args) {
		IntBuffer intBuffer = IntBuffer.allocate(1024);

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(8080));

			Selector selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			int numFD = selector.select();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
