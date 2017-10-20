static class Query implements Comparable<Query>{
	int l,r,block,id;
	static int nsqrt;
	
	public Query(int l,int r,int id){
		this.l = l;
		this.r = r;
		block = l / nsqrt ;
		this.id = id;
	}
	
	public int compareTo(Query b){
		if(block != b.block)
			return block - b.block;
		return r - b.r;
	}
	
	public String toString(){
		return id + " " + l + " " + r + " " + block;
	}
}
