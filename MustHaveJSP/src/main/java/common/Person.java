package common; //기본 패키지 이외의 패키지(규약1번)

public class Person {
	private String name; // private 멤버 변수(규약2번)
	private int age; // private 멤버 변수(규약2번)
	// public Person() {} //기본 생성자(규약3번)

	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

} // 자바 빈즈 객체