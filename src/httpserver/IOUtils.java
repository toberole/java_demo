package httpserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
	public static String toString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		while((len=inputStream.read(buffer,0,buffer.length))>0) {
			byteArrayOutputStream.write(buffer,0,len);
		}
		String reString = byteArrayOutputStream.toString();
		byteArrayOutputStream.close();
		return reString;
	}
}
