package com.iiitb.ddf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="selectCriteria ")
@RequestScoped
public class SelectCriteria {
	int i = 0;
	private String select;
	private List<String> selected;
	private String year;  
    private List<Integer> years;
    
    public List<String> selectCry() {
    	System.out.println("hello inside init");
    	selected = new ArrayList<String>();
    	selected.add(0,"select1");
    	selected.add(1,"select2");
    	selected.add(2,"select3");
    	
        years = new ArrayList<Integer>();
        years.add(2012);
        years.add(2013);
        years.add(2014);
        years.add(2015);
        years.add(2016);
        years.add(2017);
        
		return selected;
        
    }
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public List<String> getSelected() {
		return selected;
	}
	public void setSelected(List<String> selected) {
		this.selected = selected;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<Integer> getYears() {
		return years;
	}
	public void setYears(List<Integer> years) {
		this.years = years;
	}

}
