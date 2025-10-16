import java.util.*;

public class frame1{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the message to be transmitted: ");
		String message = sc.nextLine();
//		 new Frame[message.length()];
		sort sorter = new sort();
		Frame[] obj = sorter.frameDivide(message);
		System.out.println("Frames are divided as follows:");
		for(int i = 0; i < obj.length; i++){
			System.out.println("Data; " + obj[i].data + " ID: " + obj[i].id);
		}
		
		obj = sorter.mergeSort(obj);
		System.out.println("The sorted frames: ");
		for(int i = 0; i < obj.length; i++){
			System.out.println("Data: " + obj[i].data + " ID: " + obj[i].id);
		}
	}
}

class Frame{
	String data;
	int id;
	public void enterData(String data){
		this.data = data;
		return;
	}
	
	public void enterId(int id){
		this.id = id;
		return;
	}
}

class sort{
	public Frame[] frameDivide(String in){
		Random random = new Random();
		String temp = "";
		Frame[] obj = new Frame[in.length()/3];
		for(int i = 0; i < in.length()/3; i++){
			obj[i] = new Frame();
			temp = in.substring(i*3, i*3+3);
			obj[i].enterData(temp);
			temp = "";
			obj[i].enterId(random.nextInt(in.length()*10));
		}
		return obj;
	}
	
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
