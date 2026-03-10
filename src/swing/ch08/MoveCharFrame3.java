package swing.ch08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// 키 리스너를 구현하여 키보드 입력을 받아서 라벨을 움직는 클래스
public class MoveCharFrame3 extends JFrame {

    private JLabel label;
    private int count = 0;

    private final int MOVE_STEP = 10;
    private final int INIT_X = 225;
    private final int INIT_Y = 200;
    private final int FRAME_SIZE = 500;

    public MoveCharFrame3() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("방향키로 별 움직이기 실습");
        setSize(FRAME_SIZE, FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        label = new JLabel("★");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        label.setSize(50, 50); // 라벨 자체의 크기
        label.setLocation(INIT_X, INIT_Y);   // 초기 시작 위치 (중앙 부근)
    }

    private void setInitLayout() {
        // 중요
        setLayout(null); // 좌표로 직접 움직이기 위해 기본 레이아웃을 제거 합니다.
        add(label);
        setVisible(true);
    }

    private void addEventListener() {
        // 1. 프레임에 키 리스너를 등록(나 자신을 리스너로 임명)
        // KeyAdapter key1 = new KeyAdapter() {};
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // ....
            }
            @Override
            public void keyReleased(KeyEvent e) {
                // ....
            }
        });

        // 필수
        // 키보드 이벤트를 받기 위해 프레임이 포커스를 가질 수 있게 합니다.
        this.setFocusable(true);
        this.requestFocusInWindow();
    }


    public static void main(String[] args) {
        new MoveCharFrame3();
    }// end of main
}
