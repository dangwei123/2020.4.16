在 R 行 C 列的矩阵上，我们从 (r0, c0) 面朝东面开始

这里，网格的西北角位于第一行第一列，网格的东南角位于最后一行最后一列。

现在，我们以顺时针按螺旋状行走，访问此网格中的每个位置。

每当我们移动到网格的边界之外时，我们会继续在网格之外行走（但稍后可能会返回到网格边界）。

最终，我们到过网格的所有 R * C 个空间。

按照访问顺序返回表示网格位置的坐标列表。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/spiral-matrix-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res=new int[R*C][2];
        boolean[][] used=new boolean[R][C];
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        int count=0;
        res[count++]=new int[]{r0,c0};
        used[r0][c0]=true;
        int step=0;
        while(count<R*C){
            step++;
            for(int i=0;i<4;i++){
                if(i==2) step=step+1;
                for(int j=0;j<step;j++){
                    r0+=dx[i];
                    c0+=dy[i];
                    if(r0>=0&&c0>=0&&r0<R&&c0<C&&!used[r0][c0]){
                        res[count++]=new int[]{r0,c0};
                        used[r0][c0]=true;
                    }
                }
            }
        }
        return res;
    }
}

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        PriorityQueue<ListNode> q=new PriorityQueue<>(lists.length,
                                    new Comparator<ListNode>(){
                            public int compare(ListNode n,ListNode m){
                                    return n.val-m.val;
                            }
                    });
        for(ListNode node:lists){
            if(node!=null)
            q.offer(node);
        }
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        while(!q.isEmpty()){
            ListNode node=q.poll();
            cur.next=node;
            cur=cur.next;
            if(node.next!=null){
                q.offer(node.next);
            }
        }
        return dummy.next;
    }
}

在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }
    private ListNode mergeSort(ListNode head){
        if(head==null||head.next==null){
            return head;
        }
        ListNode fast=head;
        ListNode slow=head;
        ListNode pre=null;
        while(fast!=null&&fast.next!=null){
            pre=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        pre.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(slow);
        return merge(left,right);
    }
    private ListNode merge(ListNode left,ListNode right){
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        while(left!=null&&right!=null){
            if(left.val<right.val){
                cur.next=left;
                left=left.next;
                cur=cur.next;
            }else{
                cur.next=right;
                cur=cur.next;
                right=right.next;
            }
        }
        if(left!=null) cur.next=left;
        if(right!=null) cur.next=right;
        return dummy.next;
    }
}




