package pers.xxm;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Created by XuXuemin on 19/4/17
 */
public class Rod extends JComponent {
    public static final int DISK_CAPACITY = 7;
    public static final int ROD_THICKNESS = 10;
    public static final int LABEL_HEIGHT = 30;

    private String name; // 显示名称

    // 所有圆盘对象
    private Stack<Disk> disks = null;

    /**
     * 构建空的轴空间
     * @param name 轴名称
     */
    public Rod(String name) {
        this(name, 0); // 调用本类构造方法，父类用super
    }

    /**
     * 构建初始化圆盘数的轴空间
     * @param name 轴名称
     * @param numberOfDisk 圆盘数量
     */
    public Rod(String name, int numberOfDisk) {
        this.name = name;
        // 堆叠圆盘
        disks = new Stack<>();
        for (int i = 0; i < numberOfDisk; i++) {
            disks.push(new Disk(numberOfDisk - i - 1, i));
        }
        initComponent();
    }

    /**
     * 初始化页面组件
     */
    private void initComponent() {
        JLabel tipLabel = new JLabel(name, JLabel.CENTER);
        tipLabel.setSize(getLength(), LABEL_HEIGHT);
        tipLabel.setLocation(0, getLength());
        add(tipLabel);

        // 设置大小
        setPreferredSize(new Dimension(getLength(), getLength() + LABEL_HEIGHT));
    }

    /**
     * 计算柱子的长度（横柱和树柱相同）
     * @return 柱子的长度
     */
    public static int getLength() {
        return Disk.getThickness() * (DISK_CAPACITY + 1) + ROD_THICKNESS; // 一个柱子高出一个厚度和柱子粗度
    }

    /**
     * 添加圆盘操作
     * @param disk 添加的圆盘
     * @return 返回添加的该圆盘
     */
    public Disk pushDisk(Disk disk) {
        disk.setOrder(disks.size());
        return disks.push(disk);
    }

    /**
     * 删除最后添加的圆盘
     * @return 返回本次删除的圆盘
     */
    public Disk popDisk() {
        return disks.pop();
    }


    /**
     * 更新数据和组件
     */
    public void update() {
        repaint(); // 重新绘制，出发paintComponent方法
    }

    /**
     * 继承了JComponent，这里的Graphics是自身的
     * 调用时所有已绘制的内容自动擦除？实践证明似乎如此
     */
    @Override
    public void paintComponent(Graphics g) {
        ((Graphics2D)g).setStroke(new BasicStroke(ROD_THICKNESS)); // stroke表示跨度，粗细
        g.setColor(Colors.rodColors[0]); // 水平面板颜色
        g.fillRect(0, getLength() - ROD_THICKNESS, getLength(), ROD_THICKNESS); // 横向轴
        g.fillRect((getLength() - ROD_THICKNESS) / 2, 0, ROD_THICKNESS, getLength()); // 纵向轴
        for(Disk disk: disks) {
            disk.paint(g);
        }
    }
}
