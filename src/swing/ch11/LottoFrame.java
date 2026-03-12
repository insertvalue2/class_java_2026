package swing.ch11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 클래스의 역할 - 로또 게임 UI 전담 클래스
 * 담당 역할 :
 * - 화면 구성 (버튼, 번호 출력 패널)
 * - 버튼 클릭시 이벤트 처리
 * - LottoRandomNumber 클래스에게 번호 생성을 요청하고 응답 받아 결과를 화면에 표시
 */
public class LottoFrame extends JFrame implements ActionListener {

    // 상수 모음
    private static final String INITIAL_MESSAGE = "Game Start 버튼을 눌러주세요";
    private static final String FONT_NAME = "고딕";
    private static final int FONT_SIZE = 20;
    private static final int CIRCLE_SIZE = 50;

    private JButton button;
    private LottoPanel lottoPanel;
    private LottoRandomNumber lottoRandomNumber;
    private int[] createNumbers; // 현재 화면에 표시할 번호 배열 선언

    private boolean isInitialState = true;

    public LottoFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("Lotto Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Game Start");
        lottoRandomNumber = new LottoRandomNumber();
        lottoPanel = new LottoPanel();
        createNumbers = new int[LottoRandomNumber.LOTTO_NUMBER_COUNT];
    }

    private void setInitLayout() {
        setLayout(new BorderLayout());
        add(button, BorderLayout.NORTH);
        add(lottoPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addEventListener() {
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isInitialState = false;
        createNumbers = lottoRandomNumber.createNumber();
        lottoPanel.repaint(); // lottoPanel 의 paintComponent() 를 다시 호출
    }

    private class LottoPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 이전 그림을 지움
            g.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));

            if(isInitialState) {
                // 버튼을 아직 누르지 않은 초기 상태
                g.setColor(Color.GRAY);
                g.drawString(INITIAL_MESSAGE, 160, 150);
            } else {
                // 번호 6개를 원 안에 표시
                for (int i = 0; i < createNumbers.length; i++) {

                    int x = 60 + (i * 80);
                    int y = 80;

                    // 번호 값에 따라 공 색상 구분
                    g.setColor(getBallColor(createNumbers[i]));
                    g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);

                    // 원 테두리 설정
                    g.setColor(Color.DARK_GRAY);
                    g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);

                    // 원 안에 숫자 출력
                    g.setColor(Color.WHITE);
                    String numStr =  String.valueOf(createNumbers[i]);
                    g.drawString(numStr, x + 17 , y + 33 );

                }
            }
        }
    }
    /**
     * 로또 번호 범위에 따라 공 색상 반환
     * 1~10  : 노란색
     * 11~20  : 파란색
     * 21~30  : 빨간색
     * 31~40  : 회색
     * 41~45  : 초록색
     */
    private Color getBallColor(int number) {
        if(number <= 10) return new Color(255, 200, 0); // 노란색
        if(number <= 20) return new Color(0, 120, 50); // 파란색
        if(number <= 30) return new Color(220, 50, 50); // 빨간색
        if(number <= 40) return new Color(130, 130, 130); // 회색
        return new Color(50, 180, 80);
    }

    // main 함수
    public static void main(String[] args) {
        new LottoFrame();
    }
}





