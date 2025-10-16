import java.util.Scanner;
import java.util.Random;

public class _2{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		System.out.print("Enter the number of frames: ");
		int num = sc.nextInt();
		int data, id;
		
		Frame[] obj = new Frame[num];
		
		System.out.print("1. Automatic input\n2. Manual input\nEnter your choice: ");
		int choice = sc.nextInt();
		
		switch(choice){
			case 1:
				for(int i = 0; i < num; i++){
					obj[i] = new Frame();
					obj[i].enterData(random.nextInt(1000));
					obj[i].enterId(random.nextInt(num*10));
				}
				System.out.println("Frames registered successfully");
				break;
			
			case 2:
				for(int i = 0; i < num; i++){
					obj[i] = new Frame();
					System.out.print("Enter the data for frame: " + (i+1) + ": ");
					data = sc.nextInt();
					obj[i].enterData(data);
					System.out.print("Enter the id for frame: " + (i+1) + ": ");
					id = sc.nextInt();
					obj[i].enterId(id);
					System.out.println();
				}
				System.out.println("Frames registered successfully");
				break;
				
			default:
				System.out.println("Invalid Choice!!!");
		}
		
		sort sorter = new sort();
		Frame[] sortedFrames = sorter.mergeSort(obj);
		System.out.println("The frames in sorted order (ascending):");
		for(Frame f : sortedFrames){
			System.out.println("Data: " + f.data + " Id: " + f.id);
		}
		System.out.println();
//		sc.close;
	}
}

class Frame{
	int data, id;
	public void enterData(int data){
		this.data = data;
		return;
	}
	
	public void enterId(int id){
		this.id = id;
		return;
	}
}

class sort{
	public Frame[] mergeSort(Frame[] arr){
		// base case
		if(arr.length == 1)
			return arr;
			
		// general case:
		int mid = arr.length/2;
		Frame[] left = new Frame[mid];
		Frame[] right = new Frame[arr.length-mid];
		
		System.arraycopy(arr, 0, left, 0, mid);
		System.arraycopy(arr, mid, right, 0, arr.length-mid);
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		return merge(left, right);
	}
	
	private Frame[] merge(Frame[] left, Frame[] right){
		int i = 0, j = 0, k = 0;
		Frame[] merged = new Frame[left.length + right.length];
		
		while(i < left.length && j < right.length){
			if(left[i].id < right[j].id)
				merged[k++] = left[i++];
			else
				merged[k++] = right[j++];
		}
		
		while(i < left.length)	merged[k++] = left[i++];
		while(j < right.length)	merged[k++] = right[j++];
		return merged;
	}
}
