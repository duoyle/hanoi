package pers.xxm;

import java.util.LinkedList;

/**
 * Created by XuXuemin on 19/4/17
 */
public class Hanoi {
    private LinkedList<Integer> movesList; // 汉诺塔移动过程结果列表

    public Hanoi() {
        movesList = new LinkedList<>();
    }

    /**
     * 生成汉诺塔移动过程结果
     * @param n 圆盘个数
     * @param from 来源位置
     * @param spare 辅助位置
     * @param dest 目标位置
     * @return
     */
    public static LinkedList<Integer> getHanoiMoves(int n, int from, int spare, int dest) {
        Hanoi runner = new Hanoi();
        runner.hanoi(n, from, spare, dest);
        return runner.movesList;
    }

    /**
     * 汉诺塔将num个盘从from移动到destination，辅助盘为spare
     * @param num 圆盘个数
     * @param from 来源位置
     * @param spare 辅助位置
     * @param destination 目标位置
     */
    private void hanoi(int num, int from, int spare, int destination) {
        if (num == 1) {
            move(num, from, destination);
        } else {
            hanoi(num - 1, from, destination, spare); // 将num-1个先移动到spare
            move(num, from, destination); // 第num个从from移动到destination
            hanoi(num - 1, spare, from, destination); // 将spare上的num-1个移动到destination
        }
    }

    /**
     * 将第num个盘从from移动到destination
     * @param num 圆盘序号
     * @param from 来源位置
     * @param destination 目标位置
     */
    private void move(int num, int from, int destination) {
        // 记录移动记录，两条为一个移动记录，编号暂时不用
        movesList.addLast(from);
        movesList.addLast(destination);
    }
}

