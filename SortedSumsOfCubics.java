import java.lang.*;

import java.util.*;

public class SortedSumsOfCubics {

	private int m_nNum;
	private List<Integer> m_lstHeapNum;
	
	public SortedSumsOfCubics(int nNum)
	{
		m_nNum = nNum;
		m_lstHeapNum = new ArrayList<Integer>(0);
	}
	
	public int getCub(int nNum)
	{
		return nNum * nNum * nNum;
	}
	
	//we need to build a min heap to solve this problem
	public void insertHeap(int nNum)
	{
		if (m_lstHeapNum.size() < m_nNum)
		{
			m_lstHeapNum.add(nNum);
			if (m_lstHeapNum.size() == m_nNum)
			{
				for (int nIndex = m_lstHeapNum.size() / 2; nIndex >= 0; nIndex--)
				{
					sortHeap(nIndex);
				}
			}
		}
		else
		{
			System.out.println(m_lstHeapNum.get(0));
			m_lstHeapNum.set(0, nNum);
			sortHeap(0);
		}
	}
	
	//print out the last m_nNum elements in the heap;
	public void printHeap()
	{
		while(0 != m_lstHeapNum.size())
		{
			System.out.println(m_lstHeapNum.get(0));
			m_lstHeapNum.set(0, m_lstHeapNum.get(m_lstHeapNum.size() - 1));
			m_lstHeapNum.remove(m_lstHeapNum.size() - 1);
			sortHeap(0);
		}
	}
	
	//sort the heap according to the elements
	public void sortHeap(int nIndex)
	{
		if (2 * nIndex >= m_lstHeapNum.size())
			return;
		int nTmp;
		int nLeftValue = (m_lstHeapNum.size() > 2 * nIndex + 1 ? m_lstHeapNum.get(2 * nIndex + 1) : Integer.MAX_VALUE);
		int nRightValue = (m_lstHeapNum.size() > 2 * nIndex + 2 ? m_lstHeapNum.get(2 * nIndex + 2) : Integer.MAX_VALUE);
		if (nLeftValue <= nRightValue && m_lstHeapNum.get(nIndex) > nLeftValue)
		{
			nTmp = nLeftValue;
			m_lstHeapNum.set(2 * nIndex + 1, m_lstHeapNum.get(nIndex));
			m_lstHeapNum.set(nIndex, nTmp);
			sortHeap(2 * nIndex + 1);
		}
		else if (nLeftValue > nRightValue && m_lstHeapNum.get(nIndex) > nRightValue)
		{
			nTmp = nRightValue;
			m_lstHeapNum.set(2 * nIndex + 2, m_lstHeapNum.get(nIndex));
			m_lstHeapNum.set(nIndex, nTmp);
			sortHeap(2 * nIndex + 2);
		}
	}
	
	//print the result;
	public void getCubAddResult()
	{
		for (int nNumA = 0; nNumA <= m_nNum; nNumA++)
		{
			for (int nNumB = 0; nNumB <= m_nNum; nNumB++)
			{
				if(nNumA>=nNumB){
					insertHeap((getCub(nNumA) + getCub(nNumB)));
				}
			}
		}
		printHeap();
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
	
		System.out.print("Input the number: ");
		if (scan.hasNext())
		{
			int nNum = scan.nextInt();
			scan.close();
			SortedSumsOfCubics add = new SortedSumsOfCubics(nNum);
			add.getCubAddResult();
		}
	}
}
