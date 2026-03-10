package innerclass;

public class OuterClass2 {
    private static int num = 10;
    // 정적 내부 클래스
    static class InnerClass {
        public void display() {
            System.out.println("num : " + num);
        }
    }
} // end of outer class
