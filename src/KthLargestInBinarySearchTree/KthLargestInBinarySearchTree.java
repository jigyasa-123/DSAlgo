package KthLargestInBinarySearchTree;

import java.io.*;

// Main class should be named 'Solution' and should not be public.

class Node{
    int data;
    Node left;
    Node right;
    public Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}

class BinarySearch{
    public int findKLargest(Node root,int k){

        if (root == null) return -1;

        Node curr = root;
        int cnt = 0;

        while (curr != null) {
            if (curr.right == null) {
                cnt++;


                if (cnt == k) return curr.data;

                curr = curr.left;
            } else {
                Node succ = curr.right;

               while (succ.left != null) {
                    succ = succ.left;
                }

                succ.left = curr;
                Node temp =curr;
                curr = curr.right;
                temp.right = null;


            }
        }

        return -1;

    }
}
class KthLargestInBinarySearchTree {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(4);
        root.right = new Node(20);
        root.left.left = new Node(2);
        root.right.left = new Node(15);
        root.right.right = new Node(40);

        BinarySearch binarySearch = new BinarySearch();

        System.out.println(binarySearch.findKLargest(root,7));

    }
}

//Tc: O(n) SC: O(1)
// for simpler approach, inorder traversal (left to right) and then return size -k