package com.urt.persistence.model.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hyniva.sms.ws.vo.sotre.ItemVO;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "item")
public class Item extends PersistentObject{
	
	private static final long serialVersionUID = 1L;
	private Long storeId;
	private Long itemTypeId;	
	private String itemCode;
	private String itemName;
	private Long quantity;
	private Double totalPrice;
	private Long supplierId;
	private Long custId;
	/**
	 * @return the storeId
	 */
	@Column(nullable = false)
	public Long getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the itemTypeId
	 */
	@Column(nullable = false)
	public Long getItemTypeId() {
		return itemTypeId;
	}
	/**
	 * @param itemTypeId the itemTypeId to set
	 */
	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	/**
	 * @return the itemCode
	 */
	@Column(nullable = false, length = 20)
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * @return the itemName
	 */
	@Column(nullable = false, length = 40)
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the quantity
	 */
	@Column(nullable = false, length = 10)
	public Long getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the totalPrice
	 */
	@Column(nullable = false, length = 20)
	public Double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the supplierId
	 */
	@Column(nullable = false)
	public Long getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	
	/**
	 * @return the custId
	 */
	@Column(nullable = false)
	public Long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public ItemVO deepCopyEntityToVO(){
		ItemVO itemVO = new ItemVO();
		itemVO.setItemId(this.getId());
		itemVO.setItemCode(this.getItemCode());
		itemVO.setItemName(this.getItemName());
		itemVO.setItemTypeId(this.getItemTypeId());
		itemVO.setQuantity(this.getQuantity());
		itemVO.setStoreId(this.getStoreId());
		itemVO.setSupplierId(this.getSupplierId());
		itemVO.setTotalPrice(this.getTotalPrice());
		
		return itemVO;
	}
	
	

	
}
