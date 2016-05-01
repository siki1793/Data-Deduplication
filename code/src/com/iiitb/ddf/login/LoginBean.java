package com.iiitb.ddf.login;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.iiitb.dao.LoginDAO;
import com.iiitb.ddf.utility.EncryptionDecryption;

public class LoginBean {

	String name;
	String password;
	Connection conn;
	PreparedStatement queryStatement;

	HttpSession session;

	EncryptionDecryption encDec = new EncryptionDecryption();
	LoginDAO userDAO = new LoginDAO();

	public String submit() throws Exception, NoSuchAlgorithmException,
			InvalidKeySpecException, GeneralSecurityException,
			GeneralSecurityException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		session = request.getSession(false);

		String key = "ezeon8547";
		String enc = encDec.encrypt(key, password);
		System.out.println("Original text: " + password);
		System.out.println("Encrypted text: " + enc);
		String plainAfter = encDec.decrypt(key, enc);
		System.out.println("Original text after decryption: " + plainAfter);
		boolean check = userDAO.login(name, enc);
		//boolean check = true;
		System.out.println("value==" + check);
		if (check == true) {

			System.out.println("User matched :)");
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", name);
			
			session.setAttribute("username", name);
			
			return "valid";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid Login!", "Please Try Again!"));
			System.out.println("User not found !!! :(");
			return "invalid";
		}
	}
	// ----- LOGOUT ----- //

		public String logout() {
			System.out.println("Inside Logout ---");
			System.out.println("USER==" + name);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 

		//	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			//session.invalidate();

			return "logout";
		}
		
		public String returnHome(){
			
			FacesContext.getCurrentInstance().getExternalContext();
			
			return "home";
		}

		// ---- Entry Time ---- //

		public String entryTime() {
			System.out.println("inside checkEntry !!!!");
			//boolean chkEntry = userdao.checkEntryTime(name);
			boolean chkEntry = true;
			if (chkEntry == true) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Status", "You already fill your Entry Time...");
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				System.out.println("Entry already done....");
			} 
			else {
				//String record = userdao.updateEntryTime(name);
				String record = "10 o clock";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Status","Your entry time "+record +" is captured....");
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				System.out.println("Entry Captured....");
				
				System.out.println("record value==" + record);
			}
			return null;
		}

		// ---- Exit Time ---- //
		public String exitTime() {
			/*System.out.println("inside checkExit !!!!");
			boolean chkEntry= userdao.checkExitTimeBeforeEntry(name);
			if(chkEntry==true){
				boolean chkExit = userdao.checkExitTime(name);
				if (chkExit == true) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Status", "You already fill your Exit Time...");
					RequestContext.getCurrentInstance().showMessageInDialog(message);
					System.out.println("Exit already Done....");
				} else {
					String exitRecord = userdao.updateExitTime(name);
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Status","Your exit time "+exitRecord+" is captured....");
						RequestContext.getCurrentInstance().showMessageInDialog(message);
						System.out.println("Exit Captured....");
					
					
				}
			}
			else{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Status", "EntryTime is not yet submitted...");
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				System.out.println("Entry is not yet Done....");
			}*/
			
			
			return null;
		}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
