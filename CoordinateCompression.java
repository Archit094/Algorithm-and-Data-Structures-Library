    static class CoordinateCompressor
    {
        TreeSet<Long> values ;
        TreeMap<Long,Integer> map;
        long [] arr ;
        int size ;
        CoordinateCompressor(ArrayList<Long> ar)
        {
            values = new TreeSet<Long>() ;
            values.addAll(ar) ;
            build();
        }
        CoordinateCompressor(long [] a)
        {
            values = new TreeSet<Long>() ;
            for(long x : a)
            {
                values.add(x);
            }
            build();
        }
        void build()
        {
            map = new TreeMap<Long,Integer>() ;
            size = 0 ;
            for(long x : values)
            {
                map.put(x,size) ;
                size++ ;
            }
            arr = new long[size] ;
            int ctr = 0 ;
            for(long x : values)
            {
                arr[ctr] = x ;
                ctr++ ;
            }
        }
        int getCompressed(long x)
        {
            return map.get(x) ;
        }
        long getOriginal(int ind)
        {
            return arr[ind] ;
        }
        int getNextEqualCompressed(long x)
        {
            try
            {
                return map.get(map.ceilingKey(x));
            }
            catch (Exception e)
            {
                return size ;
            }
        }
        int getPrevEqualCompressed(long x)
        {
            try
            {
                return map.get(map.floorKey(x));
            }
            catch (Exception e)
            {
                return -1 ;
            }
        }
    }