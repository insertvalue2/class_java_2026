package innerclass;

public class OuterClass1 {

    private int num = 10;
    // 클래스 파일안에 내부에 또 클래스 선언 (중첩클래스) // 인스턴스 내부 클래스
    class InnerClass {
        public void display() {
            System.out.println("num : " + num);
        }
    }
} // end of outer class
