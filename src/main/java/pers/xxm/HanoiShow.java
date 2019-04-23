package pers.xxm;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by XuXuemin on 19/4/17
 * 自写的Component，添加到JPanel时（默认设置），此Component需要setPreferredSize，否则组件不显示
 * 自写的Component，添加到JPanel时，如果JPanel不执行setLayout(null)，就是有了Layout（居中），此时\
 * 该Component设置的Location和Size都是无效的，此时可以使用setPreferredSize
 */
public class HanoiShow extends JComponent {
    private static final int INIT_ROD = 0;
    private static final int SPARE_ROD = 1;
    private static final int DEST_ROD = 2;

    private Rod[] rods;

    private LinkedList<Integer> movesList;

    public HanoiShow(int num) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // 设置布局方式，横向堆垒
        // 设置最佳大小，父窗体的Layout不为null时有效
        //setPreferredSize(new Dimension(Rod.getLength() * 3, Rod.getLength() + Rod.LABEL_HEIGHT));
        //setBorder(new LineBorder(Color.GREEN)); // 测试

        initComponent(num);
    }

    private void initComponent(int num) {
        movesList = Hanoi.getHanoiMoves(num, INIT_ROD, SPARE_ROD, DEST_ROD); // 递归运行汉诺塔过程
        rods = new Rod[3]; // 初始化三个柱

        add(rods[INIT_ROD] = new Rod("初始", num));
        add(rods[SPARE_ROD] = new Rod("辅助"));
        add(rods[DEST_ROD] = new Rod("目标"));
    }
    /**
     * 更新汉诺塔整个页面的显示组件
     * @param num 圆盘个数
     */
    public void setDiskNumber(int num) {
        removeAll(); // 移除所有组件（这里指Rod）
        initComponent(num); // 初始化组件
        validate(); // 添加的组件重新按照Layout布局好
        repaint(); // 调用paintComponent，重新绘制，并绘制子组件
    }

    /**
     * 转移圆盘
     * @return 转移完成返回true，否则false
     */
    public boolean moveNext() {
        if (movesList.size() == 0) {
            return true;
        }
        // 转移圆盘
        int from = movesList.removeFirst();
        int to = movesList.removeFirst();
        rods[to].pushDisk(rods[from].popDisk());
        rods[from].update();
        rods[to].update();
        return movesList.size() == 0;
    }

}
