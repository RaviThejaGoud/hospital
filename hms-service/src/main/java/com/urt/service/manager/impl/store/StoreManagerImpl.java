package com.urt.service.manager.impl.store;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.ItemTypeVO;
import com.hyniva.sms.ws.vo.ItemTypesListVO;
import com.hyniva.sms.ws.vo.sotre.IssuedItemDetailsVO;
import com.hyniva.sms.ws.vo.sotre.ItemVO;
import com.hyniva.sms.ws.vo.sotre.SupplierVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.account.CustomerBankAccountDetails;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.store.Item;
import com.urt.persistence.model.store.ItemType;
import com.urt.persistence.model.store.ItemsIssued;
import com.urt.persistence.model.store.StoreData;
import com.urt.persistence.model.store.Supplier;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.store.StoreManager;
import com.urt.util.jrexception.JRExceptionClient;

@Component
public class StoreManagerImpl extends UniversalManagerImpl implements StoreManager {

	
	@Autowired
	protected SessionFactory sessionFactory;

	public  Map<String,String>  addUpdateStore(StoreData storeObj, long userId, long custId) throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering storemanager 'addUpdateStore' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try {
				StoreData storeData = null;
				Address address = null;
				if(null != storeObj){					
					storeData = (StoreData)this.get(StoreData.class,storeObj.getId());
					if(!ObjectFunctions.isNullOrEmpty(storeData)){
						if(ObjectFunctions.isNullOrEmpty(storeData.getStoreAddress())){
							address = new Address();
							address.setCreatedById(userId);
							address.setCreatedDate(new Date());
						} else{
							address = storeData.getStoreAddress();
							address.setLastAccessDate(new Date());
							address.setLastUpdatedById(userId);
							address.setLastUpdatedDate(new Date());
						}
						storeData.setLastUpdatedById(userId);
						storeData.setLastUpdatedDate(new Date());
						storeData.setLastAccessDate(new Date());
						
					} else{
						storeData = new StoreData();
						address = new Address();
						storeData.setCreatedById(userId);
						storeData.setCreatedDate(new Date());	
						storeData.setCustId(custId);
						address.setCreatedById(userId);
						address.setCreatedDate(new Date());					
					}
					storeData.setStoreName(storeObj.getStoreName());
					storeData.setStoreKeeperAccountId(storeObj.getStoreKeeperAccountId());
					address.setStreetName(storeObj.getStoreAddress().getStreetName());
					address.setCity(storeObj.getStoreAddress().getCity());
					address.setState(storeObj.getStoreAddress().getState());
					address.setCountry(storeObj.getStoreAddress().getCountry());
					address.setPostalCode(storeObj.getStoreAddress().getPostalCode());	
					storeData.setStoreAddress(address);
					this.save(storeData);
					storeObj = null;
					storeData = null;
				}		
				msg.put("0", "Store is added/updated successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		return msg;
	}
	
	public Map<String,String>  addItemTypes(ItemTypesListVO itemTypes, long userId, long custId) 
			throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering storemanager 'addItemTypes' method");
		}		
		Map<String, String> msg = new HashMap<String,String>();
		try{
			if(!ObjectFunctions.isNullOrEmpty(itemTypes)){
				List<ItemTypeVO> itemTypesList = itemTypes.getItemTypesList();
				if(null != itemTypesList && itemTypesList.size() > 0){
					for(ItemTypeVO itemTypevo : itemTypesList){
						ItemType itemTypeObj = new ItemType();
						if(null != itemTypevo){
							itemTypeObj.setTypeName(itemTypevo.getTypeName());
							itemTypeObj.setMeasurementType(itemTypevo.getMeasurementType());
							itemTypeObj.setCreatedById(userId);
							itemTypeObj.setCreatedDate(new Date());
							itemTypeObj.setCustId(custId);
							this.save(itemTypeObj);
							itemTypeObj = null;
						}	
					}
					msg.put("0", "Item Type is added successfully");
				}
				
			}			
			
		} catch(Exception e) {
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		
		}
		
