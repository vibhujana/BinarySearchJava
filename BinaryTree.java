import com.sun.source.tree.Tree;

public class BinaryTree {
    private TreeNode _root;

    static private class TreeNode{
        private final int _data;
        private final TreeNode _left;
        private final TreeNode _right;

        public TreeNode(int data){
            this(data,null,null);
        }

        public TreeNode(int data, TreeNode left, TreeNode right){
            _data = data;
            _left = left;
            _right = right;
        }

    }

    public BinaryTree(TreeNode node){
        _root = node;
    }

    public void print(){
        printInternal(_root);
    }

    private void printInternal(TreeNode node){
        if(node != null){
            printInternal(node._left);
            printInternal(node._right);
            System.out.println(node._data);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(
                new TreeNode(10,
                             new TreeNode(5, new TreeNode(1), new TreeNode(6)),
                             new TreeNode(20, new TreeNode(16), new TreeNode(25))
                )
        );
        tree.print();
    }

}
