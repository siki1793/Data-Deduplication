package com.iiitb.ddf;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.SWAP;

public class Matcher {
	
	
	List<DynamicColumns> matchToTable(DynamicColumns selectedRow,List<DynamicColumns> dy,String selectCriteria[],String colName[])
	{
		List<DynamicColumns> temp=new ArrayList<DynamicColumns>();
		try{
		
		
		
		
		//for(int i=0;i<selectCriteria.length;i++)
        	//System.out.println("select criteria inside matcher bean"+selectCriteria[0]);
//		System.out.println("select "+selectedRow.getColumn3Value());
		///compare function for matching
		
		
		for(int i=0;i<dy.size();i++)
		{
			Boolean r=true;
			System.out.println("inside niggn"+selectCriteria.length);
			for(int j=0;j<selectCriteria.length;j++)
			{
				if(selectCriteria[j].equals(colName[0]))
				{
					r=r&&nameMatch(selectedRow.getColumn1Value(), dy.get(i).getColumn1Value());
				}
				if(selectCriteria[j].equals(colName[1]))
				{
					r=r&&nameMatch(selectedRow.getColumn2Value(), dy.get(i).getColumn2Value());
				}
				if(selectCriteria[j].equals(colName[2]))
				{
					r=r&&nameMatch(selectedRow.getColumn3Value(), dy.get(i).getColumn3Value());
				}
				if(selectCriteria[j].equals(colName[3]))
				{
					r=r&&nameMatch(selectedRow.getColumn4Value(), dy.get(i).getColumn4Value());
				}
				if(selectCriteria[j].equals(colName[4]))
				{
					r=r&&nameMatch(selectedRow.getColumn5Value(), dy.get(i).getColumn5Value());
				}
				if(selectCriteria[j].equals(colName[5]))
				{
					r=r&&nameMatch(selectedRow.getColumn6Value(), dy.get(i).getColumn6Value());
				}
				if(selectCriteria[j].equals(colName[6]))
				{
					r=r&&nameMatch(selectedRow.getColumn7Value(), dy.get(i).getColumn7Value());
				}
				if(selectCriteria[j].equals(colName[7]))
				{
					r=r&&nameMatch(selectedRow.getColumn8Value(), dy.get(i).getColumn8Value());
				}
				if(selectCriteria[j].equals(colName[8]))
				{
					r=r&&nameMatch(selectedRow.getColumn9Value(), dy.get(i).getColumn9Value());
				}
				if(selectCriteria[j].equals(colName[9]))
				{
					r=r&&nameMatch(selectedRow.getColumn10Value(), dy.get(i).getColumn10Value());
				}
			
			
				
			}
			if(r&&!selectCriteria[0].equals(null))
			{
				//System.out.println("select "+dy.get(i).getColumn3Value());
				System.out.println(r);
				temp.add(dy.get(i));
			}
//			System.out.println("bla bla nigga nigga"+i);
			
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in connection() -->" + e.getMessage());
		}
		
		return temp;
		
	}
	
	public boolean nameMatch(String first,String second)
	{
		boolean result = false;
		
		if(levenshteinDistanceMatch(first, second, 0.5))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}
	
	public boolean levenshteinDistanceMatch(String first,String second,double tolerance)
	{
		boolean result = false;
		
		double score = matchingScore(first, second);
		
		if(score <= tolerance)
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}
	public double match(int i,String one[],String two[],int maskOne,int maskTwo,int lenOne,int lenTwo)
	{
		if(maskOne==(1<<lenOne)-1)
		{
			return 0.0;
		}
		double mn=2000.00;
		for(int k=i;k<lenOne;k++)
		{
			if((maskOne&(1<<k))==0)
			{
				for(int l=0;l<lenTwo;l++)
				{
					if((maskTwo&(1<<l))==0)
					{
						mn=Math.min(mn,editDistance(one[k], two[l])+match(k+1,one,two,maskOne|(1<<k),maskTwo|(1<<l),lenOne,lenTwo));
					}
				}
			}
		}
		
		return mn;
		
	}
	public double matchingScore(String first,String second)
	{
		double score = -1;
		
		String firstTokens[] = first.split(" ");
		String secondTokens[] = second.split(" ");
		
		String one[] = new String[10];
		String two[] = new String[10];
		
		for(int i=0; i<firstTokens.length; i++)
		{
			one[i] = firstTokens[i];
		}
		for(int i=firstTokens.length; i<one.length; i++)
		{
			one[i] = null;
		}
		for(int i=0; i<secondTokens.length; i++)
		{
			two[i] = secondTokens[i];
		}
		for(int i=secondTokens.length; i<two.length; i++)
		{
			two[i] = null;
		}
		
		double minDistance;
		if(firstTokens.length<secondTokens.length)
		{
			minDistance = match(0,one,two,0,0,firstTokens.length,secondTokens.length);
		}
		else
		{
			minDistance = match(0,two,one,0,0,secondTokens.length,firstTokens.length);
		}
		
		
		/*if(Math.min(firstTokens.length, secondTokens.length) == 3)
		{
			minDistance = Math.min(minDistance,editDistance(one[0],two[0]) + editDistance(one[1],two[2]) + editDistance(one[2],two[1]));
			minDistance = Math.min(minDistance,editDistance(one[0],two[0]) + editDistance(one[1],two[1]) + editDistance(one[2],two[2]));
			minDistance = Math.min(minDistance,editDistance(one[0],two[2]) + editDistance(one[1],two[1]) + editDistance(one[2],two[0]));
			minDistance = Math.min(minDistance,editDistance(one[0],two[1]) + editDistance(one[1],two[0]) + editDistance(one[2],two[2]));
		}
		else if(Math.min(firstTokens.length, secondTokens.length) == 2)
		{
			minDistance = Math.min(minDistance,editDistance(one[0],two[0]) + editDistance(one[1],two[1]) );
			minDistance = Math.min(minDistance,editDistance(one[0],two[0]) + editDistance(one[1],two[2]) );
			minDistance = Math.min(minDistance,editDistance(one[0],two[1]) + editDistance(one[1],two[0]) );
			minDistance = Math.min(minDistance,editDistance(one[0],two[1]) + editDistance(one[1],two[2]) );
			minDistance = Math.min(minDistance,editDistance(one[0],two[2]) + editDistance(one[1],two[0]) );
			minDistance = Math.min(minDistance,editDistance(one[0],two[2]) + editDistance(one[1],two[1]) );
		}
		else if(Math.min(firstTokens.length, secondTokens.length) == 1)
		{
			minDistance = Math.min(minDistance,editDistance(one[0],two[0]));
			minDistance = Math.min(minDistance,editDistance(one[0],two[1]));
			minDistance = Math.min(minDistance,editDistance(one[0],two[2]));
		}*/
		
		
		
		
		
		score = minDistance + 0.01 * (Math.abs(first.length() - second.length()));
		return score;
	}
	
	
	
	public double editDistance(String first,String second)
	{
		double distance = -1;
		
		if(first == null && second != null)
		{

			return 1;
		}
		if(second == null && first != null)
		{
			return 1;
		}
		if(first == null && second == null)
		{
			return 0;
		}
		
		int len1 = first.length();
		int len2 = second.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) 
		{
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) 
		{
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) 
		{
			char c1 = first.charAt(i);
			for (int j = 0; j < len2; j++) 
			{
				char c2 = second.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) 
				{
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} 
				else 
				{
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 		
		double difference = Math.max(first.length(), second.length());	
		distance = (dp[len1][len2] / difference);
		return distance;
	}

}
