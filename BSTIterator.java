
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BSTIterator {

    Deque<TreeNode> nextNodes;
    List<TreeNode> previosNodes;
    int indexPrevious;

    public BSTIterator(TreeNode root) {
        indexPrevious = -1;
        nextNodes = new ArrayDeque<>();
        previosNodes = new ArrayList<>();
        iterateToNextLeftmostNodeFromCurrentRoot(root);
    }

    public boolean hasNext() {
        return !nextNodes.isEmpty() || indexPrevious + 1 < previosNodes.size();
    }

    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (indexPrevious + 1 < previosNodes.size()) {
            return previosNodes.get(++indexPrevious).val;
        }

        TreeNode nextNode = nextNodes.pop();
        iterateToNextLeftmostNodeFromCurrentRoot(nextNode.right);

        previosNodes.add(nextNode);
        indexPrevious++;

        return nextNode.val;
    }

    public boolean hasPrev() {
        return indexPrevious - 1 >= 0;
    }

    public int prev() {
        if (!hasPrev()) {
            throw new NoSuchElementException();
        }
        return previosNodes.get(--indexPrevious).val;
    }

    private void iterateToNextLeftmostNodeFromCurrentRoot(TreeNode node) {
        while (node != null) {
            nextNodes.push(node);
            node = node.left;
        }
    }
}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
