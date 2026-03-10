package _my;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {

    private JLabel backgroundMap;
    private JLabel player;

    // 좌우 방향 이미지를 미리 로드해서 필드로 보관
    private ImageIcon playerL;  // 왼쪽을 바라보는 이미지
    private ImageIcon playerR;  // 오른쪽을 바라보는 이미지

    private final int MOVE_STEP = 10;   // 한 번에 이동할 픽셀

    // 이동 가능한 범위 (배경 크기 - 플레이어 크기)
    private final int MIN_POS = 0;
    private final int MAX_X   = 1000 - 100; // 900
    private final int MAX_Y   = 600  - 100; // 500

    public MyFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("이미지 사용 연습");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지 설정
        ImageIcon backgroundIcon = new ImageIcon("images/backgroundMap.png");
        backgroundMap = new JLabel(backgroundIcon);
        backgroundMap.setSize(1000, 600);
        backgroundMap.setLocation(0, 0);

        // 플레이어 이미지 미리 로드 (방향 전환 시 매번 파일을 읽지 않기 위해)
        playerL = new ImageIcon("images/playerL.png");
        playerR = new ImageIcon("images/playerR.png");

        // 초기 방향: 왼쪽
        player = new JLabel(playerL);
        player.setSize(100, 100);
        player.setLocation(200, 200);
    }

    private void setInitLayout() {
        setLayout(null);
        backgroundMap.add(player);
        add(backgroundMap);
        setVisible(true);
    }

    private void addEventListener() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int x = player.getX();
        int y = player.getY();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                y -= MOVE_STEP;
                break;
            case KeyEvent.VK_DOWN:
                y += MOVE_STEP;
                break;
            case KeyEvent.VK_LEFT:
                x -= MOVE_STEP;
                player.setIcon(playerL); // ← 왼쪽 이미지로 교체
                break;
            case KeyEvent.VK_RIGHT:
                x += MOVE_STEP;
                player.setIcon(playerR); // → 오른쪽 이미지로 교체
                break;
            default:
                return; // 방향키가 아니면 아무것도 안 함
        }

        // 배경 밖으로 나가지 않도록 범위 제한
        x = Math.max(MIN_POS, Math.min(x, MAX_X));
        y = Math.max(MIN_POS, Math.min(y, MAX_Y));

        player.setLocation(x, y);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new MyFrame();
    }
}
