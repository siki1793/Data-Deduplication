package com.iiitb.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.iiitb.ddf.DynamicColumns;
import com.iiitb.ddf.Student;
import com.sun.istack.internal.logging.Logger;

public class DataTableViewDAO {
	Database db = new Database();
	Connection con = null;
	PreparedStatement ps = null;
	int count;
	int count1;
	String columnName[];
	String columnName1[];
	private static Logger log;
	
	public ArrayList<String> populateTables(){
		ArrayList<String> tables=new ArrayList<String>();
		
		try{
			con = db.getConnection();
			ResultSet rs = null;
		    DatabaseMetaData meta = con.getMetaData();
		    rs = meta.getTables(null, null, null, new String[]{"TABLE"});

		    while (rs.next()) {
		      String tableName = rs.getString("TABLE_NAME");
		      System.out.println("tableName=" + tableName);
		      tables.add(tableName);
		    }

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		for(String i:tables)
		{
			System.out.println(i);
		}
		
		db.close(con);
		return tables;
	}
	
	public ArrayList<Student> populateColunms(String table){
		ArrayList<Student> tableList = new  ArrayList<Student>();
		try {
			con = db.getConnection();
			ps = con.prepareStatement("select * from "+table);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			count = metaData.getColumnCount(); //number of column
			System.out.println("number od columns "+count);
			columnName = new String[count];
			for (int i = 0; i < count; i++)
			{
			   columnName[i] = metaData.getColumnLabel(i+1);
			   System.out.println(i+columnName[i]+" "+metaData.getColumnLabel(i+1)+"column type "+metaData.getColumnType(count));
			}
			while(rs.next())
			{
				tableList.add(new Student(rs.getInt(1),rs.getString(2) ,rs.getString(3)));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in connection() -->" + e.getMessage());
		}
		
		db.close(con);
		return tableList;
		
	}
	
	public ArrayList<DynamicColumns> populateDynamicColunms(String table){
		ArrayList<DynamicColumns> tableList = new  ArrayList<DynamicColumns>();
		try {
			con = db.getConnection();
			ps = con.prepareStatement("select * from "+table);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			count = metaData.getColumnCount(); //number of column
			System.out.println("number od columns "+count);
			columnName = new String[count];
			for (int i = 0; i < count; i++)
			{
			   columnName[i] = metaData.getColumnLabel(i+1);
			   System.out.println(i+columnName[i]+" "+metaData.getColumnLabel(i+1)+"column type "+metaData.getColumnType(count));
			}
			
			while(rs.next())
			{
				DynamicColumns dyn=new DynamicColumns();
				for(int i = 0; i < count; i++)
				{
		//			System.out.println("List of size count============"+columnName[i]);		
					int type = metaData.getColumnType(i+1);
					if(i==0)
					{
						if(type == Types.INTEGER)
						{
							dyn.setColumn1Value(Integer.toString(rs.getInt(i+1)));
			//				System.out.println("hereea"+dyn.getColumn1Value());
						}
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn1Value(rs.getString(i+1));
						}
					}
					if(i==1)
					{
						if(type == Types.INTEGER)
							dyn.setColumn2Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn2Value(rs.getString(i+1));
						}
					}
					if(i==2)
					{
						if(type == Types.INTEGER)
							dyn.setColumn3Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn3Value(rs.getString(i+1));
				//			System.out.println("hereea"+dyn.getColumn3Value());
						}
					}
					if(i==3)
					{
						if(type == Types.INTEGER)
							dyn.setColumn4Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn4Value(rs.getString(i+1));
						}
					}
					if(i==4)
					{
						if(type == Types.INTEGER)
							dyn.setColumn5Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn5Value(rs.getString(i+1));
						}
					}
					if(i==5)
					{
						if(type == Types.INTEGER)
							dyn.setColumn6Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn6Value(rs.getString(i+1));
						}
					}
					if(i==6)
					{
						if(type == Types.INTEGER)
							dyn.setColumn7Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn7Value(rs.getString(i+1));
						}
					}
					if(i==7)
					{
						if(type == Types.INTEGER)
							dyn.setColumn8Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn8Value(rs.getString(i+1));
						}
					}
					if(i==8)
					{
						if(type == Types.INTEGER)
							dyn.setColumn9Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn9Value(rs.getString(i+1));
						}
					}
					if(i==9)
					{
						if(type == Types.INTEGER)
							dyn.setColumn10Value(Integer.toString(rs.getInt(i+1)));
						else
						if(type == Types.VARCHAR)
						{
							dyn.setColumn10Value(rs.getString(i+1));
						}
					}
				}
				tableList.add(dyn);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in connection() -->" + e.getMessage());
		}
		
		db.close(con);
		return tableList;
		
	}
	
	public List<DynamicColumns> deleteRowFromTable(String table,List<DynamicColumns> toDeleteRows,String fromColumn,List<DynamicColumns> dataTable)
	{
		try {
			System.out.println("deleted from here"+fromColumn);
			for(int i=0;i<toDeleteRows.size();i++)
			{
			con = db.getConnection();
			System.out.println("deleted from here"+fromColumn);
			ps = con.prepareStatement("DELETE FROM "+table+" WHERE "+fromColumn+" = "+toDeleteRows.get(i).getColumn1Value());
			for(int j=0;j<dataTable.size();j++)
			{
				if(dataTable.get(j).getColumn1Value().equals(toDeleteRows.get(i).getColumn1Value()))
				{
					dataTable.remove(j);
				}
			}
			int rs = ps.executeUpdate();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataTable;
	}
	
	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public String getColumnName(int index) {
		return columnName[index];
	}



	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public String getColumnName1(int index) {
		return columnName1[index];
	}

	public void setColumnName1(String[] columnName1) {
		this.columnName1 = columnName1;
	}

}
