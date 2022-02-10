
#include<deque>
#include<vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {}

    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}

    TreeNode(int x, TreeNode* left, TreeNode* right) : val(x), left(left), right(right) {}
};

class BSTIterator {
    
public:

    deque<TreeNode*> nextNodes;
    vector<TreeNode*> previosNodes;
    int indexPrevious = -1;

    BSTIterator(TreeNode* root) {
        iterateToNextLeftmostNodeFromCurrentRoot(root);
    }

    bool hasNext() {
        return !nextNodes.empty() || indexPrevious + 1 < previosNodes.size();
    }

    int next() {
        if (!hasNext()) {
            throw out_of_range("No Such Element Exception.");
        }

        if (indexPrevious + 1 < previosNodes.size()) {
            return previosNodes[++indexPrevious]->val;
        }

        TreeNode* nextNode = nextNodes.front();
        nextNodes.pop_front();
        iterateToNextLeftmostNodeFromCurrentRoot(nextNode->right);

        previosNodes.push_back(nextNode);
        indexPrevious++;

        return nextNode->val;
    }

    bool hasPrev() {
        return indexPrevious - 1 >= 0;
    }

    int prev() {
        if (!hasPrev()) {
            throw out_of_range("No Such Element Exception.");
        }
        return previosNodes[--indexPrevious]->val;
    }

    void iterateToNextLeftmostNodeFromCurrentRoot(TreeNode* node) {
        while (node != nullptr) {
            nextNodes.push_front(node);
            node = node->left;
        }
    }
};
