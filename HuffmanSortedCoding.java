package greedyalgos;

import java.util.ArrayList;




class Queue{
	int front;
	int rear;
	int size;
	MinHeapNode[] array;
	
	public Queue(int size){
		this.front = this.rear = -1;
		this.size = size;
		this.array = new MinHeapNode[size];
	}
	
	boolean isEmpty(){
		return (this.front == -1);
	}
	
	boolean isFull(){
		return (this.rear == (this.size -1));
	}
	
	boolean hasOnlyOneItem(){
		return (this.front == this.rear);
	}
	
	void enQue(MinHeapNode root){
		if(isFull())
			return;
		this.array[++this.rear] = root;
		
		if(isEmpty())
			this.front++;
	}
	
	MinHeapNode deQue(){
		if(isEmpty())
			return null;
		MinHeapNode temp = this.array[front];
		if(hasOnlyOneItem())
			this.front = this.rear = -1;
		else
			this.front++;
		return temp;
	}
	MinHeapNode getFront(){
		if(isEmpty())
			return null;
		else
			return array[front];
	}
	
}

public class HuffmanSortedCoding {

	MinHeapNode findMin(Queue first, Queue second){
		if(first.isEmpty())
			return second.deQue();
		if(second.isEmpty())
			return first.deQue();
		if(first.getFront().frequency < second.getFront().frequency)
			return first.deQue();
		return second.deQue();
	}
	
	MinHeapNode buildHuffmanTree(char data[], int frequencies[]){
		
		MinHeapNode left, right, top;
		int size = data.length;
		Queue firstQueue = new Queue(size);
		Queue secondQueue = new Queue(size);
		
		for(int i = 0; i < size; i++)
			firstQueue.enQue(new MinHeapNode(data[i], frequencies[i]));
		
		while(!(firstQueue.isEmpty() && secondQueue.size == 1)){
			left = findMin(firstQueue, secondQueue);
			right = findMin(firstQueue, secondQueue);
			top = new MinHeapNode('$', left.frequency + right.frequency);
			top.left = left;
			top.right = right;
			secondQueue.enQue(top);
		}
		return secondQueue.deQue();
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
