import java.util.Arrays;
import java.util.stream.Collectors;

class Parent {
    void show() {
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    @Override
    void show() {
        super.show(); // invokes parent method
        System.out.println("Child show");
    }

    void show(String msg) { // Overloading
        System.out.println(msg);
    }

    int show(int msg) { // Overloading
        System.out.println(msg);
        return msg;
    }
}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
    }
}

class Test {

    void rotate(int[] arr, int k) {
        int n = arr.length;
        // this is used to handle the case when k is greater than n
        k = k % n;
        // this is used to reverse the entire array
        reverse(arr, 0, n - 1);
        // this is used to reverse the first k elements
        reverse(arr, 0, k - 1);
        // // this is used to reverse the remaining elements
        reverse(arr, k, n - 1);
    }

    void reverse(int[] arr, int l, int r) {
        // this is used to reverse the array
        while (l < r) {
            // this is used to swap the elements
            int temp = arr[l];

            arr[l] = arr[r];
            arr[r] = temp;
            // this is used to increment the left pointer
            // this is used to decrement the right pointer
            l++;
            r--;
        }
    }

    Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }
        return prev;
    }

    Node getIntersection(Node headA, Node headB) {
        Node a = headA, b = headB;
        // this is used to traverse the linked list
        while (a != b) {
            a = (a == null) ? headB : a.next;
            // this is used to traverse the linked list
            b = (b == null) ? headA : b.next;
        }
        return a; // null if no intersection
    }

    public static void main(String[] args) {
        // Child c = new Child();
        // c.show();
        // c.show("Hello");
        // int arr[]={1,2,3,4,5,6,7};
        // int k=2;
        Test t = new Test();
        // t.rotate(arr, k);
        // for(int i=0; i<arr.length; i++) {
        // System.out.print(arr[i] + " ");
        // }

        // int i=1;
        // Node head = new Node(i);
        // Node temp = head;
        // while (i<5) {
        // head.next = new Node(i+1);
        // head = head.next;
        // i++;
        // }

        // Node reversed = t.reverse(temp);
        // while(reversed != null) {
        // System.out.print(reversed.val + " ");
        // reversed = reversed.next;
        // }

        // // Common part: 7 → 8 → 9
        // Node common = new Node(7);
        // common.next = new Node(8);
        // common.next.next = new Node(9);

        // // List A: 1 → 2 → 3 → 7 → 8 → 9
        // Node headA = new Node(1);
        // headA.next = new Node(2);
        // headA.next.next = new Node(3);
        // // headA.next.next.next = common;

        // // List B: 4 → 5 → 7 → 8 → 9
        // Node headB = new Node(4);
        // headB.next = new Node(5);
        // // headB.next.next = common;

        // Node intersection = t.getIntersection(headA, headB);

        // if (intersection != null) {
        //     System.out.println("Intersection at node with value: " + intersection.val);
        // } else {
        //     System.out.println("No intersection found.");
        // }


        String s = "Hello World";
        StringBuilder sb = new StringBuilder(s);
        Arrays.stream(s.split("")).map(c -> Character.toUpperCase(c)).collect(Collectors.toList());
        Character.toUpperCase(sb.charAt(0));
        System.out.println(sb);
    }
}