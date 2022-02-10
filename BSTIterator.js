
function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val);
    this.left = (left === undefined ? null : left);
    this.right = (right === undefined ? null : right);
}

/**
 * @param {TreeNode} root
 */
var BSTIterator = function (root) {
    this.indexPrevious = -1;
    this.nextNodes = [];
    this.previosNodes = [];
    this.iterateToNextLeftmostNodeFromCurrentRoot(root);
};

/**
 * @return {boolean}
 */
BSTIterator.prototype.hasNext = function () {
    return this.nextNodes.length > 0 || this.indexPrevious + 1 < this.previosNodes.length;
};

/**
 * @return {number}
 */
BSTIterator.prototype.next = function () {
    if (!this.hasNext()) {
        throw "No Such Element Exception.";
    }

    if (this.indexPrevious + 1 < this.previosNodes.length) {
        return this.previosNodes[++this.indexPrevious].val;
    }

    let nextNode = this.nextNodes.pop();
    this.iterateToNextLeftmostNodeFromCurrentRoot(nextNode.right);

    this.previosNodes.push(nextNode);
    this.indexPrevious++;

    return nextNode.val;
};

/**
 * @return {boolean}
 */
BSTIterator.prototype.hasPrev = function () {
    return this.indexPrevious - 1 >= 0;
};

/**
 * @return {number}
 */
BSTIterator.prototype.prev = function () {
    if (!this.hasPrev()) {
        throw "No Such Element Exception.";
    }
    return this.previosNodes[--this.indexPrevious].val;
};

/**
 * @param {TreeNode} node
 */
BSTIterator.prototype.iterateToNextLeftmostNodeFromCurrentRoot = function (node) {
    while (node !== null) {
        this.nextNodes.push(node);
        node = node.left;
    }
};
