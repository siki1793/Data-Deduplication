package com.iiitb.ddf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.DynamicColumn;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.iiitb.dao.*;

@ManagedBean(name = "dataTableView")
@ViewScoped
public class DataTableView implements Serializable {

	DataTableViewDAO dataDAO=new DataTableViewDAO();
	Matcher match=new Matcher();
	private DynamicColumns selectedData=new DynamicColumns();
	
	private static final long serialVersionUID = 1L;

	private String column1;
	private String column2;
	private String column3;
	private String column4;
	private String column5;
	private String column6;
	private String column7;
	private String column8;
	private String column9;
	private String column10;

	private int doo;
	int i = 0;
	int n[]=new int[dataDAO.getCount()];
	int col;

	private List<String> selected;
	private List<String> algorithmList;
	private List<String> Types;
	private List<String> selectList=new ArrayList<String>();
	private double toleranceValue;

	private List<DynamicColumns> dy;
	private List<DynamicColumns> selectedRows;
	private List<DynamicColumns> deleteRows;
	private List<DataTableColumn> dataTableColumns = new ArrayList<DataTableColumn>();
	private List<DataTableColumn> dataTableColumns1 = new ArrayList<DataTableColumn>();
	private DynamicColumns selectedRow;
	
	private String selectCriteria[]=new String[10];
	private String tableType[]=new String[10];
	private String select;
	private String selectedAlgorithm;
	private boolean deleteStatus;
	private boolean tolField =true ;
	String columnNames[]=new String[10];
	String na[]=new String[dataDAO.getCount()];


	Boolean visable1;
	Boolean visable2;
	Boolean visable3;
	Boolean visable4;
	Boolean visable5;
	Boolean visable6;
	Boolean visable7;
	Boolean visable8;
	Boolean visable9;
	Boolean visable10;
	

