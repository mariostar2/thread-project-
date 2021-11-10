package ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// 소켓 서버 = StateFul
public class MyServerSocket {

   private ServerSocket serverSocket;

   public MyServerSocket() {
      try {
         serverSocket = new ServerSocket(10000);
         while(true) { // 메인스레드 = 데몬 스레드
            System.out.println("클라이언트로 부터 접속 대기중");
            Socket socket = serverSocket.accept(); // 클라이언트가 접속할 때까지 락이 걸림.
            System.out.println("클라이언트 연결 완료");

            Thread t = new Thread(new InnerThread(socket));
            t.start();
         }

         
      } catch (IOException e) {
    	  System.out.println("클라이언트와의 메시지 통신에 오류가 발생하였습니다.");
      }
   }

   class InnerThread implements Runnable {

      private Socket socket;
      
      public InnerThread(Socket socket) {
         this.socket = socket;
      }
      
      private BufferedReader br;
      
      @Override
      public void run() {
         
         try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input = null;

            // 겁나 바빠요
            while ((input = br.readLine()) != null) {
               System.out.println("클라이언트 : " + input);
            }
         } catch (Exception e) {
        	 System.out.println("클라이언트와의 메시지 통신에 종료되었습니다.");
        	 try {
				socket.close();
				br.close();
				//메모리 릭:메모리에 누수가 생김  
			} catch (IOException e1) {
				System.out.println("메모리의 릭이 발생하였습니다");
				e1.printStackTrace();
			}
         }
         
      }

   }

   public static void main(String[] args) {
      new MyServerSocket();
   }
}