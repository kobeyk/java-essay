package com.appleyk.datastructure.redblack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <p>越努力，越幸运 -- 红黑🌲</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class RedBlackTree {

    private Node root;

    private class Node {
        int key;
        boolean color;// 黑色为true
        // 左节点、右节点、父节点
        Node left, right, parent;
        public Node(int key) {
            this.key = key;
        }
    }

    public RedBlackTree(int key) {
        this.root = new Node(key);
        // 根节点，必须为黑色
        this.root.color = true;
    }

    // 插入一个新key
    public void insertKey(int key) {
        insertKey(new Node(key), root);

    }

    /**
     * 插入节点
     *
     * @param child 孩子节点
     * @param node
     */
    public void insertKey(Node child, Node node) {
        // 如果是左节点的话
        if (child.key < node.key) {
            // 如果node的左孩子节点为null，直接把child挂上去,同时为child指定parent
            if (node.left == null) {
                node.left = child;
                child.parent = node;
                fixTree(child);
            } else {
                // 否则，继续递归node的左节点
                insertKey(child, node.left);
            }
        } else {
            // 如果node的右孩子节点为null，直接把child挂上去,同时为child指定parent
            if (node.right == null) {
                node.right = child;
                child.parent = node;
                fixTree(child);
            } else {
                insertKey(child, node.right);
            }
        }
    }

    // 修整（检查修整）树，主要是颜色
    public void fixTree(Node node) {
        // 不能为根节点，且父节点不能是红色
        // 如果父节点为空，一定是根节点，就不需要判断了，所以循环判断的，一定不是根节点，且父节点一定不能是红
        while (node != root && !node.parent.color) {
            // 获取当前节点的父
            Node parent = node.parent;
            // 获取当前节点的祖父节点（就是父节点的父节点）
            Node graderParent = node.parent.parent;
            // 定义一个叔叔节点，目前不知道叔叔节点在哪，是谁
            Node uncle = null;
            // 父节点为左节点的情况,如果父节点等于祖父节点的左（孩子）节点的话
            if (parent == graderParent.left) {
                // 那当前节点的叔叔节点，一定是祖父节点的右（孩子）节点
                uncle = graderParent.right;
                if (uncle == null || uncle.color) {
                    // 第三种情况，先调整成 情况2
                    // 情况3，父节点为左孩子，插入位置为右孩子，uncle为黑或不存在
                    if (parent.right == node) {
                        leftRotate(parent);
                        node = parent;
                    }
                    // 情况2，父节点为左孩子，插入位置为左孩子，uncle节点为黑色或不存在（就是默认影藏的nil节点）
                    // 右侧旋转
                    /** B:black node ,R:red node,C:current node,默认是红色
                     *     B（祖父）               B（父亲）
                     *        / \                   / \
                     *  R（父亲） B（叔叔）  --》  C（新） R（祖父）
                     *      /                           \
                     *  C（新）                          B（叔叔）
                     *
                     *     插入当前节点后，违背连续两个红色节点的原则
                     *     调整：以祖父节点为中心点，右旋转
                     *     原来的父节点图为黑色，祖父节点图为红色
                     */
                    //  父节点图为黑色
                    //  不能直接用parent去图，因为有可能经过情况三的处理，node已经发生变化了
                    node.parent.color = true;
                    //  祖父节点图为红色
                    graderParent.color = false;
                    // 右旋
                    rightRotate(graderParent);
                }
            } else {
                // 父节点为右节点的情况,如果父节点等于祖父节点的右（孩子）节点的话
                uncle = graderParent.left;
                if (uncle == null || uncle.color) {
                    // 第三种情况，先调整成 情况2
                    if (parent.left == node) {
                        rightRotate(parent);
                        node = parent;
                    }
                    //  情况2，进行一次旋转
                    node.parent.color = true;
                    graderParent.color = false;
                    // 左旋
                    leftRotate(graderParent);

                }
            }

            // 情况1，插入位置为左子树，且uncle叔叔节点是红色的，这个跟插入右子树是一个道理
            /** B:black node ,R:red node,C:current node，默认是红色
             *        B
             *       / \
             *      R   R
             *     /
             *    C
             */
            if (uncle != null && !uncle.color) {
                // 只要将父辈节点调整成黑色，祖父（graderParent）节点图红，重新检查即可
                parent.color = true;
                uncle.color = true;
                // 为什么父辈要黑，而祖父要红呢，因为如果
                graderParent.color = false;
                // 把祖父给当前节点，重新循环，去向上检查一下
                node = graderParent;
            }
        }
        // 根节点图黑
        root.color = true;
    }

    public void leftRotate(Node node){
        Node temp = node.right;
        node.right = temp.left;
        if(node.right !=null){
            node.right.parent = node;
        }
        temp.parent = node.parent;
        if(node.parent == null){
            root = temp;
        }else if(node.parent.left == node){
            node.parent.left = temp;
        }else{
            node.parent.right = temp;
        }
        node.parent = temp;
        temp.left = node;
    }

    public void rightRotate(Node node){
        // 节点与自己的右节点进行位置交换
        // 右侧节点变成这个节点的父了
        // 新的parent节点，如果有right节点，挂到原来的父节点上
        Node temp = node.left;
        // node的left节点（是parent节点）的右节点，要挂到新的node的left节点上
        node.left = temp.right;
        if(node.left !=null){
            node.left.parent = node;
        }
        temp.parent = node.parent;
        // 如果没有parent，那就是根节点，与上面右旋的处理是一个道理
        if(root == node){
            root = temp;
        }else if(node.parent.left == node){
            node.parent.left = temp;
        }else{
            node.parent.right = temp;
        }
        node.parent = temp;
        temp.right = node;
    }

    // 遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.key+","+node.color+",");
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree(10);
        Scanner input = new Scanner(System.in);
        int key = input.nextInt();
        while(key!=0){
            tree.insertKey(key);
            tree.levelOrder();
            key = input.nextInt();
        }
        input.close();
    }

}
