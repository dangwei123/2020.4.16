给出一个区间的集合，请合并所有重叠的区间。
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                return a[0]-b[0];
            }
        });
        List<int[]> res=new LinkedList<>();
        int i=0;
        while(i<intervals.length){
            int left=intervals[i][0];
            int right=intervals[i][1];
            while(i+1<intervals.length&&right>=intervals[i+1][0]){
                right=Math.max(right,intervals[i+1][1]);
                i++;
            }
            res.add(new int[]{left,right});
            i++;
        }
        return res.toArray(new int[res.size()][2]);
    }
}

你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
class Solution {
    public int superPow(int a, int[] b) {
        int res=1;
        for(int i=0;i<b.length;i++){
            res=fastPow(res,10)*fastPow(a,b[i])%1337;
        }
        return res;
    }
    private int fastPow(int a,int b){
        a%=1337;
        int res=1;
        while(b!=0){
            if(b%2!=0){
                res=res*a%1337;
            }
            a=a*a%1337;
            b/=2;
        }
        return res;
    }
    private int pow(int a,int b){
        a%=1337;
        int res=1;
        while(b--!=0){
            res*=a;
            res%=1337;
        }
        return res;
    }
}

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/jump-game-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int jump(int[] nums) {
        int n=nums.length;
        int max=0;
        int end=0;
        int res=0;
        for(int i=0;i<n-1;i++){
            max=Math.max(max,nums[i]+i);
            if(end==i){
                res++;
                end=max;
            }
        }
        return res;
    }
}

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int n=getLen(head);
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode pre=dummy;
        ListNode cur=dummy.next;
        for(int i=0;i<n/k;i++){
            for(int j=0;j<k-1;j++){
                ListNode next=cur.next;
                if(next!=null){
                    cur.next=next.next;
                next.next=pre.next;
                pre.next=next;
                }
                
                
            }
            pre=cur;
            cur=pre.next;
        }
        return dummy.next;
    }
    private int getLen(ListNode head){
        int len=0;
        ListNode cur=head;
        while(cur!=null){
            cur=cur.next;
            len++;
        }
        return len;
    }
}