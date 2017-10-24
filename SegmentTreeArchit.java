    static class SegmentTree
    {
        int n ;
        Node [] tree;
        SegmentTree(int n)
        {
            this.n = n ;
            tree = new Node[4*n] ;
            build(0,n-1,0) ;
        }
        void build(int l,int r,int ind)
        {
            if(l==r)
            {
                tree[ind] = new Node() ;
            }
            else
            {
                int mid = (l + r) >> 1;
                build(l,mid,2*ind+1);
                build(mid+1,r,2*ind+2);
                tree[ind] = new Node() ;
                buildCombine(tree[ind],tree[ind*2+1],tree[ind*2+2]);
            }
        }
        private void push(int l,int r,int ind)
        {
            if(tree[ind].lazy)
            {
                tree[ind].val = (r-l+1)-tree[ind].val ;
                if(l!=r)
                {
                    tree[2*ind+1].lazy ^= true ;
                    tree[2*ind+2].lazy ^= true ;
                }
                tree[ind].lazy = false ;
            }
        }
        private void buildCombine(Node l,Node left,Node right)
        {
            l.val = left.val+right.val ;
        }
        private int queryCombine(int val1,int val2)
        {
            return val1+val2  ;
        }
        private int query(int qL, int qR, int l, int r, int idx)
        {
            push(l, r, idx);
            if(l > qR || r < qL)
                return 0 ;
            if(qL <= l && r <= qR)
                return tree[idx].val;
            int ans = 0 ;
            int mid = (l + r) >> 1;
            ans =  queryCombine(ans,query(qL, qR, l, mid, 2 * idx + 1)) ;
            ans = queryCombine(ans,query(qL, qR, mid + 1, r, 2 * idx + 2));
            return ans ;
        }
        void update(int l,int r)
        {
            update(l,r,0,n-1,0) ;
        }
        int query(int l,int r)
        {
            return query(l,r,0,n-1,0) ;
        }
        void update(int qL, int qR, int l, int r, int ind)
        {
            push(l, r, ind);
            if(qL > r || l > qR)
                return;
            if(qL <= l && r <= qR)
            {
                tree[ind].lazy = true;
                push(l, r, ind);
                return;
            }
            int mid = (l + r) >> 1;
            update(qL, qR, l, mid, 2 * ind + 1);
            update(qL, qR, mid + 1, r, 2 * ind + 2);
            buildCombine(tree[ind],tree[2*ind + 1], tree[2*ind + 2]);
        }
        class Node
        {
            int val ;
            boolean lazy ;
            Node()
            {
                val = 0 ;
                lazy = false ;
            }
        }
    }
