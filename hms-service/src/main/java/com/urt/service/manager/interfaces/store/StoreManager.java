package com.urt.service.manager.interfaces.store;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hyniva.sms.ws.vo.ItemTypesListVO;
import com.hyniva.sms.ws.vo.sotre.IssuedItemDetailsVO;
import com.hyniva.sms.ws.vo.sotre.ItemVO;
import com.hyniva.sms.ws.vo.sotre.SupplierVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.store.ItemType;
import com.urt.persistence.model.store.ItemsIssued;
import com.urt.persistence.model.store.StoreData;
import com.urt.persistence.model.store.Supplier;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;



/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="UserManager.java.html"><i>View Source</i></a></p>
 */
public interface StoreManager extends UniversalManager {
	
	public Map<String,String>  addUpdateStore(StoreData storeObj, long userId, long custId) throws URTUniversalException;
	
	public Map<String,String>  addItemTypes(ItemTypesListVO storeObj, long userId, long custId) throws URTUniversalException;
	
	public Map<String,String>  updateItemType(ItemType itemTypeObj, long userId) throws URTUniversalException;
	
	public Map<String,String>  addItemsToStore(ItemVO item, long userId, long custId) throws URTUniversalException;
	
	public Map<String,String>  addUpdateSupplier(Supplier supplier, long userId, long custId) throws URTUniversalException;
	
	public Map<String,String>  updateSupplierStatus(long supplierId) throws URTUniversalException;
	
	public Map<String,String>  addIssuedItems(ItemsIssued itemsIssued, Long itemId, User user) throws URTUniversalException;
	
	public List<IssuedItemDetailsVO> getIssuedItemDetails(long storeId) throws URTUniversalException, ParseException;
	
	public ItemVO getItemDetailsById(Long itemId) throws URTUniversalException, ParseException;
	
	public int getIssuedItemsQuantity(Long itemId);
	List<SupplierVO> getSupplierDetails(long custId);
}