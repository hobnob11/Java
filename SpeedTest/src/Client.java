import java.io.InputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket socket = new Socket("ts3.h08.us", 6666);
		InputStream input = socket.getInputStream();
		long total = 0;
		long start = System.currentTimeMillis();
		
		byte[] bytes = new byte[10240]; //10kb
		while (true) {
			int read = input.read(bytes);
			total += read;
			long cost = System.currentTimeMillis() - start;
			if (cost > 0 && System.currentTimeMillis() % 10 == 0) {
				long bps = (total / cost)*1024*8;
				long kbps = bps/1024;
				long mbps = kbps/1024;
				System.out.println(mbps);
			}
		}
	}
}
