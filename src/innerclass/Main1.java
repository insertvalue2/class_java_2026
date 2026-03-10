package innerclass;

public class Main1 {

    public static void main(String[] args) {
        // 내부 클래스가 일반 멤버 클래스로 설계된 경우
        // 외부 클래스 객체가 생성되고 내부 클래스를 생성할 수 있다.
        OuterClass1 outerClass = new OuterClass1();
        OuterClass1.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();
    }
}
