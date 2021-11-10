package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

//소캣 서버 = StateFul 서버 (자기가 임의로 기다릴수도 있고 요청할수도 있다) 
//StateFul + get,post(protocol)을 가저오면 http로 만들수 있다. 	
//HTTP의 웹서버: 기다리는 친구
public class MyServerSocket {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader br;
	
	public MyServerSocket() {	
		try {
			serverSocket =  new ServerSocket(10000);
			System.out.println("클라이언트로 부터 접속 대기중");
			socket = serverSocket.accept(); //클라이언트가 접속할때까지 락이 걸림.
			System.out.println("클라이언트 연결 완료");
			
			//읽어온다 (inputstream) 문자를 가변적으로 읽겠다. 
			br =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String input  = null;
			
			
			while((input = br.readLine()) != null){
				System.out.println("클라이언트:" + input);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//main 달기(Client)연결용 
	public static void main(String[] args) {
		new MyServerSocket();
	}
}
