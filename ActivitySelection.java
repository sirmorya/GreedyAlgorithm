package greedyalgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Activity implements Comparable<Activity>{

	int startTime;
	int endTime;
	
	public int compareTo(Activity o) {
		if(endTime > o.endTime)
			return 1;
		else if(endTime < o.endTime)
			return -1;
		else
			return 0;
	}
	
}

public class ActivitySelection {
	
	public static void main(String[] args) {
		
		Scanner snr = new Scanner(System.in);
		int n = snr.nextInt();
		Activity newActivity;
		ArrayList<Activity> list = new ArrayList<Activity>();
		while((n--)>0){
			newActivity = new Activity();
			System.out.println("Enter Start and End Time");
			newActivity.startTime = snr.nextInt();
			newActivity.endTime = snr.nextInt();
			list.add(newActivity);
		}
		Collections.sort(list);
		System.out.println(0+": "+list.get(0).startTime+" => "+list.get(1).endTime);
		newActivity = list.get(0);
		for(int i =1; i < list.size(); i++){
			if(list.get(i).startTime > newActivity.endTime){
				System.out.println(i+": "+list.get(i).startTime+" => "+list.get(i).endTime);
				newActivity = list.get(i);
			}
		}
		
	}
	
	
}
