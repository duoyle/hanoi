package pers.xxm;

import java.awt.*;

/**
 * Created by XuXuemin on 19/4/17
 */
public class Disk {
    public static final int UNIT_SIZE = 15; // 单位大小，厚度设两层

    private int number; // 磁盘编号，从小到大0到n
    private int order; // 在柱上的顺序，从下到上0到n

    public static int getThickness() {
        return UNIT_SIZE * 2;
    }

    /**
     * 创建圆盘对象
     * @param number 从0开始的编号
     * @param order 顺序
     */
    public Disk(int number, int order) {
        this.number = number;
        this.order = order;
    }

    /**
     * 设置顺序
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * 绘制圆盘
     * @param g 父容器的绘制对象
     */
    public void paint(Graphics g) {
        // 从预配颜色中设置颜色
        g.setColor(Colors.diskColors[number % Colors.diskColors.length]); // 轮询使用颜色
        // 绘制圆盘形状
        g.fillRect(getThickness() / 2 * (Rod.DISK_CAPACITY - number), getThickness() * (Rod.DISK_CAPACITY - order),
                getThickness() * (number + 1) + Rod.ROD_THICKNESS, getThickness());
    }


}
