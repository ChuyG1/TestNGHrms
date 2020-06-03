package com.hrms.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.utils.CommonMethods;

public class AddEmployeePageElements extends CommonMethods {
	
	@FindBy(id="firstName")
	public WebElement firstName;
	
	@FindBy(id="lastName")
	public WebElement lastName;
	
	@FindBy(id="employeeId")
	public WebElement employeeId;
	
	@FindBy(id="chkLogin")
	public WebElement checkboxLoginDetails;
	
	@FindBy(id="user_name")
	public WebElement username;
	
	@FindBy(id="user_password")
	public WebElement password;
	
	@FindBy(id="btnSave")
	public WebElement saveBtn;
	
	@FindBy(id="re_password")
	public WebElement confirmpassword;
	
	public AddEmployeePageElements() {
		PageFactory.initElements(driver, this);
	}
}
 