		return msg;
		
	}
	
	public Map<String,String> updateItemType(ItemType itemTypeObj, long userId)
			throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering storemanager 'updateItemType' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
			ItemType itemType = null;
			if(!ObjectFunctions.isNullOrEmpty(itemTypeObj)){
				itemType = (ItemType)this.get(ItemType.class, itemTypeObj.getId());
				if(!ObjectFunctions.isNullOrEmpty(itemType)){
					itemType.setLastUpdatedById(userId);
					itemType.setLastUpdatedDate(new Date());
					itemType.setLastAccessDate(new Date());
					itemType.setTypeName(itemTypeObj.getTypeName());
					itemType.setMeasurementType(itemTypeObj.getMeasurementType());
					this.save(itemType);
					itemType=null;
					msg.put("0", "Itemtype is updated successfully");					
				}				
			} 
			
		} catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		
		return msg;
		
	}
	
	public Map<String,String>  addItemsToStore(ItemVO item, long userId, long custId) 
			throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering storemanager 'addItemstoStore' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
			Item itemObj = null;
			if(!ObjectFunctions.isNullOrEmpty(item)){
				if(!ObjectFunctions.isNullOrEmpty(item.getItemId())){
					itemObj = (Item)this.get(Item.class, item.getItemId());
				}
				if(!ObjectFunctions.isNullOrEmpty(itemObj)){
					itemObj.setItemName(item.getItemName());
					itemObj.setItemCode(item.getItemCode());
					itemObj.setLastUpdatedById(userId);
					itemObj.setLastUpdatedDate(new Date());
					itemObj.setLastAccessDate(new Date());
				}  else{
					itemObj = new Item();
					itemObj.setCreatedById(userId);
					itemObj.setCreatedDate(new Date());
					itemObj.setQuantity(item.getQuantity());
					itemObj.setTotalPrice(item.getTotalPrice());
					itemObj.setStoreId(item.getStoreId());
					itemObj.setItemTypeId(item.getItemTypeId());
					itemObj.setItemName(item.getItemName());
					itemObj.setItemCode(item.getItemCode());
					itemObj.setSupplierId(item.getSupplierId());
					itemObj.setCustId(custId);
				}				
				this.save(itemObj);
				itemObj = null;
				
				msg.put("0", "Item is added/updated successfully");
				
			}
			
		} catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		
		return msg;		
		
	}
	
	public Map<String,String>  addUpdateSupplier(Supplier supplier, long userId, long custId) throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering storemanager 'addUpdateSupplier' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
			Supplier supplierObj = null;
			Address address = null;
			CustomerBankAccountDetails bankDetails = null;
			if(!ObjectFunctions.isNullOrEmpty(supplier)){
				supplierObj = (Supplier)this.get(Supplier.class, supplier.getId());				
				if(!ObjectFunctions.isNullOrEmpty(supplierObj)){
					supplierObj.setLastUpdatedById(userId);
					supplierObj.setLastUpdatedDate(new Date());
					supplierObj.setLastAccessDate(new Date());
					address = supplierObj.getAddress();
					bankDetails = supplierObj.getSupplierBankAccntDetails();
					if(ObjectFunctions.isNullOrEmpty(address)){
						address = new Address();
					}else{
						address.setLastUpdatedById(userId);
						address.setLastUpdatedDate(new Date());
						address.setLastAccessDate(new Date());	
					} if(ObjectFunctions.isNullOrEmpty(bankDetails)){
						bankDetails = new CustomerBankAccountDetails();
					} else {
						bankDetails.setLastAccessDate(new Date());
						bankDetails.setLastUpdatedById(userId);
						bankDetails.setLastUpdatedDate(new Date());
					}
					
				} else {
					supplierObj = new Supplier();	
					address = new Address();
					bankDetails = new CustomerBankAccountDetails();
					supplierObj.setCreatedById(userId);
					supplierObj.setCreatedDate(new Date());
					supplierObj.setCustId(custId);
					address.setCreatedById(userId);
					address.setCreatedDate(new Date());
					bankDetails.setCreatedById(userId);
					bankDetails.setCreatedDate(new Date());
				}
				supplierObj.setSupplierName(supplier.getSupplierName());		
				supplierObj.setMobileNumber(supplier.getMobileNumber());	
				supplierObj.setPhoneNumber(supplier.getPhoneNumber());
				supplierObj.setEmail(supplier.getEmail());
				address.setStreetName(supplier.getAddress().getStreetName());
				address.setCity(supplier.getAddress().getCity());
				address.setState(supplier.getAddress().getState());
				address.setCountry(supplier.getAddress().getCountry());
				address.setPostalCode(supplier.getAddress().getPostalCode());	
				bankDetails.setBankName(supplier.getSupplierBankAccntDetails().getBankName());
				bankDetails.setAccountNumber(supplier.getSupplierBankAccntDetails().getAccountNumber());
				supplierObj.setAddress(address);
				supplierObj.setSupplierBankAccntDetails(bankDetails);
				this.save(supplierObj);
				supplier = null;
				supplierObj = null;
				
				msg.put("0", "Supplier is added/updated successfully");
			}
			
		} catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		
		return msg;

	}
	public Map<String,String>  updateSupplierStatus(long supplierId) 
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering storemanager 'updateSupplierStatus' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
			Supplier supplierObj = (Supplier)this.get(Supplier.class, supplierId);
			if(!ObjectFunctions.isNullOrEmpty(supplierObj)){
				supplierObj.setSupplierStatus("N");
				this.save(supplierObj);
				supplierObj = null;
			}
			msg.put("0", "Supplier is deactivated successfully");
		} catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		
		return msg;
		
	}
	
	public Map<String,String>  addIssuedItems(ItemsIssued itemsIssued, Long itemId, User loginUser) 
			throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering storemanager 'addIssuedItems' method");
		}
		Map<String, String> msg = new HashMap<String,String>();
		try{
			if(itemId > 0){
				if(!ObjectFunctions.isNullOrEmpty(itemsIssued)){					
					ItemsIssued itemIssue = new ItemsIssued();
					itemIssue.setCreatedById(loginUser.getId());
					itemIssue.setCustId(loginUser.getCustId());
					itemIssue.setCreatedDate(new Date());
					itemIssue.setItemId(itemId);
					itemIssue.setCustId(loginUser.getCustId());
					itemIssue.setQuantity(itemsIssued.getQuantity());
					itemIssue.setRecieverName(itemsIssued.getRecieverName());
					itemIssue.setIssuedDate(new Date());
					itemIssue.setIssuedBy(loginUser.getId());
					itemIssue.setIssuerName(loginUser.getPerson().getPersonFullName());
					this.save(itemIssue);
				}
			} 
			
			msg.put("0", "Items are issued successfully");
			
		} catch(Exception e){
			e.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(e);jre = null;
			msg.put("1", "System error occurred. Please contact customer support!");
		}
		
		return msg;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 
	 */
	public List<IssuedItemDetailsVO> getIssuedItemDetails(long storeId)
			throws URTUniversalException {
		List<IssuedItemDetailsVO>  issuedItemDetailsListVO = new ArrayList<IssuedItemDetailsVO>();
		List<Object[]> issuedItemsList = this.getAll("select IST.quantity as issuedQuantity,IST.recieverName,IST.issuedDate,IST.issuerName,I.custId,IT.typeName,I.itemName,I.itemCode "
				+ "	from issuedItems IST LEFT JOIN  item I on (IST.itemId = I.Id)  LEFT JOIN itemType IT on (IT.id = I.itemTypeId) Where storeId = "+storeId);
		
		IssuedItemDetailsVO issuedItemDetailsVO = null;
		
		if(!ObjectFunctions.isNullOrEmpty(issuedItemsList)){	
			for(Object[] objectArray : issuedItemsList){
				issuedItemDetailsVO = new IssuedItemDetailsVO();
				issuedItemDetailsVO.setQuantity(Long.parseLong(objectArray[0].toString()));
				issuedItemDetailsVO.setRecievedBy(objectArray[1].toString());
				Date date = DateFormatter.parseString(DateFormatter.YYYYMMDDHHMMSSN_3_PATTERN,objectArray[2].toString());
				SimpleDateFormat issuedDateStr = new SimpleDateFormat(DateFormatter.ddMMMyyyy_PATTERN1);
				issuedItemDetailsVO.setIssuedDate(issuedDateStr.format(date));
				issuedItemDetailsVO.setIssuerBy(objectArray[3].toString());
				issuedItemDetailsVO.setCustId(Long.parseLong(objectArray[4].toString()));
				issuedItemDetailsVO.setItemType(ObjectFunctions.isNullOrEmpty(objectArray[5])?"":objectArray[5].toString());
				issuedItemDetailsVO.setItemName(ObjectFunctions.isNullOrEmpty(objectArray[6])?"":objectArray[6].toString());
				issuedItemDetailsVO.setItemCode(ObjectFunctions.isNullOrEmpty(objectArray[7])?"":objectArray[7].toString());
				issuedItemDetailsListVO.add(issuedItemDetailsVO);
				issuedItemDetailsVO = null;
			}
		}
		return issuedItemDetailsListVO;
	}

	@Override
	public ItemVO getItemDetailsById(Long itemId) throws URTUniversalException,
			ParseException {
		Item item = (Item) this.get(Item.class, itemId);
		
		return item.deepCopyEntityToVO();
	}
	
	public int getIssuedItemsQuantity(Long itemId) {
		
		return dao.getSum("select sum(quantity) from issuedItems where itemId = "+itemId );
	}
	
	public List<SupplierVO> getSupplierDetails(long custId)
	{
		List<SupplierVO>  suppliersListVO = new ArrayList<SupplierVO>();
		@SuppressWarnings("unchecked")
		List<Object[]> supplierDetailsList = this.getAll("select s.id,s.supplierName,s.mobileNumber,s.email,addr.streetName as streetName,addr.city as city,addr.state as state,addr.country as country,"
				+ "addr.postalcode as postalcode,IF(i.id IS NULL , 'N','Y') as itemAdded"
				+ "	from supplier s	left join Address addr ON(addr.id =s.addressId)	left join item i ON (i.supplierid =s.id)"
				+ "	where s.supplierStatus ='Y'  and s.custId ="
				+ custId
				+ " group by s.id");
		
		SupplierVO supplierVO = null;
		
		if(!ObjectFunctions.isNullOrEmpty(supplierDetailsList)){	
			for(Object[] objectArray : supplierDetailsList){
				supplierVO  = new SupplierVO();
				supplierVO.setSupplierId(Long.parseLong(objectArray[0].toString()));
				supplierVO.setSupplierName(objectArray[1].toString());
				supplierVO.setMobileNumber((null!=objectArray[2]?objectArray[2].toString():""));
				supplierVO.setEmail((null!=objectArray[3]?objectArray[3].toString():""));
				supplierVO.setStreetName((null!=objectArray[4]?objectArray[4].toString():null));
				supplierVO.setCity((null!=objectArray[5]?objectArray[5].toString():null));
				supplierVO.setState((null!=objectArray[6]?objectArray[6].toString():null));
				supplierVO.setCountry((null!=objectArray[7]?objectArray[7].toString():null));
				supplierVO.setPostalcode((null!=objectArray[8]?objectArray[8].toString():null));
				supplierVO.setItemsSupplied((null!=objectArray[9]?objectArray[9].toString():"N"));
				suppliersListVO.add(supplierVO);				
			}
		}
		
		
		return suppliersListVO;
	}
	
	
	
}