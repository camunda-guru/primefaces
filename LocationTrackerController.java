package com.infinite.onlinestore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.infinite.onlinestore.beans.LocationTrackerBeanRemote;
import com.infinite.onlinestore.entities.Address;

@ManagedBean
@SessionScoped
public class LocationTrackerController {

	@EJB
	private LocationTrackerBeanRemote locationTrackerBeanRemote;
	private List<Address> addressList;
	private int orderId;
	private Address permAddress;
	private Address commnAddress;
	
	public Address getPermAddress() {
		return permAddress;
	}

	public void setPermAddress(Address permAddress) {
		this.permAddress = permAddress;
	}

	public Address getCommnAddress() {
		return commnAddress;
	}

	public void setCommnAddress(Address commnAddress) {
		this.commnAddress = commnAddress;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocationTrackerBeanRemote getLocationTrackerBeanRemote() {
		return locationTrackerBeanRemote;
	}

	public void setLocationTrackerBeanRemote(LocationTrackerBeanRemote locationTrackerBeanRemote) {
		this.locationTrackerBeanRemote = locationTrackerBeanRemote;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	@PostConstruct
	public void init()
	{
		addressList=new ArrayList<Address>();
		permAddress=new Address();
		commnAddress=new Address();
	}

	public List<Address> getAddressList() {
		return addressList;
	}
	
	
	public String getAllAddress()
	{
		List<Object> data=locationTrackerBeanRemote.findAddress(orderId);
		System.out.println(data.size());
		Address addr=null;
		for(Object obj : data)
		{
			addr=(Address) obj;
			System.out.println(addr.getCity());
			addressList.add(addr);
		}
		permAddress=addressList.get(0);
		commnAddress=addressList.get(1);
		return "locationMap.xhtml";
	}
	
}
