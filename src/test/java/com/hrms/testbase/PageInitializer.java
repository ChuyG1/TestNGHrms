package com.hrms.testbase;

import com.hrms.pages.AddEmployeePageElements;
import com.hrms.pages.DashBoardPageElements;
import com.hrms.pages.LoginPageElements;
import com.hrms.pages.PersonalDetailsPageElements;

public class PageInitializer extends BaseClass {
	
	public static LoginPageElements login;
	public static DashBoardPageElements dashboard;
	public static AddEmployeePageElements addEmp;
	public static PersonalDetailsPageElements pdetails;
	
	public static void initialize() {
		
		login=new LoginPageElements();
		dashboard=new DashBoardPageElements();
		addEmp=new AddEmployeePageElements();
		pdetails=new PersonalDetailsPageElements();
		
	}
}