        public void getOnloadValueList() {
        	
        	try{
        		
        		algorithmList = new ArrayList<String>();
        		
        		algorithmList.add("Naive Matching");
        		algorithmList.add("Edit Distance");
        		algorithmList.add("SoundEX");
        		
        		genereateTable(null);
        		
        		
			}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}

        }
        
        public void processingAlgo()
        {
        	System.out.println("here in side the processing algo"+selectedAlgorithm);
        	if(selectedAlgorithm.equals("Edit Distance"))
        	{
        		tolField = false;
        	}
        	else
        	{
        		
        		tolField = true;
        	}
        }
        
        
        public void tableSelectListener(){
        	
        	
        	genereateTable(select);
        	
        	System.out.println("selected algorithm :: "+selectedAlgorithm);
        	
			deleteStatus=false;
			
        }
        
        
        public void seletedRowBean()
        {
        	//for(String s:selectCriteria)
        	for(int i=0;i<selectCriteria.length;i++)
        	System.out.println("select criteria"+selectCriteria[i]);
        }
        
        public void processingText()
        {
        	System.out.println("when the tolerance Value is entered "+toleranceValue);
        }
        
        public void onRowSelect(SelectEvent even) {
            System.out.println("Selected row "+selectedRow.getColumn1Value()+" jkdaslj "+selectedRow.getColumn2Value());
            
            System.out.println("toleranceValue"+toleranceValue);
            
            	selectedRows=match.matchToTable(selectedRow,dy,selectCriteria,columnNames,selectedAlgorithm,tableType,toleranceValue);
            	
            	System.out.println("return back type "+selectedRows.size());
            	seletedRowBean();
            	System.out.println(selectCriteria.length);
            
            
           
            setDoo(selectedRows.size());
            
        }
     
        public void deleteRow()
        {
        	for(int i=0;i<deleteRows.size();i++)
              System.out.println("delete row Selected  "+deleteRows.get(i).getColumn1Value()+" bla bla "+deleteRows.get(i).getColumn2Value());
        	System.out.println("table selected hah "+select);
        	deleteStatus=true;
        	dy=dataDAO.deleteRowFromTable(select, deleteRows,getColumnNames(0),dy);
        	selectedRows=dataDAO.deleteRowFromTable(select, deleteRows,getColumnNames(0),selectedRows);
        }
             
        public void onRowUnselect(UnselectEvent event) {
            FacesMessage msg = new FacesMessage("Car Unselected", ((DynamicColumns) event.getObject()).getColumn1Value());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    
    	public void pageLoad() {
    		if (this.i == 0) {
    			getOnloadValueList();
    		}
    		this.i += 1;
    	}
    	
        public void genereateTable(String table)
        {
        	
        	dy=new ArrayList<DynamicColumns>();
        	
        	System.out.println("Coming into listener"+select);
        
        	System.out.println("select list length "+selectList.size());
 
        	selectList.clear();
        	for(int i=0;i<columnNames.length;i++)
        	{
        		columnNames[i]=null;
        	}
        	
        	if(table!=null)
        	dy=dataDAO.populateDynamicColunms(select);
        	
        	
    		if(col != dataDAO.getCount())
              {
                for(int co = 0;co < dataDAO.getCount();co++){
                	columnNames[co]=dataDAO.getColumnName(co);
                	tableType[co] = dataDAO.getColumnType(co);
                	dataTableColumns.add(new DataTableColumn(dataDAO.getColumnName(co),dataDAO.getColumnName(co)));
                	System.out.println(co+" index cococo "+columnNames[co]);
                 }
                col = dataDAO.getCount();
              }
    		
    		DynamicColumns dyn=new DynamicColumns();
    		
    		if(columnNames[0]!=null)
    		{
    			setColumn1(columnNames[0]);
    			System.out.println(columnNames[0]);
    			setVisable1(true);
    			selectList.add(getColumn1());
    		}
    		else
    		{
    			setVisable1(false);
    		}
    		if(columnNames[1]!=null)
    		{
    			setColumn2(columnNames[1]);
    			System.out.println(columnNames[1]);
    			setVisable2(true);
    			selectList.add(getColumn2());
    		}
    		else
    		{
    			setVisable2(false);
    		}
    		if(columnNames[2]!=null)
    		{
    			setColumn3(columnNames[2]);
    			System.out.println(columnNames[2]);
    			setVisable3(true);
    			selectList.add(getColumn3());
    		}
    		else
    		{
    			setVisable3(false);
    		}
    		if(columnNames[3]!=null)
    		{
    			setColumn4(columnNames[3]);
    			setVisable4(true);
    			selectList.add(getColumn4());
    		}
    		else
    		{
    			setVisable4(false);
    		}
    		if(columnNames[4]!=null)
    		{
    			setColumn5(columnNames[4]);
    			setVisable5(true);
    			selectList.add(getColumn5());
    		}
    		else
    		{
    			setVisable5(false);
    		}
    		if(columnNames[5]!=null)
    		{
    			setColumn6(columnNames[5]);
    			setVisable6(true);
    			selectList.add(getColumn6());
    		}
    		else
    		{
    			setVisable6(false);
    		}
    		if(columnNames[6]!=null)
    		{
    			setColumn7(columnNames[6]);
    			setVisable7(true);
    			selectList.add(getColumn7());
    		}
    		else
    		{
    			setVisable7(false);
    		}
    		if(columnNames[7]!=null)
    		{
    			setColumn8(columnNames[7]);
    			setVisable8(true);
    			selectList.add(getColumn8());
    			
    		}
    		else
    		{
    			setVisable8(false);
    		}
    		if(columnNames[8]!=null)
    		{
    			setColumn9(columnNames[8]);
    			setVisable9(true);
    			selectList.add(getColumn9());
    		}
    		else
    		{
    			setVisable9(false);
    		}
    		if(columnNames[9]!=null)
    		{
    			setColumn10(columnNames[9]);
    			setVisable10(true);
    			selectList.add(getColumn10());
    		}
    		else
    		{
    			setVisable10(false);
    		}
    		
    		
    		selected=dataDAO.populateTables();
    		
    		for(String i:selected)
    		{
    			System.out.println("select table list "+i);
    		}
    		
    		System.out.println("dataTableView"+select);
        }
    	
	public List<DataTableColumn> getDataTableColumns() {
		return dataTableColumns;
	}
	
	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public List<String> getAlgorithmList() {
		return algorithmList;
	}


	public void setAlgorithmList(List<String> algorithmList) {
		this.algorithmList = algorithmList;
	}


	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}

	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
	}
	public List<String> getSelected() {
		return selected;
	}
	public void setSelected(ArrayList<String> selected) {
		this.selected = selected;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}

	public List<DataTableColumn> getDataTableColumns1() {
		return dataTableColumns1;
	}

	public void setDataTableColumns1(List<DataTableColumn> dataTableColumns1) {
		this.dataTableColumns1 = dataTableColumns1;
	}

	public String getColumnNames(int index) {
		return columnNames[index];
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public List<DynamicColumns> getDy() {
		return dy;
	}

	public void setDy(List<DynamicColumns> dy) {
		this.dy = dy;
	}

	public DynamicColumns getSelectedData() {
		
		return selectedData;
	}
	public void setSelectedData(DynamicColumns selectedData) {
		this.selectedData = selectedData;
	}
	public DynamicColumns getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(DynamicColumns selectedRow) {
		this.selectedRow = selectedRow;
	}
		public List<DynamicColumns> getSelectedRows() {
		return selectedRows;
	}
	public void setSelectedRows(List<DynamicColumns> selectedRows) {
		this.selectedRows = selectedRows;
	}
	public Boolean getVisable1() {
		return visable1;
	}
	public void setVisable1(Boolean visable1) {
		this.visable1 = visable1;
	}
	public Boolean getVisable2() {
		return visable2;
	}
	public void setVisable2(Boolean visable2) {
		this.visable2 = visable2;
	}
	public Boolean getVisable3() {
		return visable3;
	}
	public void setVisable3(Boolean visable3) {
		this.visable3 = visable3;
	}
	public Boolean getVisable4() {
		return visable4;
	}
	public void setVisable4(Boolean visable4) {
		this.visable4 = visable4;
	}
	public Boolean getVisable5() {
		return visable5;
	}
	public void setVisable5(Boolean visable5) {
		this.visable5 = visable5;
	}
	public Boolean getVisable6() {
		return visable6;
	}
	public void setVisable6(Boolean visable6) {
		this.visable6 = visable6;
	}
	public Boolean getVisable7() {
		return visable7;
	}
	public void setVisable7(Boolean visable7) {
		this.visable7 = visable7;
	}
	public Boolean getVisable8() {
		return visable8;
	}
	public void setVisable8(Boolean visable8) {
		this.visable8 = visable8;
	}
	public Boolean getVisable9() {
		return visable9;
	}
	public void setVisable9(Boolean visable9) {
		this.visable9 = visable9;
	}
	public Boolean getVisable10() {
		return visable10;
	}
	public void setVisable10(Boolean visable10) {
		this.visable10 = visable10;
	}
	public String[] getSelectCriteria() {
		return selectCriteria;
	}
	public void setSelectCriteria(String[] selectCriteria) {
		this.selectCriteria = selectCriteria;
	}
	public List<String> getSelectList() {
		return selectList;
	}
	public void setSelectList(List<String> selectList) {
		this.selectList = selectList;
	}
	public int getDoo() {
		return doo;
	}
	public void setDoo(int doo) {
		this.doo = doo;
	}
	public List<DynamicColumns> getDeleteRows() {
		return deleteRows;
	}
	public void setDeleteRows(List<DynamicColumns> deleteRows) {
		this.deleteRows = deleteRows;
	}
	public boolean isDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}


	public String getSelectedAlgorithm() {
		return selectedAlgorithm;
	}


	public void setSelectedAlgorithm(String selectedAlgorithm) {
		this.selectedAlgorithm = selectedAlgorithm;
	}


	public String[] getTableType() {
		return tableType;
	}


	public void setTableType(String tableType[]) {
		this.tableType = tableType;
	}


	public double getToleranceValue() {
		return toleranceValue;
	}


	public void setToleranceValue(double toleranceValue) {
		this.toleranceValue = toleranceValue;
	}


	public List<String> getTypes() {
		return Types;
	}


	public void setTypes(List<String> types) {
		Types = types;
	}

	public boolean isTolField() {
		return tolField;
	}

	public void setTolField(boolean tolField) {
		this.tolField = tolField;
	}
}
