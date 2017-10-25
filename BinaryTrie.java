static class Node
    {
        private Node zro, one;
        int cnt;
        final int level;
        public Node(int level)
        {
            this.level = level;
        }
        private boolean test(int integer, int bit)
        {
            return (integer & (1 << bit)) > 0;
        }
        void add(int x)
        {
            ++cnt;
            if (level < 0)
            {
                return;
            }
            if (test(x, level))
            {
                if (one == null)
                {
                    one = new Node(level - 1);
                }
                one.add(x);
            }
            else
            {
                if (zro == null)
                {
                    zro = new Node(level - 1);
                }
                zro.add(x);
            }
        }
        void remove(int x)
        {
            --cnt;
            if (level < 0)
            {
                return;
            }
            if (test(x, level))
            {
                if (one != null)
                {
                    one.remove(x);
                }
            }
            else
            {
                if (zro != null)
                {
                    zro.remove(x);
                }
            }
        }
        int maxXor(int x)
        {
            if (level < 0)
            {
                return 0;
            }
            if (test(x, level))
            {
                if (zro != null && zro.cnt > 0)
                {
                    return (1 << level) | zro.maxXor(x);
                }
                if (one != null && one.cnt > 0)
                {
                    return one.maxXor(x);
                }
            }
            else
            {
                if (one != null && one.cnt > 0)
                {
                    return (1 << level) | one.maxXor(x);
                }
                if (zro != null && zro.cnt > 0)
                {
                    return zro.maxXor(x);
                }
            }
            return 0;
        }
    }