package AhmadSaleh;

public class SortingAlgorithms {
	
	
	/* ################## Start Helper Functions ################## */
	
	static void Swap(double Arr[] , int A , int B)
	{
		double T = Arr[A];
		Arr[A] = Arr[B];
		Arr[B] = T;
	}
	
	/* ################## End Helper Functions ################## */
	
	
	/* ################## Start Bubble Sort ################## */
	
	static void BubbleSort(double Arr[])
	{
		int Size = Arr.length;
		int Cnt = 0;
		while(true)
		{
			boolean Found = false;
			for(int i=0;i<Size - Cnt - 1;i++)
			{
				if(Arr[i] > Arr[i + 1])
				{
					Swap(Arr , i ,i + 1);
					Found|=true;
				}
			}
			
			
			if(!Found)break;
			Cnt++;
		}
	}
	
	/* ################## End Bubble Sort ################## */
	
	
	/* ################## Start Selection Sort ################## */
	
	static void SelectionSort(double Arr[])
	{
		int Size = Arr.length;
		for(int i=0;i<Size;i++)
		{
			int Min = i;
			for(int j=i + 1;j<Size;j++)if(Arr[Min] > Arr[j])Min = j;
			Swap(Arr , i , Min);
		}
	}

	/* ################## End Selection Sort ################## */
	
	
	/* ################## Start Heap Sort ################## */
	
	static void Heapify(double Arr[] , int Size , int Curr)
	{
		while(true)
		{
			int Left = Curr*2 + 1;
			int Right = Curr*2 + 2;
			int Max = Curr;
			if(Left < Size && Arr[Left] > Arr[Max])Max = Left;
			if(Right < Size && Arr[Right] > Arr[Max])Max = Right;
			
			if(Max!=Curr)
			{
				Swap(Arr , Max , Curr);
				Curr = Max;
				continue;
			}
			
			break;
		}
	}
	static void HeapSort(double Arr[])
	{
		int Size = Arr.length;
		
		for(int i=(Size - 2)/2;i>=0;i--) // Build Heap
		{
			Heapify(Arr , Size , i);
		}
		
		
		for(int i=Size-1;i>=0;i--) // Start Swap And Heapify !
		{
			Swap(Arr , i , 0);
			Heapify(Arr , i , 0);
		}
	}
	
	/* ################## End Heap Sort ################## */
	
	
	/* ################## Start Quick Sort ################## */
	
	static int Partition(double Arr[] , int L , int R)
	{
		double Pivot = Arr[L];
		int j = L - 1;
		for(int i=L;i<=R;i++)
		{
			if(Arr[i]<=Pivot)
			{
				j++;
				Swap(Arr , i , j);
			}
		}
		
		Swap(Arr , j , L);
		return j;
	}
	
	static void QuickSort(double Arr[] , int L , int R)
	{
		if(R<=L)return;
		int Mid = Partition(Arr , L , R);
		QuickSort(Arr , L , Mid - 1);
		QuickSort(Arr , Mid + 1 , R);
	}
	
	static void QuickSort(double Arr[])
	{
		QuickSort(Arr , 0 , Arr.length - 1);
	}
	
	
	/* ################## End Quick Sort ################## */
	
}


