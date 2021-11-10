package ex01;

class NewThread2 implements Runnable{

	
	//(t1이 들고있는 steck)
	@Override
	public void run(){
		for (int i = 0; i < 21; i++) {
			System.out.println("새로운 스레드:"+ i); //target
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //static 함수 1/1000초 
		}  	
	}
} 

public class ThreadEx02 {
	
	//일꾼 하나 = 메인 쓰래드 (context -swithing) 
	//일 = 최대 퍼포먼스
	//딜레이 걸기 =  Thread.sleep()
	public static void main(String[]args) {
		
		Thread t1 = new Thread(new NewThread2());
		t1.start(); // run 함수가 실행 (target)
		
		//메인 쓰래드 
		for (int i = 0; i < 21; i++) {
			System.out.println("메인 스레드:"+ i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //static 함수 1/1000초 
		}  
	}

}
