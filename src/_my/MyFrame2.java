package _my;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 플레이어 이동 + 적군 자동 이동 (Thread 활용)
 *
 * [새로 배우는 것]
 * - Thread : 적군을 백그라운드에서 자동으로 움직이게 하는 별도 작업자
 * - Runnable : Thread 가 실행할 작업을 정의하는 인터페이스
 */
public class MyFrame2 extends JFrame implements KeyListener {

    // ── 배경 & 플레이어 ──────────────────────────
    private JLabel backgroundMap;
    private JLabel player;
    private ImageIcon playerL;
    private ImageIcon playerR;

    // ── 적군 ─────────────────────────────────────
    private JLabel enemy;
    private ImageIcon enemyL;
    private ImageIcon enemyR;

    // ── 이동 설정 ─────────────────────────────────
    private final int MOVE_STEP  = 10;   // 플레이어 이동 픽셀
    private final int ENEMY_STEP = 5;    // 적군 이동 픽셀
    private final int DELAY_MS   = 50;   // 적군 이동 간격 (ms) - 숫자가 작을수록 빠름

    // ── 범위 제한 ─────────────────────────────────
    private final int MIN_POS = 0;
    private final int MAX_X   = 1000 - 100; // 900
    private final int MAX_Y   = 600  - 100; // 500

    public MyFrame2() {
        initData();
        setInitLayout();
        addEventListener();
        startEnemyThread(); // 적군 자동 이동 Thread 시작
    }

    private void initData() {
        setTitle("플레이어 이동 + 적군 자동 이동");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경
        ImageIcon backgroundIcon = new ImageIcon("images/backgroundMap.png");
        backgroundMap = new JLabel(backgroundIcon);
        backgroundMap.setSize(1000, 600);
        backgroundMap.setLocation(0, 0);

        // 플레이어 - 상단 중앙
        playerL = new ImageIcon("images/playerL.png");
        playerR = new ImageIcon("images/playerR.png");
        player  = new JLabel(playerL);
        player.setSize(100, 100);
        player.setLocation(450, 50);

        // 적군 - 하단 중앙에서 시작
        enemyL = new ImageIcon("images/enemyL.png");
        enemyR = new ImageIcon("images/enemyR.png");
        enemy  = new JLabel(enemyR); // 처음엔 오른쪽을 바라봄
        enemy.setSize(100, 100);
        enemy.setLocation(100, 500); // 하단 배치
    }

    private void setInitLayout() {
        setLayout(null);
        backgroundMap.add(player);
        backgroundMap.add(enemy);  // 적군도 배경 위에 올리기
        add(backgroundMap);
        setVisible(true);
    }

    private void addEventListener() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    // ── Thread 시작 ───────────────────────────────
    private void startEnemyThread() {

        // Runnable: Thread 가 실행할 작업을 익명 클래스로 정의
        Runnable enemyTask = new Runnable() {
            @Override
            public void run() {
                boolean movingRight = true; // true = 오른쪽 이동 중

                while (true) { // 게임이 끝날 때까지 계속 반복

                    // 방향에 따라 x 좌표 이동
                    int x = enemy.getX();

                    if (movingRight) {
                        x += ENEMY_STEP;
                        enemy.setIcon(enemyR); // 오른쪽 이미지
                    } else {
                        x -= ENEMY_STEP;
                        enemy.setIcon(enemyL); // 왼쪽 이미지
                    }

                    // 오른쪽 끝(800)에 닿으면 방향 전환
                    if (x >= 800) movingRight = false;

                    // 왼쪽 끝(0)에 닿으면 방향 전환
                    if (x <= 0)   movingRight = true;

                    // 계산된 위치로 이동
                    enemy.setLocation(x, enemy.getY());

                    // DELAY_MS 만큼 잠깐 대기 후 다음 이동
                    try {
                        Thread.sleep(DELAY_MS);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };

        // Thread 생성 + 시작
        Thread thread = new Thread(enemyTask);
        thread.start(); // → 이 순간부터 enemyTask.run() 이 별도 스레드에서 실행!
    }

    // ── 플레이어 키 이벤트 ────────────────────────
    @Override
    public void keyPressed(KeyEvent e) {
        int x = player.getX();
        int y = player.getY();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    y -= MOVE_STEP; break;
            case KeyEvent.VK_DOWN:  y += MOVE_STEP; break;
            case KeyEvent.VK_LEFT:
                x -= MOVE_STEP;
                player.setIcon(playerL);
                break;
            case KeyEvent.VK_RIGHT:
                x += MOVE_STEP;
                player.setIcon(playerR);
                break;
            default: return;
        }

        x = Math.max(MIN_POS, Math.min(x, MAX_X));
        y = Math.max(MIN_POS, Math.min(y, MAX_Y));

        player.setLocation(x, y);
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new MyFrame2();
    }
}