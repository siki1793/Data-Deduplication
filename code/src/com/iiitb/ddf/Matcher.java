package com.iiitb.ddf;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.SWAP;

public class Matcher {
	
	
	List<Double> tolList = new ArrayList<Double>();
	
	List<DynamicColumns> matchToTable(DynamicColumns selectedRow,List<DynamicColumns> dy,String selectCriteria[],String colName[],String algorithm,String types[],double toleranceValue)
	{
		List<DynamicColumns> temp=new ArrayList<DynamicColumns>();
		List<DynamicColumns> tempdata = new ArrayList<DynamicColumns>();
		try{
		
		
		
		

			System.out.println("i am inside the matcher method"+toleranceValue);
			
			for(String a: types)
			{
				System.out.println("type "+a);
			}
		
			//compare function for matching		
		for(int i=0;i<dy.size();i++)
		{
			Boolean r=true;
			for(int j=0;j<selectCriteria.length;j++)
			{
				if(selectCriteria[j].equals(colName[0]))
				{
					r=r&&nameMatch(selectedRow.getColumn1Value(), dy.get(i).getColumn1Value(),types[0],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[1]))
				{
					r=r&&nameMatch(selectedRow.getColumn2Value(), dy.get(i).getColumn2Value(),types[1],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[2]))
				{
					r=r&&nameMatch(selectedRow.getColumn3Value(), dy.get(i).getColumn3Value(),types[2],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[3]))
				{
					r=r&&nameMatch(selectedRow.getColumn4Value(), dy.get(i).getColumn4Value(),types[3],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[4]))
				{
					r=r&&nameMatch(selectedRow.getColumn5Value(), dy.get(i).getColumn5Value(),types[4],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[5]))
				{
					r=r&&nameMatch(selectedRow.getColumn6Value(), dy.get(i).getColumn6Value(),types[5],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[6]))
				{
					r=r&&nameMatch(selectedRow.getColumn7Value(), dy.get(i).getColumn7Value(),types[6],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[7]))
				{
					r=r&&nameMatch(selectedRow.getColumn8Value(), dy.get(i).getColumn8Value(),types[7],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[8]))
				{
					r=r&&nameMatch(selectedRow.getColumn9Value(), dy.get(i).getColumn9Value(),types[8],toleranceValue,algorithm);
				}
				if(selectCriteria[j].equals(colName[9]))
				{
					r=r&&nameMatch(selectedRow.getColumn10Value(), dy.get(i).getColumn10Value(),types[9],toleranceValue,algorithm);
				}
			
			
				
			}
			if(r&&!selectCriteria[0].equals(null))
			{
				System.out.println(r);
				temp.add(dy.get(i));
			}
			
		}
		
		System.out.println("size of temp data "+temp.size());
		System.out.println("size of tolerance Value data "+tolList.size());
		
		//adding tolerance value 
		for(int i = 0; i<temp.size();i++)
		{
			DynamicColumns te = temp.get(i);
			DynamicColumns hugeTemps =new DynamicColumns();
			if(te.getColumn1Value()!= null)
			{
				hugeTemps.setColumn1Value(te.getColumn1Value());
			}
			if(te.getColumn2Value()!= null)
			{
				hugeTemps.setColumn2Value(te.getColumn2Value());
			}
			if(te.getColumn3Value()!= null)
			{
				hugeTemps.setColumn3Value(te.getColumn3Value());
			}
			if(te.getColumn4Value()!= null)
			{
				hugeTemps.setColumn4Value(te.getColumn4Value());
			}
			if(te.getColumn5Value()!= null)
			{
				hugeTemps.setColumn5Value(te.getColumn5Value());
			}
			if(te.getColumn6Value()!= null)
			{
				hugeTemps.setColumn6Value(te.getColumn6Value());
			}
			if(te.getColumn7Value()!= null)
			{
				hugeTemps.setColumn7Value(te.getColumn7Value());
			}
			if(te.getColumn8Value()!= null)
			{
				hugeTemps.setColumn8Value(te.getColumn8Value());
			}
			if(te.getColumn9Value()!= null)
			{
				hugeTemps.setColumn9Value(te.getColumn9Value());
			}
			if(te.getColumn1Value()!= null)
			{
				hugeTemps.setColumn1Value(te.getColumn1Value());
			}
			if(algorithm.equals("Edit Distance") )
			{
				if(tolList.get(i)!=null)
				{
					hugeTemps.setTolerance(tolList.get(i));
				}
			}
			else
			{
				hugeTemps.setTolerance(null);
			}
			
			tempdata.add(hugeTemps);
		}
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in connection() -->" + e.getMessage());
		}
		
		return tempdata;
		
	}
	
