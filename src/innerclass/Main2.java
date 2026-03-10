package innerclass;

public class Main2 {

    public static void main(String[] args) {
        // 경우1
        // Inner Class 일반 멤버 클래스로 설계된 경우
        // 인스턴스 내부 클래스(기본)
        // - 외부 클래스와 강하게 연결됨 상태
        // - 외부 객체가 있어야 내부 객체를 호출할 수 있음.

        // 경우 2
        // static Inner Class 정적 클래스로 설계된 경우
        // - 외부 클래스와 이름만 묶인 상태
        // - 외부 객체 생성없이도 생성 가능
        OuterClass2.InnerClass inner = new OuterClass2.InnerClass();
        inner.display();

    }
}
