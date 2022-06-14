import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private static TreeNode _root;

    public BinarySearchTree(){
        _root = null;
    }

    static private class TreeNode{
        private int _data;
        private TreeNode _parent;
        private TreeNode _left;
        private TreeNode _right;

        public TreeNode(int data){
            _data = data;
        }

        @Override
        public String toString(){
            return "" + _data;
        }

    }

    public void insert(int d){
        if(_root == null){
            _root = new TreeNode(d);
            return;
        }
        insertInternal(d, _root);
    }

    private void insertInternal(int d, TreeNode n){
        if(n._data < d){
            if(n._right == null){
                n._right = new TreeNode(d);
                n._right._parent = n;
            }
            else{
                insertInternal(d, n._right);
            }
        }
        else{
            if(n._left == null){
                n._left = new TreeNode(d);
                n._left._parent = n;
            }
            else{
                insertInternal(d, n._left);
            }
        }
    }

    public void preOrder(){
        preOrderInternal(_root);
    }

    private void preOrderInternal(TreeNode n){
        if(n != null){
            System.out.println(n._data);
            preOrderInternal(n._left);
            preOrderInternal(n._right);
        }
    }

    public void inOrder(){
        inOrderInternal(_root);
    }

    private void inOrderInternal(TreeNode n){
        if(n != null){
            inOrderInternal(n._left);
            System.out.println(n._data);
            inOrderInternal(n._right);
        }
    }

    public String postOrder(){
        return postOrderInternal(_root);
    }

    private String postOrderInternal(TreeNode n){
        if(n != null){
            return postOrderInternal(n._right) + postOrderInternal(n._left) + n._data;
        }
        return "";
    }

    public boolean find(int d){
        return findInternal(_root, d) != null;
    }

    private TreeNode findInternal(TreeNode node, int value) {
        if(node == null)
            return null;
        if (value == node._data)
            return node;
        else if(value < node._data)
            return findInternal(node._left,value);
        else
            return findInternal(node._right, value);
    }

    public boolean delete(int d){
        return deleteInternal(_root,d);
    }

    private boolean deleteInternal(TreeNode node, int value) {
        TreeNode nodeToDelete = findInternal(node, value);
        if(nodeToDelete == null)
            return false;

        if(nodeToDelete._left == null && nodeToDelete._right == null){ //no children
            if(isLeftChild(nodeToDelete, nodeToDelete._parent)){
                nodeToDelete._parent._left = null;
            }
            else if(isRightChild(nodeToDelete, nodeToDelete._parent)){
                nodeToDelete._parent._right = null;
            }
        }

        else if(nodeToDelete._left != null && nodeToDelete._right == null){ //has left child but no right child
            if(nodeToDelete._data == _root._data){
                _root = nodeToDelete._left;
            } else {
                if (isLeftChild(nodeToDelete, nodeToDelete._parent)) {
                    nodeToDelete._parent._left = nodeToDelete._left;
                } else if (isRightChild(nodeToDelete, nodeToDelete._parent)) {
                    nodeToDelete._parent._right = nodeToDelete._left;
                }
            }

        }

        else if (nodeToDelete._left == null && nodeToDelete._right != null) { //has right child but no left child
            if(nodeToDelete._data == _root._data){
                _root = nodeToDelete._right;
            } else {
                if (isRightChild(nodeToDelete, nodeToDelete._parent)) {
                    nodeToDelete._parent._right = nodeToDelete._right;
                } else if (isLeftChild(nodeToDelete, nodeToDelete._parent)) {
                    nodeToDelete._parent._left = nodeToDelete._right;
                }
            }


        }

        else if(nodeToDelete._left !=null && nodeToDelete._right != null){
            TreeNode nodeToSwap = maxInternal(nodeToDelete._left);
            int val = nodeToSwap._data;
            delete(val);
            nodeToDelete._data = val;

        }

        nodeToDelete._parent = null;

        //TODO: What if root gets deleted?
        return true;
    }

    private boolean isLeftChild(TreeNode node, TreeNode parent){
        return node == parent._left;
    }

    private boolean isRightChild(TreeNode node, TreeNode parent){
        return node == parent._right;
    }
    private boolean isRoot(TreeNode node){
        return node == _root;
    }



    public int min(){
        return minInternal(_root)._data;

    }

    private TreeNode minInternal(TreeNode node){
        while (node._left != null){
            node = node._left;
        }
        return node;
    }

    public int max(){
        return maxInternal(_root)._data;
    }

    private TreeNode maxInternal(TreeNode node){
        while (node._right != null){
            node = node._right;
        }
        return node;
    }

    //TODO: What is the difference between height and level?
  

    public void levelTreePrint() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(_root);
        int count = queue.size();
        while(!queue.isEmpty()){
            TreeNode n = queue.remove();
            System.out.print(n._data + " ");
            count--;

            if(n._left != null) {
                queue.add(n._left);
            }

            if(n._right != null) {
                queue.add(n._right);
            }

            if(count == 0){
                System.out.println();
                count = queue.size();
            }
        }
    }
    public String tree2str(TreeNode t){
        return "";
    }
    public boolean isSameTree(BinarySearchTree q){
        if(postOrder().equals(q.postOrder()))
            return true;
        return false;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(2);
        BinarySearchTree searchTree = new BinarySearchTree();
        _root = node;
        searchTree.insert(1);
        searchTree.insert(10);

        System.out.println(searchTree.heightInternal(node));



    }

}