	public boolean nameMatch(String first,String second,String type,double toleranceValue,String algorithm)
	{
		boolean result = false;
		if(type.equals("int"))
		{
			int firstInt = Integer.parseInt(first);
			int secondInt = Integer.parseInt(second);
			if(Math.abs(firstInt-secondInt)<=toleranceValue)
			{
				result = true;
			}
			tolList.add(null);

		}
		else
		{
			if(filterAndMatchNumbersFromString(first, second))
			{
				System.out.println("algo name"+algorithm);
				if(algorithm.equals("Naive Matching"))
				{
					if(first.equals(second))
						result = true;
				}
				else if(algorithm.equals("Edit Distance"))
				{
					if(!isCommaSeparated(first,second))
					{
						if(levenshteinDistanceMatch(first, second, toleranceValue))
						{
							result = true;
						}
					}
					else
					{
						String firstToken[] = first.split(",");
						String secondToken[] = second.split(",");
						if(firstToken.length==secondToken.length)
						{
							result=true;
							for(int i=0;i<firstToken.length;i++)
							{
								result&=levenshteinDistanceMatch(firstToken[i], secondToken[i], toleranceValue);
							}
						}
					}
				}
				else if(algorithm.equals("SoundEX"))
				{
					first.replace(",", " ");
					second.replace(",", " ");
					String firstToken[] = first.split(" ");
					String secondToken[] = second.split(" ");
					if(firstToken.length==secondToken.length)
					{
						result=true;
						for(int i=0;i<firstToken.length;i++)
						{
							result&=soundEX(firstToken[i], secondToken[i]);
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public boolean filterAndMatchNumbersFromString(String str1, String str2){
		
		//extract numbers from first string
		String numberOnly1 = str1.replaceAll("[^0-9]", " ");
		
		//store it in array list
		ArrayList<Integer> al1 = new ArrayList<Integer>();
		
		for(String retval : numberOnly1.split("\\s+")){	
			
			//System.out.print(retval+"-");
			retval.trim();
			if(retval.length() != 0){ 
			
				//System.out.print(retval+"-"); 
			    al1.add(Integer.parseInt(retval)); 
			}
		
		}
		
		//extract numbers from second string
		String numberOnly2 = str2.replaceAll("[^0-9]", " ");
		
		//store it in array list
		ArrayList<Integer> al2 = new ArrayList<Integer>();

		for(String retval : numberOnly2.split("\\s+")){	
			
			retval.trim();
			if(retval.length() != 0){ 
				al2.add(Integer.parseInt(retval));
			}
		}
		
		
		//check if elements of first array list match with all elements of second array list
		for(Integer element : al1){
			if(al2.contains(element)){ al2.remove(element); }
		}
		
		//if all elements of second list match it will be empty
		if(al2.isEmpty()) { System.out.println("match");  return true; }	//match 
		else { System.out.println("not a match");	return false; 	} //not a match
	
 	}
	
	//soundEX algorithm
	public boolean soundEX(String first,String second)
	{
		if(soundex(first).equals(soundex(second)))
		{
			return true;
		}
		return false;
	}
	
	public String soundex(String s)
	{
		 char[] x = s.toUpperCase().toCharArray();
	        char firstLetter = x[0];

	        // convert letters to numeric code
	        for (int i = 0; i < x.length; i++) {
	            switch (x[i]) {

	                case 'B':
	                case 'F':
	                case 'P':
	                case 'V':
	                    x[i] = '1';
	                    break;

	                case 'C':
	                case 'G':
	                case 'J':
	                case 'K':
	                case 'Q':
	                case 'S':
	                case 'X':
	                case 'Z':
	                    x[i] = '2';
	                    break;

	                case 'D':
	                case 'T':
	                    x[i] = '3';
	                    break;

	                case 'L':
	                    x[i] = '4';
	                    break;

	                case 'M':
	                case 'N':
	                    x[i] = '5';
	                    break;

	                case 'R':
	                    x[i] = '6';
	                    break;

	                default:
	                    x[i] = '0';
	                    break;
	            }
	        }

	        // remove duplicates
	        String output = "" + firstLetter;
	        for (int i = 1; i < x.length; i++)
	            if (x[i] != x[i-1] && x[i] != '0')
	                output += x[i];

	        // pad with 0's or truncate
	        output = output + "0000";
	        return output.substring(0, 4);
	}
	
	//checkes for commaSeparated
	public boolean isCommaSeparated(String first,String second)
	{
		String firstToken[] = first.split(",");
		String secondToken[] = second.split(",");
		if(firstToken.length>1||secondToken.length>1)
		{
			return true;
		}
		return false;
	}
	
	//improved levenshtein Distance algorithm
	public boolean levenshteinDistanceMatch(String first,String second,double tolerance)
	{
		boolean result = false;
		
		double score = matchingScore(first, second);
		
		if(score <= tolerance)
		{
			score = Math.floor(score*100)/100;
			tolList.add(score);
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
