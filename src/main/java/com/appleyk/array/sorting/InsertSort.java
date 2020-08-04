package com.appleyk.array.sorting;

/**
 * <p>越努力，越幸运 -- 插入排序</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class InsertSort {

    static int[] intArr_bubbling = new int[]{1, 24, 12, 0, 22, 8, 35, 6, 7, 11};
    static int[] intArr_select   = new int[]{1, 24, 12, 0, 22, 8, 35, 6, 7, 11};
    static int[] intArr_insert   = new int[]{1, 24, 12, 0, 22, 8, 35, 6, 7, 11};
    static int len = intArr_bubbling.length;

    public static void bubblingSort() {
        // 外循环
        for (int out = 0; out < len - 1; out++) {
            // 内循环，一定要注意，每内循环一次，比较次数就会减少，因为out在++
            // 因为，大的都往后移了，前面都是小的，没必要在重新回过头再比较一次了
            for (int in = 0; in < len-out-1; in++) {
                if (intArr_bubbling[in] > intArr_bubbling[in + 1]) {
                    // 大的值，往后移
                    swap(intArr_bubbling, in, in + 1);
                }
            }
        }
        show(intArr_bubbling);
    }

    public static void selectSort() {
        // 标记下元素最小的那个下标
        int min;
        for (int out = 0; out < len - 1; out++) {
            // 假设最小的那个数就是第一个，反正还没有比较，我就先假设是第一个
            min = out;
            // 注意，每轮比较后，内循环的次数都在减少
            for (int in = out + 1; in < len; in++) {
                // 如果当前比较的数要小于假设的最小下标的那个数
                if (intArr_select[in] < intArr_select[min]) {
                    // 则把当前最小下标的荣誉称号重新赋值给min
                    min = in;
                }
            }
            // 一轮比较后，把最开始的那个假设最小的数和真正最小的那个数进行一次交换
            // 这样每次比较后，最小的那个数一定在最左边
            swap(intArr_select, out, min);
        }
        show(intArr_select);
    }

    public static void insertSort() {
        for (int out = 1; out < len; out++) {
            // 选一个标记对象，这个标记对象的下标比较有意思，是从1开始的，因为第1（下标0）个元素你跟谁比较啊！！！
            int mark = out;
            // 被选出来的天选之子，和它前面的数（mark-1）进行比较，如果标记的数比他前面的数小，
            // 不好意思，前面的数移到当前标记数的位置上
            // 如果标记的数比他前面的数还要大，不好意思，不用比较了，循环终止，因为大的在右边，根本不用比较
            // 然后，依次循环，直到mark标记和最后一个数（就是最前面的数）比较结束后，循环终止（即mark=0）
            while (mark > 0 && intArr_insert[mark] < intArr_insert[mark - 1]) {
                // 把大的数，往后移动
                swap(intArr_insert,mark - 1, mark);
                // 每比较一次次且交换完数字后，记得mark-1，把比较的机会留给下一个数
                mark--;
            }
        }
        show(intArr_insert);
    }

    public static void main(String[] args) {
        // 冒泡排序 -- 比较次数多、交换次数多
        bubblingSort();
        // 选择排序 -- 比较次数多，交换次数少
        selectSort();
        // 插入排序 -- 比较次数少，交换次数少
        insertSort();
    }

    public static void show(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int pre, int next) {
        int temp = arr[pre];
        arr[pre] = arr[next];
        arr[next] = temp;
    }
}
