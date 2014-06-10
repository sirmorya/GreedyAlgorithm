package greedyalgos;

class MinHeapNode{
	
	char data;//Input Character
	int frequency;
	MinHeapNode left, right;
	public MinHeapNode(char data,int frequency) {
		this.data = data;
		this.frequency = frequency;
		this.left = this.right = null;
	}
	boolean isLeaf(){
		return (left == null && right ==null);
	}
}

class MinHeap{
	int size;
	int capacity;
	MinHeapNode[] array;
	public MinHeap(int capacity) {
		this.size = capacity;
		this.capacity = capacity;
		array = new MinHeapNode[capacity]; 
	}
	
	void swap(int i, int j){
		MinHeapNode temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	void minHeapify(int idx){
		int smallest = idx;
		int left = (2 * idx) + 1;
		int right = (2 * idx) + 1;
		if(left < size && (array[left].frequency < array[smallest].frequency))
			smallest = left;
		if(right < size && (array[right].frequency < array[smallest].frequency))
			smallest = right;
		
		if( idx != smallest){
			swap(idx, smallest);
			minHeapify(smallest);		}
	}
	
	MinHeapNode extractMin(){
		MinHeapNode temp = array[0];
		array[0] = array[size -1];
		size--;
		minHeapify(0);
		return temp;
	}
	
	
	void insertMinHeap(MinHeapNode node){
		size++;
		int i = size -1;
		while((i > 0) && node.frequency < array[(i-1)/2].frequency){
			array[i] = array[(i-1)/2];
			i = (i-1)/2;
		}
		array[i] = node;
	}
	
	void buildMinHeap(){
		for(int i = (size -1)/2; i >= 0; i--){
			minHeapify(i);
		}
	}
	
	
}


public class HuffManCoding {
	
	MinHeap createandBuildMinHeap(char data[], int frequencies[], int size){
		MinHeap minHeap = new MinHeap(size);
		MinHeapNode minHeapNode;
		for(int i = 0; i < size; i++){
			minHeap.array[i] = new MinHeapNode(data[i], frequencies[i]);
		}
		minHeap.size = size;
		minHeap.buildMinHeap();
		return minHeap;
	}

	MinHeapNode buildHuffmanTree(char[] data, int[] frequencies){
		
		MinHeapNode left, right, top;
		MinHeap minHeap = createandBuildMinHeap(data, frequencies, data.length);
		while(minHeap.size != 1){
			left = minHeap.extractMin();
			right = minHeap.extractMin();
			//Creating the internal nodes
			top = new MinHeapNode('$', left.frequency + right.frequency);
			top.left = left;
			top.right =right;
			minHeap.insertMinHeap(top);
		}
		return minHeap.extractMin();
	}
	
	void printCodes(MinHeapNode root, int arr[], int top){
		if(root.left != null){
			arr[top] = 0;
			printCodes(root.left, arr, top+1);
		}
		if(root.right != null){
			arr[top] = 1;
			printCodes(root.right, arr, top+1);
		}
		if(root.left == null && root.right == null){
			System.out.print(root.data+" : ");
			printArr(arr, top);
		}
	}
	
	void printArr(int arr[], int n){
		for(int i = 0; i < n; i++)
			System.out.print(arr[i]);
		System.out.println();
	}
	
	void huffmanCode(char[] data,int[] frequencies, int size){
		
		MinHeapNode root = buildHuffmanTree(data, frequencies);
		int[] arr = new int[100];
		int top =0;
		printCodes(root, arr, top);
		
	}
	
	public static void main(String[] args) {
		char data[] = {'a', 'b', 'c', 'd', 'e', 'f'};
	    int  freq[] = {5, 9, 12, 13, 16, 45};
	    new HuffManCoding().huffmanCode(data,freq,6);
	}
}
