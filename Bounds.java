    static class Bounds
    {
        long [] a ;
        int n ;
        Bounds(long [] a)
        {
            this.a = a ;
            this.n = a.length ;
        }
        int [] getLeftLess()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = 0; i <n ; i++)
            {
                while(!s.isEmpty()&&a[s.peek()]>=a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = -1 ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
        int [] getRightLess()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = n-1; i >=0 ; i--)
            {
                while(!s.isEmpty()&&a[s.peek()]>=a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = n ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
        int [] getLeftLessEqual()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = 0; i <n ; i++)
            {
                while(!s.isEmpty()&&a[s.peek()]>a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = -1 ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
        int [] getRightLessEqual()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = n-1; i >=0 ; i--)
            {
                while(!s.isEmpty()&&a[s.peek()]>a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = n ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
        int [] getLeftGreater()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = 0; i <n ; i++)
            {
                while(!s.isEmpty()&&a[s.peek()]<=a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = -1 ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
        int [] getRightGreater()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = n-1; i >=0 ; i--)
            {
                while(!s.isEmpty()&&a[s.peek()]<=a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = n ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
        int [] getLeftGreaterEqual()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = 0; i <n ; i++)
            {
                while(!s.isEmpty()&&a[s.peek()]<a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = -1 ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
        int [] getRightGreaterEqual()
        {
            int [] ans = new int[n] ;
            Stack<Integer> s = new Stack<>() ;
            for (int i = n-1; i >=0 ; i--)
            {
                while(!s.isEmpty()&&a[s.peek()]<a[i])
                {
                    s.pop() ;
                }
                if(s.isEmpty())
                {
                    ans[i] = n ;
                }
                else
                {
                    ans[i] = s.peek() ;
                }
                s.push(i) ;
            }
            return ans ;
        }
    }