package pers.xxm;

import javax.swing.*;

/**
 * Created by XuXuemin on 19/4/18
 * 主函数退出时，主线程退出，但是窗口程序仍然运行，估计有非守护线程（UI线程）
 * 一个Frameadd两个Panel第一个被覆盖，无效了。
 */
public class MainFrame extends JFrame {
    private static final String TITLE = "汉诺塔过程演示";
    private static final int GAP = 40;
    private static final int DELAY = 1000;
    private static final int INIT_COUNT = 3;

    private HanoiShow hanoiShow;
    private Timer timer;

    public MainFrame() {
        initComponent();
    }

    /**
     * 初始化窗体组件
     */
    private void initComponent() {
        JPanel mainPanel = new JPanel(); // Frame中的主面板
        JPanel titlePanel = new JPanel(); // 标题面板
        JPanel hanoiPanel = new JPanel(); // 汉诺塔面板
        JPanel menuPanel = new JPanel(); // 操作按钮面板
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titlePanel);
        mainPanel.add(hanoiPanel);
        mainPanel.add(menuPanel);

        // 标题显示部分
        titlePanel.add(new JLabel("Hanoi Tower Show Process"));

        // 汉诺塔演示部分
        hanoiShow = new HanoiShow(INIT_COUNT); // 添加到Panel时，默认采用左右居中，上面GAP为5的Layout
        // hanoiPanel.setBackground(Color.GRAY); // 有效，Component设置无效（可能先设置其他）
        hanoiPanel.add(hanoiShow);

        createMenu(menuPanel); // 创建菜单部分

        add(mainPanel); // 添加主面板

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 必须设置大小，使用BoxLayout报错
        setSize(hanoiPanel.getPreferredSize().width + GAP, titlePanel.getPreferredSize().height
            + hanoiPanel.getPreferredSize().height + menuPanel.getPreferredSize().height + GAP);
        setTitle(TITLE);
        setVisible(true);
    }

    /**
     * 创建操作菜单
     * @param menuPanel
     */
    private void createMenu(JPanel menuPanel) {
        String start = "开始";
        String stop = "停止";
        String end = "结束";
        JButton animateButton = new JButton(start);
        // 自动转移按钮添加监听事件
        animateButton.addActionListener(event -> {
            if (animateButton.getText().equals(start)) {
                timer.start();
                animateButton.setText(stop);
            } else if (animateButton.getText().equals(stop)) {
                timer.stop();
                animateButton.setText(start);
            }
        });
        // 自动控制的计时器
        timer = new Timer(DELAY, event -> {
            if (hanoiShow.moveNext()) {
                timer.stop();
                animateButton.setText(end);
            }
        });
        JLabel diskNumberLabel = new JLabel("圆盘数量：");
        JComboBox<Integer> diskNumberSelection = new JComboBox<>(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        diskNumberSelection.setSelectedItem(INIT_COUNT); // 设置初始选择项
        // 修改圆盘数量
        diskNumberSelection.addActionListener(event -> {
            timer.stop();
            animateButton.setText(start);
            hanoiShow.setDiskNumber((int)diskNumberSelection.getSelectedItem());
        });
        JButton nextButton = new JButton("下一步");
        // 下一步按钮
        nextButton.addActionListener(event -> {
            timer.stop();
            hanoiShow.moveNext();
            if (animateButton.getText().equals(stop)) {
                animateButton.setText(start);
            }
        });

        // 设置菜单按钮
        menuPanel.add(diskNumberLabel);
        menuPanel.add(diskNumberSelection);
        menuPanel.add(nextButton);
        menuPanel.add(animateButton);
    }
}
