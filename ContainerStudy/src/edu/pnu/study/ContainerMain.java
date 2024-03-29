package edu.pnu.study;

import java.util.ArrayList;
import java.util.Scanner;

import edu.pnu.study.operator.MyOperatorContainer;

public class ContainerMain {

	// Thread 종료 조건 변수 /자바는 메인스레드가 종료되도 자식 스레드는 종료되지 않고 프로세스가 두개가 됨 
	private boolean threadCheck = true;
	// Operator 문자열 저장 배열
	private ArrayList<String> list = new ArrayList<>();
	private MyOperatorContainer moc = new MyOperatorContainer();

	public static void main(String[] args) {
		ContainerMain cm = new ContainerMain();

		// 일정 시간동안 사용되지 않고 있는 Operator 객체를 제거하는 Thread
		Thread th = new Thread(() -> {
			MyOperatorContainer moc = cm.getContainer();

			// 메인 Thread가 종료하면 같이 종료되도록 종료 조건 변수(threadCheck)를 확인한다.
			while(cm.getThreadCheck()) {
				
				// 생존 기준 시간인 millisecond를 넘겨서 사용되지 않은 객체는 제거한다.
				moc.releaseOldObject(10000);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("쓰레드를 종료합니다.");
		});
		// Thread 시작
		th.start(); 
		//th.start(); > 릴리즈 수정 안하고 주석달아도 됨.
		
		System.out.println("프로그램을 시작합니다.");
		
		cm.doTest();
		
		System.out.println("프로그램을 종료합니다.");
	}
	
	public MyOperatorContainer getContainer() {
		return moc;
	}
	
	public boolean getThreadCheck() {
		return threadCheck;
	}
	
	private boolean isOperator(String str) {
		if (list.size() == 0) {
			list.add("+"); list.add("-"); list.add("*"); list.add("/");
		}
		if (list.contains(str))
			return true;
		return false;
	}
	
	private void printCmd(String cmd) {
		switch(cmd) {
		case "+": System.out.println("더하기 연산을 합니다.");	break;
		case "-": System.out.println("빼기 연산을 합니다.");		break;
		case "*": System.out.println("곱하기 연산을 합니다.");	break;
		case "/": System.out.println("나누기 연산을 합니다.");	break;
		}
	}

	public void doTest() {

		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("연산자를 입력하세요.(+,-,*,/) : ");
			String cmd = sc.next();

			if (isOperator(cmd) == false) {
				sc.close();
				threadCheck = false;
				return;
			}
			printCmd(cmd);
			
			System.out.print("첫번째 숫자 : ");		int f = sc.nextInt();
			System.out.print("두번째 숫자 : ");		int s = sc.nextInt();
			
			System.out.println("연산 결과 : " + moc.doOperate(cmd, f, s));
			System.out.println("-".repeat(40));
		}
	}
}
