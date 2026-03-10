package innerclass;

public class SpaceshipMain {

    public static void main(String[] args) {

        // 정적 내부클래라서 바로 생성 가능함
        Spaceship.Engine engine1 = new Spaceship.Engine();
        Spaceship spaceship = new Spaceship();
        spaceship.addEngine(engine1);
        spaceship.startSpaceShip();

    } // end of main
}
