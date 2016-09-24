package com.infinite.onlinestore.controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import com.infinite.onlinestore.services.Payment;
import com.infinite.onlinestore.services.PaymentProcessProxy;

@ManagedBean
@SessionScoped
public class PaymentController {

	private String paymentOption;
	private String bankOption;
	private String imageOption;
    private Payment payment;
    @PostConstruct
    public void init()
    {
    	payment=new Payment();
    }
    
    public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getImageOption() {
		return imageOption;
	}

	public void setImageOption(String imageOption) {
		this.imageOption = imageOption;
	}

	private List<String> options;
    private List<Bank> bankOptions;
    
    

	public List<Bank> getBankOptions() {
		bankOptions=new ArrayList<Bank>();
		Bank bank =new Bank();
		bank.setBankName("Axis");
		bank.setImageName("axis.png");
		bankOptions.add(bank);
		bank =new Bank();
		bank.setBankName("HDFC");
		bank.setImageName("hdfc.png");
		bankOptions.add(bank);
		bank =new Bank();
		bank.setBankName("ICICI");
		bank.setImageName("icici.png");
		bankOptions.add(bank);
		bank =new Bank();
		bank.setBankName("Punjab");
		bank.setImageName("punjab.png");
		bankOptions.add(bank);
		bank =new Bank();
		bank.setBankName("RBS");
		bank.setImageName("rbs.jpg");
		bankOptions.add(bank);
		bank =new Bank();
		bank.setBankName("SBI");
		bank.setImageName("sbi.png");
		bankOptions.add(bank);
		bank =new Bank();
		bank.setBankName("SC");
		bank.setImageName("sc.png");
		bankOptions.add(bank);
		
		return bankOptions;
	}

	public void setBankOptions(List<Bank> bankOptions) {
		this.bankOptions = bankOptions;
		
	}

	

	



	public String getBankOption() {
		return bankOption;
	}

	public void setBankOption(String bankOption) {
		this.bankOption = bankOption;
	}

	public List<String> getOptions() {
		options =new ArrayList<String>();
		options.add("Cash");
		options.add("NetBanking");
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	
	public void handleChange(){  
	    System.out.println(paymentOption);
	    
	}
	
	public String fundTransafer()
	{
		boolean status=false;
		String path="payment.xhtml";
		PaymentProcessProxy pproxy=new PaymentProcessProxy();
		try {
			status=pproxy.addPayment(payment);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   if (status)	
		path= "index.xhtml";
	   return path;
	}
}
