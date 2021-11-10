  package ex02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class MyClientSocket {

	private Socket socket;
	private BufferedWriter bw;
	private PrintWriter pw;
	
	public MyClientSocket() {
	
		try {
			socket  = new Socket("localhost", 10000);
			
			//공부용
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//개발용 
			//pw = new PrintWriter(socket.getOutputStream(),true);
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
			String keyboardInput = sc.nextLine(); //락이 걸림 
			bw.write(keyboardInput +"\n");
			bw.flush();		
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//main 필요 (client)
	public static void main(String[] args) {
		new MyClientSocket();
	}
}
