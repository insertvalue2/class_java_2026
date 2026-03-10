package swing.ch09;

import javax.swing.*;

public class MyFrame extends JFrame {

    private JLabel backgroundMap;
    private JLabel player;

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
        backgroundMap.setLocation(0,0);

        // 플레이어 설정
        ImageIcon playerIcon = new ImageIcon("images/playerL.png");
        player = new JLabel(playerIcon);
        player.setSize(100, 100);
        player.setLocation(200, 200);

    }

    private void setInitLayout() {
        setLayout(null); // 좌표 기반
        backgroundMap.add(player);
        add(backgroundMap);
        setVisible(true);
    }

    private void addEventListener() {

    }

    // 테스트 코드 (메인 쓰레드)
    public static void main(String[] args) {
        new MyFrame();
    }

}
