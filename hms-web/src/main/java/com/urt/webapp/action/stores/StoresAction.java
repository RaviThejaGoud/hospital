package com.urt.webapp.action.stores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.pdf.PDFGenerator;
import com.churchgroup.util.pdf.PdfHeaderFooterMarkJasper;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.churchgroup.util.xls.ExcelView;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.hyniva.sms.ws.vo.ItemTypesListVO;
import com.hyniva.sms.ws.vo.sotre.IssuedItemDetailsVO;
import com.hyniva.sms.ws.vo.sotre.ItemVO;
import com.hyniva.sms.ws.vo.sotre.SupplierVO;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.common.Country;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.store.Item;
import com.urt.persistence.model.store.ItemType;
import com.urt.persistence.model.store.ItemsIssued;
import com.urt.persistence.model.store.StoreData;
import com.urt.persistence.model.store.Supplier;
import com.urt.persistence.model.store.ViewIssuedItemDetails;
import com.urt.persistence.model.store.ViewStoreBasicDetails;
import com.urt.persistence.model.store.ViewStoreDetails;
import com.urt.persistence.model.store.ViewSupplierDetails;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.jrexception.JRExceptionClient;

@Namespace("/store")

public class StoresAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StoreData store;	

	protected List<StoreData> storeDataList = null;		

	protected List<ViewAllUsers> storeKeeperUsersList = null;

	protected List measurementsList = null;

	protected ItemTypesListVO itemTypesListVO = null;

	private ItemType itemType;

	private List<ItemType> itemTypesList = null;	

	//private Item item;

	private ItemVO item;
	private List<ItemVO> itemsList = null;

	private Supplier supplier;

	private List<Supplier> suppliersList = null;

	private long selectedStore ;

	private Long itemId ;
	private Long storeId;
	private String itemName;
	private Long availableQuantity =0L;
	private ItemsIssued issuedItems;
	private List<IssuedItemDetailsVO> issuedItemsList = null;
	
	private List<SupplierVO> supplierDetailsList = null;
	
	private List<ViewStoreBasicDetails> storeBasicDetailsList = null;		

	public StoreData getStore() {
		if(ObjectFunctions.isNullOrEmpty(this.store)){
			this.store = new StoreData();
		}
		return this.store;
	}
	public void setStore(StoreData store) {
		this.store = store;
	}

	public List<StoreData> getStoreDataList() {
		return storeDataList;
	}
	public void setStoreDataList(List<StoreData> storeDataList) {
		this.storeDataList = storeDataList;
	}
	public List<ViewAllUsers> getStoreKeeperUsersList() {
		return storeKeeperUsersList;
	}
	public void setStoreKeeperUsersList(List<ViewAllUsers> storeKeeperUsersList) {
		this.storeKeeperUsersList = storeKeeperUsersList;
	}
	public List getMeasurementsList() {
		return measurementsList;
	}
	public void setMeasurementsList(List measurementsList) {
		this.measurementsList = measurementsList;
	}
	public ItemTypesListVO getItemTypesListVO() {
		return itemTypesListVO;
	}
	public void setItemTypesListVO(ItemTypesListVO itemTypesListVO) {
		this.itemTypesListVO = itemTypesListVO;
	}

	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	public List<ItemType> getItemTypesList() {
		return itemTypesList;
	}
	public void setItemTypesList(List<ItemType> itemTypesList) {
		this.itemTypesList = itemTypesList;
	}	

	public Supplier getSupplier() {
		if (ObjectFunctions.isNullOrEmpty(this.supplier)) {
			this.supplier = new Supplier();
		}
		return this.supplier;		
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}	
	public List<Supplier> getSuppliersList() {
		return suppliersList;
	}
	public void setSuppliersList(List<Supplier> suppliersList) {
		this.suppliersList = suppliersList;
	}

	public long getSelectedStore() {
		return selectedStore;
	}
	public void setSelectedStore(long selectedStore) {
		this.selectedStore = selectedStore;
	}		
	public ItemsIssued getIssuedItems() {
		return issuedItems;
	}
	public void setIssuedItems(ItemsIssued issuedItems) {
		this.issuedItems = issuedItems;
	}
	/**
	 * @return the issuedItemsList
	 */
	public List<IssuedItemDetailsVO> getIssuedItemsList() {
		return issuedItemsList;
	}
	/**
	 * @param issuedItemsList the issuedItemsList to set
	 */
	public void setIssuedItemsList(List<IssuedItemDetailsVO> issuedItemsList) {
		this.issuedItemsList = issuedItemsList;
	}
	/**
	 * @return the item
	 */
	public ItemVO getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(ItemVO item) {
		this.item = item;
	}
	/**
	 * @return the itemId
	 */
	public Long getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the storeId
	 */
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
	 * @return the availableQuantity
	 */
	public Long getAvailableQuantity() {
		return availableQuantity;
	}
	/**
	 * @param availableQuantity the availableQuantity to set
	 */
	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	/**
	 * @return the itemsList
	 */
	public List<ItemVO> getItemsList() {
		return itemsList;
	}
	/**
	 * @param itemsList the itemsList to set
	 */
	public void setItemsList(List<ItemVO> itemsList) {
		this.itemsList = itemsList;
	}

	@Actions( { 
		@Action(value = "ajaxDoManageStores", results = { @Result(location = "ajaxManageStores.jsp", name = "success") })
	})
	public String ajaxDoManageStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoManageStaff' method");
		}
		try {			
			getStoreBasicDetails();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { 
		@Action(value = "ajaxDoAddStore", results = { @Result(location = "ajaxAddStore.jsp", name = "success") }) })
	public String ajaxDoAddStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddStore' method");
		}
		try {			
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			setCountryList((List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST));	
			setStoreKeeperUsersList(storeManager.getAll(ViewAllUsers.class, "custId ="+getUser().getCustId() +" and roleId='54' and accountEnabled='"+Constants.YES_STRING+"'"));
			if(getStore().getId() > 0){					
				setStore((StoreData)storeManager.get(StoreData.class,getStore().getId()));				
			}else{					
				setStore(null);
			}


		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { 
		@Action(value = "ajaxDoSaveStore", results = { @Result(location = "ajaxManageStores.jsp", name = "success") }) })
	public String ajaxDoSaveStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoSaveStore' method");
		}
		try {			
			if(!ObjectFunctions.isNullOrEmpty(getStore())){
				addActionMessages(storeManager.addUpdateStore(getStore(), getUser().getId(), getUser().getCustId()));				
			}			
			getStoreBasicDetails();
			setStore(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	} 

	@Actions({
		@Action(value="ajaxDoAddItemType", results= {@Result (location = "ajaxAddItemTypes.jsp" , name="success") }) })
	public String  ajaxDoAddItemType() throws URTUniversalException {		
		try{
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddItemType' method");
			}
			setItemType(null);
			setItemTypesListVO(null);
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;

	}

	@Actions({
		@Action(value="ajaxViewItemTypes", results= {@Result (location = "ajaxManageItemTypes.jsp" , name="success") })
	})
	public String ajaxViewItemTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewItemTypes' method");
		}		
		try{			
			List<ItemType> itemTypesListObj = storeManager.getAll(ItemType.class,"custId="+getUser().getCustId());
			setItemTypesList(itemTypesListObj);
			getSession().setAttribute("ItemTypesList", itemTypesListObj);			
		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value="ajaxSaveItemTypes", results= {@Result (location = "ajaxManageItemTypes.jsp" , name="success") })
	})

	public String ajaxSaveItemTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveItemTypes' method");
		}
		try{			
			ItemTypesListVO itemTypesObj = getItemTypesListVO();
			if(null != itemTypesObj){				
				addActionMessages(storeManager.addItemTypes(itemTypesObj, getUser().getId(), getUser().getCustId()));
			} 
			List<ItemType> itemTypesListObj = storeManager.getAll(ItemType.class,"custId="+getUser().getCustId());
			setItemTypesList(itemTypesListObj);
			getSession().setAttribute("ItemTypesList", itemTypesListObj);			

		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value="ajaxEditUpdateItems", results= {@Result (location = "ajaxUpdateItemTypes.jsp" , name="success") })
	})
	public String ajaxEditUpdateItems() throws URTUniversalException {	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditUpdateItems' method");
		}
		try{			
			List<ItemType> listObj = (List)getSession().getAttribute("ItemTypesList");
			if(null != getItemType()){
				long id = getItemType().getId();
				for(ItemType itemType : listObj){
					if(id == itemType.getId()){
						setItemType(itemType);
					}				
				}
			}		

		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}


	@Actions({
		@Action(value="ajaxUpdateItems", results= {@Result (location = "ajaxManageItemTypes.jsp" , name="success") })
	})	
	public String ajaxUpdateItemType() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateItemType' method");
		}
		try{			
			ItemType itemType = getItemType();
			if(null != itemType){
				addActionMessages(storeManager.updateItemType(getItemType(),getUser().getId()));
			}
			List<ItemType> itemTypesListObj = storeManager.getAll(ItemType.class,"custId="+getUser().getCustId());
			setItemTypesList(itemTypesListObj);
			getSession().setAttribute("ItemTypesList", itemTypesListObj);

		}catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value = "ajaxRemoveStore", results = { @Result(location = "ajaxManageStores.jsp", name = "success") }) })
	public String removeStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeStore' method");
		}
		try{		
			long storeId = getStore().getId();
			if (!ObjectFunctions.isNullOrEmpty(storeId) ) {
				storeManager.remove(StoreData.class,Long.valueOf(storeId));	
				getStoreBasicDetails();
			}super.addActionMessage("Store Deleted successfully.");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value = "ajaxRemoveItem", results = { @Result(location = "ajaxManageItemTypes.jsp", name = "success") }) })
	public String removeItemType() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveItem' method");
		}
		try{		
			long itemTypeId = getItemType().getId();
			if (!ObjectFunctions.isNullOrEmpty(itemTypeId) ) {
				storeManager.remove(ItemType.class,itemTypeId);	
				List<ItemType> itemTypesListObj = storeManager.getAll(ItemType.class, "custId="+getUser().getCustId());
				setItemTypesList(itemTypesListObj);
				getSession().setAttribute("ItemTypesList", itemTypesListObj);
			}super.addActionMessage("ItemType Deleted successfully.");
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value = "ajaxManageStoreItems", results = {@Result(location = "ajaxManageStoreItems.jsp", name = "success")})		
	})	

	public String ajaxManageStoreItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageStoreItems' method");
		}
		try{			
			getStoreDetails();
			getSession().setAttribute("storeItemsList", getStoreDataList());			
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value = "ajaxGetStoreItems", results = {@Result(location="ajaxViewItemsDetails.jsp" , name="success")})		
	})	
	public String ajaxGetStoreItems(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStoreItems' method");
		}
		try{
			List<StoreData> storesList = (List)getSession().getAttribute("storeItemsList"); 
			long id  = getStore().getId();
			setSelectedStore(id);
			if(null != storesList && storesList.size() > 0){
				List<ItemVO> itemVOList = null;
				ItemVO itemVOObj = null;
				int issuedCount = 0;
				ItemType itemType = null;
				for(StoreData storedata : storesList){
					if(id == storedata.getId()){
						itemVOList = new ArrayList<ItemVO>();
						for(Item itemObj:   storedata.getItems()){
							itemVOObj =itemObj.deepCopyEntityToVO();
							itemType = (ItemType) storeManager.get(ItemType.class,  "id ="+itemVOObj.getItemTypeId());
							if(!ObjectFunctions.isNullOrEmpty(itemType))
							itemVOObj.setItemTypeName(itemType.getTypeName());
							//get issued quantity
							issuedCount =storeManager.getIssuedItemsQuantity(itemVOObj.getItemId());
							itemVOObj.setAvailableQuantity(itemVOObj.getQuantity() - issuedCount );
							itemVOList.add(itemVOObj);
							itemVOObj = null;
							itemType = null;
							issuedCount = 0;
						}
						setItemsList(itemVOList);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value = "ajaxAddItemToStore", results = {@Result(location = "ajaxAddItem.jsp", name = "success")})		
	})
	public String ajaxAddItemToStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddItemToStore' method");
		}
		try{
			getStoreDetails();			
			setItemTypesList(storeManager.getAll(ItemType.class,"custId="+getUser().getCustId()));			
			setSuppliersList(storeManager.getAll(Supplier.class,"supplierStatus='Y' and custId="+getUser().getCustId()));			
			setItem(null);						
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({@Action(value = "ajaxUpdateItemToStore", results = {@Result(location = "ajaxUpdateItem.jsp", name = "success")})	})
	public String ajaxUpdateItemToStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateItemToStore' method");
		}
		try{		
			if(null != getItemId() && getItemId() > 0){
				setItem(storeManager.getItemDetailsById(getItemId()));
			}			
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}	

	/**
	 * 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({@Action(value = "ajaxSaveItemToStore", results = {@Result(location = "ajaxManageStoreItems.jsp", name = "success")})})
	public String ajaxSaveItemToStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveItemToStore' method");
		}
		try{
			ItemVO item = getItem();			
			if(null != item){
				addActionMessages(storeManager.addItemsToStore(item, getUser().getId(), getUser().getCustId()));				
			}
			setStoreItems(item.getStoreId());				
			setItem(null);

		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({@Action(value = "ajaxDeleteItemFromStore", results = {@Result(location = "ajaxManageStoreItems.jsp", name = "success")})})
	public String ajaxDeleteItemFromStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteItemFromStore' method");
		}
		try{
			if (!ObjectFunctions.isNullOrEmpty(getStoreId()) && !ObjectFunctions.isNullOrEmpty(getItemId()) ) {
				storeManager.remove(Item.class, getItemId());	
				setStoreItems(getStoreId());
			}
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions({
		@Action(value = "ajaxIssueItemFromStore", results = {@Result(location = "ajaxIssueItem.jsp", name = "success")})	
	})
	public String ajaxIssueItemFromStore() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxIssueItemFromStore' method");
		}
		try{
			ItemVO item = new ItemVO();
			item.setItemId(getItemId());
			item.setStoreId(getStoreId());
			item.setAvailableQuantity(getAvailableQuantity());
			item.setItemName(getItemName());
			setItem(item);
			setIssuedItems(null);			
		} catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxIssueItem", results = {@Result(location = "ajaxManageStoreItems.jsp", name = "success")})		
	})
	public String ajaxIssueItem() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxIssueItem' method");
		}
		try{		 
			ItemsIssued itemsIssued = getIssuedItems();		  
			addActionMessages(storeManager.addIssuedItems(itemsIssued,getItemId(),getUser()));
			setStoreItems(getStoreId());

		} catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}	

	@Actions({
		@Action(value = "ajaxManageSuppliers", results = {@Result(location = "ajaxManageSuppliers.jsp", name = "success")})		
	})
	public String ajaxManageSuppliers(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageSuppliers' method");
		}
		try{
			setSupplierDetailsList(storeManager.getSupplierDetails(getUser().getCustId()));
		} catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({
		@Action(value = "ajaxAddSuppliers", results = {@Result(location = "ajaxAddSupplier.jsp", name = "success")})		
	})
	public String ajaxAddSuppliers(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddSuppliers' method");
		}
		try {
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			setCountryList((List<Country>)SMSLookUpDataCache.lookUpDataMap.get(Constants.COUNTRY_LIST));

			if(getSupplier().getId() > 0){
				setSupplier((Supplier)storeManager.get(Supplier.class, getSupplier().getId()));
			} else {
				setSupplier(null);								
			}			

		} catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;

	}
	@Actions({		
		@Action(value = "ajaxSaveSuppliers", results = {@Result(location = "ajaxManageSuppliers.jsp", name = "success")})
	})
	public String ajaxSaveSuppliers(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveSuppliers' method");
		}
		try {
			Supplier supplier = getSupplier();
			if(null != supplier){
				addActionMessages(storeManager.addUpdateSupplier(supplier, getUser().getId(), getUser().getCustId())) ;				
			}
			//setSuppliersList(storeManager.getAll(Supplier.class, "supplierStatus='Y' and custId="+getUser().getCustId()));
			setSupplierDetailsList(storeManager.getSupplierDetails(getUser().getCustId()));
		} catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions({		
		@Action(value = "ajaxRemoveSupplier", results = {@Result(location = "ajaxManageSuppliers.jsp", name = "success")})
	})
	public String ajaxRemoveSupplier(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveSupplier' method");
		}
		try {
			Supplier supplier = getSupplier();			
			if (!ObjectFunctions.isNullOrEmpty(supplier) ) {
				storeManager.updateSupplierStatus(supplier.getId());	
			}
			setSupplier(null);
			//setSuppliersList(storeManager.getAll(Supplier.class, "supplierStatus='Y' and custId="+getUser().getCustId()));	
			setSupplierDetailsList(storeManager.getSupplierDetails(getUser().getCustId()));
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	public void setStoreItems(long storeId){
		if(storeId > 0){
			setSelectedStore(storeId);
			getStoreDetails();
			getSession().removeAttribute("storeItemsList");
			getSession().setAttribute("storeItemsList", getStoreDataList());
			List<ItemVO> itemVOList = null;
			ItemVO itemVOObj = null;
			int issuedCount = 0;
			ItemType itemType = null;
			for(StoreData storedata : getStoreDataList()){
				if(storeId == storedata.getId()){
					itemVOList = new ArrayList<ItemVO>();
					for(Item itemObj:   storedata.getItems()){
						itemVOObj =itemObj.deepCopyEntityToVO();
						itemType = (ItemType) storeManager.get(ItemType.class,  "id ="+itemVOObj.getItemTypeId());
						if(!ObjectFunctions.isNullOrEmpty(itemType))
						itemVOObj.setItemTypeName(itemType.getTypeName());
						//get issued quantity
						issuedCount =storeManager.getIssuedItemsQuantity(itemVOObj.getItemId());
						itemVOObj.setAvailableQuantity(itemVOObj.getQuantity() - issuedCount );
						itemVOList.add(itemVOObj);
						itemVOObj = null;
						itemType = null;
						issuedCount = 0;
					}
					setItemsList(itemVOList);
				}
			}
		}

	}

	@Actions({@Action(value = "ajaxIssuedItemsDetails", results = {@Result(location = "ajaxViewIssuedItems.jsp", name = "success")})})	
	public String ajaxIssuedItemsDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxIssuedItemsDetails' method");
		}
		try{			
			getStoreDetails();
			getSession().setAttribute("storeItemsList", getStoreDataList());			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * This Method used to get the issued items details.
	 * @return
	 */
	@Actions({@Action(value = "ajaxGetIssuedItemsDetails", results = {@Result(location="ajaxViewIssuedItemsDetails.jsp" , name="success")})	})	
	public String ajaxGetIssuedItemsDetails(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetIssuedItemsDetails' method");
		}
		try{
			long storeId  = getStore().getId();

			setIssuedItemsList(storeManager.getIssuedItemDetails(storeId));

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}


	/**
	 * 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({@Action(value = "ajaxStoreWiseDetailsReport", results = { @Result(location = "reports/ajaxStoreDetailsReport.jsp", name = "success") }) })
	public String storeWiseDetailsReport() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'storeWiseDetailsReport' method");
		}
		try{		
			getStoreDetails();
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({@Action(value = "ajaxGetStores", results = { @Result(location = "reports/ajaxGetStores.jsp", name = "success") }) })
	public String ajaxGetStores() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStores' method");
		}
		try{		
			getStoreDetails();
			setReportType(getReportType());
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxStoreReports",  results = {})})
	public String ajaxsStoreReports() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxsStoreReports' method");
		}
		if("Store Wise Details Report".equalsIgnoreCase(getReportType())){
			ajaxsStoreWiseDetailsReports() ;
		}else if("Issued Items Details Report".equalsIgnoreCase(getReportType())){
			ajaxsStoreWiseIssuedItemsDetailReports();
		}else if("Supplier Details Report".equalsIgnoreCase(getReportType())){
			ajaxsSupplierDetailReports();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({@Action(value = "ajaxGetSuppliers", results = { @Result(location = "reports/ajaxSupplierDetailsReport.jsp", name = "success") }) })
	public String ajaxGetSuppliers() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSuppliers	' method");
		}
		try{		
			List<Supplier> suppliersDetailsList = storeManager.getAll(Supplier.class, "supplierStatus='Y' and custId="+getUser().getCustId());
			setSuppliersList(suppliersDetailsList);
			setReportType(getReportType());
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}


	public String ajaxsStoreWiseDetailsReports() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxsStoreWiseDetailsReports' method");
		}
		try {
			if ("PDF".equalsIgnoreCase(getAnyId())) {

				List<ViewStoreDetails> storeDetailsList = storeManager.getAll(ViewStoreDetails.class, "custId="+getUser().getCustId() +" and storeId in ("+StringUtil.convertListToString(getChkBoxSelectedIds())+")");
				Customer customer = getCustomerByCustId();
				String fileName = "StoreWiseDetailsReport"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());;
				PDFGenerator pDFGenerator = new PDFGenerator();
				getResponse().setContentType(pDFGenerator.getMimeType());
				getResponse().setHeader("Content-Disposition","attachment; filename="+ fileName.replace(' ', '_') + ".pdf");
				pDFGenerator.createDocumentJasper();
				pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), getResponse().getOutputStream()));
				PdfHeaderFooterMarkJasper phfmj = new PdfHeaderFooterMarkJasper();
				pDFGenerator.getPdfWriter().setPageEvent(phfmj);
				pDFGenerator.getDocument().open();
				Long storeId =0L;
				int i = 0;
				String fontPath = getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+ "/Droid-Sans/DroidSans-Bold.ttf");
				FontFactory.register(fontPath);
				// creating pDF page event to set header and Footer to document
				PdfPTable mainTable = new PdfPTable(1);
				mainTable.setWidthPercentage(100);
				mainTable.setSplitLate(false);
				mainTable.getDefaultCell().setBorder(Rectangle.BOX);
				int width = 7;
				PdfPTable defaultersHeaderReport = new PdfPTable(width);
				defaultersHeaderReport.setWidthPercentage(100);
				int[] widths = { 7, 17, 17, 17, 14, 14, 14 };
				defaultersHeaderReport.setWidths(widths);
				PdfPTable defaultersHeaderReport1 = new PdfPTable(width);
				defaultersHeaderReport1.setWidthPercentage(100);
				PdfPTable headerReport = new PdfPTable(100);
				headerReport.setWidthPercentage(100);
				if (!StringFunctions.isNullOrEmpty(customer.getOrganization())) {
					headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganization().toUpperCase(), 100,fontPath, 15, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				if (!StringFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())) {
					headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganizationFullAddress().toUpperCase(), 100,fontPath, 8, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				mainTable.addCell(headerReport);
				defaultersHeaderReport1.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings("STORE WISE DETAILS REPORT".toUpperCase(),100, fontPath));
				mainTable.addCell(defaultersHeaderReport1);
				for(ViewStoreDetails storedata : storeDetailsList){

					if(storeId!=storedata.getStoreId() && !ObjectFunctions.isNullOrEmpty(storedata.getTotalQuantity()) && storedata.getTotalQuantity() > 0){
						i = 1;
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(storedata.getStoreName(),100, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("S.NO ", "#000",fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Type ", "#000",fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Name", "#000",fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Code", "#000",fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Total Quantity", "#000",fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Total Issued Quantity", "#000",fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Total Available Quantity", "#000",fontPath));
						storeId = storedata.getStoreId();
					}
					if(!ObjectFunctions.isNullOrEmpty(storedata.getTotalQuantity()) && storedata.getTotalQuantity() > 0)
					{
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+i,"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(storedata.getItemType(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(storedata.getItemName(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(storedata.getItemCode(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+storedata.getTotalQuantity(),"#000", fontPath));
						if(!ObjectFunctions.isNullOrEmpty(storedata.getIssuedQuantity())){
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+storedata.getIssuedQuantity() ,"#000", fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+(storedata.getTotalQuantity()-storedata.getIssuedQuantity()),"#000", fontPath));
						}else{
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2("0" ,"#000", fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+storedata.getTotalQuantity(),"#000", fontPath));
						}
						i++;
					}
					else{
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(storedata.getStoreName(),100, fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingJasper2("Currently Itesm are not added into Store.",7, fontPath));
					}
				}
				mainTable.addCell(defaultersHeaderReport);
				pDFGenerator.getDocument().add(mainTable);
				pDFGenerator.getDocument().close();
				pDFGenerator = null;
				mainTable = null;
				defaultersHeaderReport = null;
				phfmj = null;
				storeDetailsList = null;
			}else if ("Excel".equalsIgnoreCase(getAnyId())){
				List<ViewStoreDetails> storeDetailsList = storeManager.getAll(ViewStoreDetails.class, "custId="+getUser().getCustId() +" and storeId in ("+StringUtil.convertListToString(getChkBoxSelectedIds())+")");
				String fileName = "StoreWiseDetailsReport "+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				ExcelView excelView = new ExcelView();
				WritableCellFormat cellFormat =null;
				getResponse().setContentType(excelView.getMimeType());
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
				WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, false);
				font1.setColour(Colour.WHITE);
				cellFormat=ExcelView.getUserFormattedCell(font1, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL,BorderLineStyle.NONE);
				int rowCount=0;
				rowCount=8;
				excelView.setWorkSheetName(" Store_Wise_Details");
				excelView.createWorkSheet(0);
				excelView.setDefaultFormat(excelView.getArial10format());
				schoolAddresDetailsOnlyForExcel(excelView,6); 
				excelView.getWs().removeRow(0);
				excelView.getWs().setColumnView(0,6);
				excelView.getWs().setColumnView(1,30);
				excelView.getWs().setColumnView(2,30);
				excelView.getWs().setColumnView(3,30);
				excelView.getWs().setColumnView(4,25);
				excelView.getWs().setColumnView(5,25);
				excelView.getWs().setColumnView(6,25);
				excelView.getWs().mergeCells(0, 6, 6, 6);
				excelView.getWs().addCell(new Label(0,6,  "STORE WISE DETAILS REPORT", cellFormat));
				excelView.getWs().addCell(new Label(0,7, "S.NO", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(1,7, "Item Type", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(2,7, "Item Name", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(3,7, "Item Code", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(4,7, "Total Quantity", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(5,7, "Total Issued Quantity", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(6,7, "Total Available Quantity", excelView.getUsermore10BoldformatGreenBgClr()));
				int i =0;
				Long storeId =0L;
				if(!ObjectFunctions.isNullOrEmpty(storeDetailsList)){		
					for(ViewStoreDetails storedata : storeDetailsList){
						if(storeId!=storedata.getStoreId() ){
							i = 1;
							if(storeId>0){
								rowCount+=1;
							}
							excelView.getWs().mergeCells(0, rowCount, 6, rowCount);
							excelView.getWs().addCell(new Label(0,rowCount, storedata.getStoreName().toUpperCase(), cellFormat));
							storeId = storedata.getStoreId();
						}
						if(!ObjectFunctions.isNullOrEmpty(storedata.getTotalQuantity()) && storedata.getTotalQuantity() > 0)
						{
							rowCount+=1;
							excelView.getWs().addCell(new Label(0,rowCount, String.valueOf(i), excelView.getDefaultFormat()));
							excelView.getWs().addCell(new Label(1,rowCount, storedata.getItemType(), excelView.getDefaultFormat()));
							excelView.getWs().addCell(new Label(2,rowCount, storedata.getItemName(), excelView.getDefaultFormat()));
							excelView.getWs().addCell(new Label(3,rowCount, storedata.getItemCode(), excelView.getDefaultFormat()));
							excelView.getWs().addCell(new Label(4,rowCount, String.valueOf(storedata.getTotalQuantity()), excelView.getDefaultFormat()));
							if(!ObjectFunctions.isNullOrEmpty(storedata.getIssuedQuantity())){
								excelView.getWs().addCell(new Label(5,rowCount,String.valueOf(storedata.getIssuedQuantity()) ,excelView.getDefaultFormat()));
								excelView.getWs().addCell(new Label(6,rowCount, String.valueOf((storedata.getTotalQuantity()-storedata.getIssuedQuantity())),excelView.getDefaultFormat()));
							}else{
								excelView.getWs().addCell(new Label(5,rowCount, "0",excelView.getDefaultFormat()));
								excelView.getWs().addCell(new Label(6,rowCount, String.valueOf(storedata.getTotalQuantity()),excelView.getDefaultFormat()));
							}
							i+=1;
						}
						else{
							rowCount+=1;
							excelView.getWs().mergeCells(0, rowCount,6, rowCount);
							excelView.getWs().addCell(new Label(0,rowCount, "Currently Itesm are not added into Store.", excelView.getDefaultFormat()));
						}
					}
				}
				showSchoolUrlInExcelSheetFooter(rowCount+1,excelView,6);
				excelView.getWb().write();
				excelView.getWb().close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;

	}


	public String ajaxsStoreWiseIssuedItemsDetailReports() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxsStoreWiseIssuedItemsDetailReports' method");
		}
		try {
			if ("PDF".equalsIgnoreCase(getAnyId())) {
				List<ViewIssuedItemDetails> issuedItemDetailsList = storeManager.getAll(ViewIssuedItemDetails.class, "custId="+getUser().getCustId() +" and storeId in ("+StringUtil.convertListToString(getChkBoxSelectedIds())+")");
				Customer customer = getCustomerByCustId();
				String fileName = "IssuedItemsDetailsReport"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());;
				PDFGenerator pDFGenerator = new PDFGenerator();
				getResponse().setContentType(pDFGenerator.getMimeType());
				getResponse().setHeader("Content-Disposition","attachment; filename="+ fileName.replace(' ', '_') + ".pdf");
				pDFGenerator.createDocumentJasper();
				pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), getResponse().getOutputStream()));
				PdfHeaderFooterMarkJasper phfmj = new PdfHeaderFooterMarkJasper();
				pDFGenerator.getPdfWriter().setPageEvent(phfmj);
				pDFGenerator.getDocument().open();
				Long storeId =0L;
				int i = 0;
				String fontPath = getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+ "/Droid-Sans/DroidSans-Bold.ttf");
				FontFactory.register(fontPath);
				// creating pDF page event to set header and Footer to document
				PdfPTable mainTable = new PdfPTable(1);
				mainTable.setWidthPercentage(100);
				mainTable.setSplitLate(false);
				mainTable.getDefaultCell().setBorder(Rectangle.BOX);
				int width = 8;
				PdfPTable defaultersHeaderReport = new PdfPTable(width);
				defaultersHeaderReport.setWidthPercentage(100);
				int[] widths = { 5, 15, 15, 15, 15, 12, 12,11 };
				defaultersHeaderReport.setWidths(widths);
				PdfPTable defaultersHeaderReport1 = new PdfPTable(width);
				defaultersHeaderReport1.setWidthPercentage(100);
				PdfPTable headerReport = new PdfPTable(100);
				headerReport.setWidthPercentage(100);
				if (!StringFunctions.isNullOrEmpty(customer.getOrganization())) {
					headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganization().toUpperCase(), 100,fontPath, 15, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				if (!StringFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())) {
					headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganizationFullAddress().toUpperCase(), 100,fontPath, 8, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				mainTable.addCell(headerReport);
				defaultersHeaderReport1.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings("ISSUED ITEMS DETAILS REPORT".toUpperCase(),100, fontPath));
				mainTable.addCell(defaultersHeaderReport1);
				if(!ObjectFunctions.isNullOrEmpty(issuedItemDetailsList)){
					for(ViewIssuedItemDetails issuedItemDetails : issuedItemDetailsList){
						if(storeId!=issuedItemDetails.getStoreId()){
							i = 1;
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(issuedItemDetails.getStoreName(),100, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("S.NO ", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Type ", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Name", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Code", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Issued To", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Issued Date", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Issued Quantity", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Issued By", "#000",fontPath));
							storeId = issuedItemDetails.getStoreId();
						}
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+i,"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(issuedItemDetails.getItemType(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(issuedItemDetails.getItemName(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(issuedItemDetails.getItemCode(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+issuedItemDetails.getIssuedTo(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2( DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, issuedItemDetails.getIssuedDate()),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+issuedItemDetails.getIssuedQuantity(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+issuedItemDetails.getIssuedBy(),"#000", fontPath));

						i++;
					}		
				}else{
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingJasper2("Currently Items are not issued from this Store.",7, fontPath));
				}
				mainTable.addCell(defaultersHeaderReport);
				pDFGenerator.getDocument().add(mainTable);
				pDFGenerator.getDocument().close();
				pDFGenerator = null;
				mainTable = null;
				defaultersHeaderReport = null;
				phfmj = null;
			}else if ("Excel".equalsIgnoreCase(getAnyId())){
				List<ViewIssuedItemDetails> issuedItemDetailsList = storeManager.getAll(ViewIssuedItemDetails.class, "custId="+getUser().getCustId() +" and storeId in ("+StringUtil.convertListToString(getChkBoxSelectedIds())+")");
				String fileName = "IssuedItemsDetailsReport "+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				ExcelView excelView = new ExcelView();
				WritableCellFormat cellFormat =null;
				getResponse().setContentType(excelView.getMimeType());
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
			//	WritableFont font = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
				WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, false);
				font1.setColour(Colour.WHITE);
				cellFormat=ExcelView.getUserFormattedCell(font1, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL,BorderLineStyle.NONE);
				//cellFormat=ExcelView.getUserFormattedCell(font, Colour.AQUA, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL,BorderLineStyle.NONE);
				int rowCount=0;
				excelView.setWorkSheetName("Issued_Items_Details");
				excelView.createWorkSheet(0);
				excelView.setDefaultFormat(excelView.getArial10format());
				schoolAddresDetailsOnlyForExcel(excelView,7); 
				excelView.getWs().removeRow(0);
				excelView.getWs().setColumnView(0,6);
				excelView.getWs().setColumnView(1,30);
				excelView.getWs().setColumnView(2,30);
				excelView.getWs().setColumnView(3,30);
				excelView.getWs().setColumnView(4,25);
				excelView.getWs().setColumnView(5,25);
				excelView.getWs().setColumnView(6,25);
				excelView.getWs().setColumnView(7,25);
				excelView.getWs().mergeCells(0, 6, 7, 6);
				excelView.getWs().addCell(new Label(0,6,  "ISSUED ITEMS DETALS REPORT", cellFormat));
				if(!ObjectFunctions.isNullOrEmpty(issuedItemDetailsList)){	
					excelView.getWs().addCell(new Label(0,7, "S.NO", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(1,7, "Item Type", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(2,7, "Item Name", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(3,7, "Item Code", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(4,7, "Issued To", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(5,7, "Issued Date", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(6,7, "Issued Quantity", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(7,7, "Issued By", excelView.getUsermore10BoldformatGreenBgClr()));
					int i =0;
					Long storeId =0L;
					rowCount=8;
					for(ViewIssuedItemDetails issuedItemDetails : issuedItemDetailsList){
						if(storeId!=issuedItemDetails.getStoreId() ){
							i = 1;
							if(storeId>0){
								rowCount+=1;
							}
							excelView.getWs().mergeCells(0, rowCount, 7, rowCount);
							excelView.getWs().addCell(new Label(0,rowCount, issuedItemDetails.getStoreName().toUpperCase(), cellFormat));
							storeId = issuedItemDetails.getStoreId();
						}
						rowCount+=1;
						excelView.getWs().addCell(new Label(0,rowCount, String.valueOf(i), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(1,rowCount, issuedItemDetails.getItemType(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(2,rowCount, issuedItemDetails.getItemName(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(3,rowCount, issuedItemDetails.getItemCode(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(4,rowCount, issuedItemDetails.getIssuedTo(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(5,rowCount,DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, issuedItemDetails.getIssuedDate()) ,excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(6,rowCount, String.valueOf(issuedItemDetails.getIssuedQuantity()),excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(7,rowCount, issuedItemDetails.getIssuedBy(),excelView.getDefaultFormat()));
						i+=1;
					}
				}else{
					rowCount+=8;
					excelView.getWs().mergeCells(0, rowCount,7, rowCount);
					excelView.getWs().addCell(new Label(0,rowCount, "Currently Items are not issued from this Store.", excelView.getDefaultFormat()));
				}
				showSchoolUrlInExcelSheetFooter(rowCount+1,excelView,7);
				excelView.getWb().write();
				excelView.getWb().close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}


	public String ajaxsSupplierDetailReports() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxsSupplierDetailReports' method");
		}
		try {
			if ("PDF".equalsIgnoreCase(getAnyId())) {
				List<ViewSupplierDetails> supplierDetailsList = storeManager.getAll(ViewSupplierDetails.class, "custId="+getUser().getCustId() +" and supplierId in ("+StringUtil.convertListToString(getChkBoxSelectedIds())+")");
				Customer customer = getCustomerByCustId();
				String fileName = "supplierDetailsReport"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());;
				PDFGenerator pDFGenerator = new PDFGenerator();
				getResponse().setContentType(pDFGenerator.getMimeType());
				getResponse().setHeader("Content-Disposition","attachment; filename="+ fileName.replace(' ', '_') + ".pdf");
				pDFGenerator.createDocumentJasper();
				pDFGenerator.setPdfWriter(PdfWriter.getInstance(pDFGenerator.getDocument(), getResponse().getOutputStream()));
				PdfHeaderFooterMarkJasper phfmj = new PdfHeaderFooterMarkJasper();
				pDFGenerator.getPdfWriter().setPageEvent(phfmj);
				pDFGenerator.getDocument().open();
				Long supplierId =0L;
				int i = 0;
				String fontPath = getSession().getServletContext().getRealPath(getText(Constants.GILITE_FILE_DOCS_DIR)+ "/Droid-Sans/DroidSans-Bold.ttf");
				FontFactory.register(fontPath);
				// creating pDF page event to set header and Footer to document
				PdfPTable mainTable = new PdfPTable(1);
				mainTable.setWidthPercentage(100);
				mainTable.setSplitLate(false);
				mainTable.getDefaultCell().setBorder(Rectangle.BOX);
				int width = 8;
				PdfPTable defaultersHeaderReport = new PdfPTable(width);
				defaultersHeaderReport.setWidthPercentage(100);
				int[] widths = { 5, 15, 15, 15, 15, 12, 12,11 };
				defaultersHeaderReport.setWidths(widths);
				PdfPTable defaultersHeaderReport1 = new PdfPTable(width);
				defaultersHeaderReport1.setWidthPercentage(100);
				PdfPTable headerReport = new PdfPTable(100);
				headerReport.setWidthPercentage(100);
				if (!StringFunctions.isNullOrEmpty(customer.getOrganization())) {
					headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganization().toUpperCase(), 100,fontPath, 15, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				if (!StringFunctions.isNullOrEmpty(customer.getOrganizationFullAddress())) {
					headerReport.addCell(PDFGenerator.getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(customer.getOrganizationFullAddress().toUpperCase(), 100,fontPath, 8, "#005CB9",Element.ALIGN_CENTER, 5.0f));
				}
				mainTable.addCell(headerReport);
				defaultersHeaderReport1.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings("SUPPLIER WISE ITEMS RECEIVED DETAILS REPORT".toUpperCase(),100, fontPath));
				mainTable.addCell(defaultersHeaderReport1);
				if(!ObjectFunctions.isNullOrEmpty(supplierDetailsList)){
					for(ViewSupplierDetails supplierDetails : supplierDetailsList){
						if(supplierId!=supplierDetails.getSupplierId()){
							i = 1;
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadings(supplierDetails.getSupplierName(),100, fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("S.NO ", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Store Name ", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Type ", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Name", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Item Code", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Quantity", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Received Date", "#000",fontPath));
							defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter("Price", "#000",fontPath));
							supplierId = supplierDetails.getStoreId();
						}
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+i,"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(supplierDetails.getStoreName(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(supplierDetails.getItemType(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(supplierDetails.getItemName(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignLeftNormalFont(supplierDetails.getItemCode(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+supplierDetails.getQuantity(),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2( DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, supplierDetails.getReceivedDate()),"#000", fontPath));
						defaultersHeaderReport.addCell(PDFGenerator.getPdfCellAlignCenter2(""+supplierDetails.getPrice(),"#000", fontPath));
						i++;
					}		
				}else{
					defaultersHeaderReport.addCell(PDFGenerator.getPdfCellWithCenterAlignHeadingJasper2("Currently Items are not supplied by suplier.",8, fontPath));
				}
				mainTable.addCell(defaultersHeaderReport);
				pDFGenerator.getDocument().add(mainTable);
				pDFGenerator.getDocument().close();
				pDFGenerator = null;
				mainTable = null;
				defaultersHeaderReport = null;
				phfmj = null;
			}else if ("Excel".equalsIgnoreCase(getAnyId())){
				List<ViewSupplierDetails> supplierDetailsList = storeManager.getAll(ViewSupplierDetails.class, "custId="+getUser().getCustId() +" and supplierId in ("+StringUtil.convertListToString(getChkBoxSelectedIds())+")");
				String fileName = "supplierDetailsReport "+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
				ExcelView excelView = new ExcelView();
				WritableCellFormat cellFormat =null;
				getResponse().setContentType(excelView.getMimeType());
				getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
				excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
				WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, false);
				font1.setColour(Colour.WHITE);
				cellFormat=ExcelView.getUserFormattedCell(font1, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL,BorderLineStyle.NONE);
				int rowCount=0;
				excelView.setWorkSheetName("Supplier_Details_Report");
				excelView.createWorkSheet(0);
				excelView.setDefaultFormat(excelView.getArial10format());
				schoolAddresDetailsOnlyForExcel(excelView,7); 
				excelView.getWs().removeRow(0);
				excelView.getWs().setColumnView(0,6);
				excelView.getWs().setColumnView(1,30);
				excelView.getWs().setColumnView(2,30);
				excelView.getWs().setColumnView(3,30);
				excelView.getWs().setColumnView(4,25);
				excelView.getWs().setColumnView(5,25);
				excelView.getWs().setColumnView(6,25);
				excelView.getWs().setColumnView(7,25);
				excelView.getWs().mergeCells(0, 6, 7, 6);
				excelView.getWs().addCell(new Label(0,6,  "SUPPLIER WISE ITEMS RECEIVED DETAILS REPORT", cellFormat));
				if(!ObjectFunctions.isNullOrEmpty(supplierDetailsList)){	
					excelView.getWs().addCell(new Label(0,7, "S.NO", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(1,7, "Store Name", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(2,7, "Item Type", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(3,7, "Item Name", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(4,7, "Item Code", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(5,7, "Quantity", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(6,7, "Quantity Date", excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(7,7, "Price", excelView.getUsermore10BoldformatGreenBgClr()));
					int i =0;
					rowCount=8;
					Long supplierId =0L;	
					for(ViewSupplierDetails supplierDetails : supplierDetailsList){
						if(supplierId!=supplierDetails.getSupplierId()){
							i = 1;
							if(supplierId>0){
								rowCount+=1;
							}
							excelView.getWs().mergeCells(0, rowCount, 7, rowCount);
							excelView.getWs().addCell(new Label(0,rowCount, supplierDetails.getSupplierName().toUpperCase(), cellFormat));
							supplierId = supplierDetails.getSupplierId();
						}
						rowCount+=1;
						excelView.getWs().addCell(new Label(0,rowCount, String.valueOf(i), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(1,rowCount, supplierDetails.getStoreName(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(2,rowCount, supplierDetails.getItemType(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(3,rowCount, supplierDetails.getItemName(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(4,rowCount, supplierDetails.getItemCode(), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(5,rowCount, String.valueOf(supplierDetails.getQuantity()), excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(6,rowCount,DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, supplierDetails.getReceivedDate()) ,excelView.getDefaultFormat()));
						excelView.getWs().addCell(new Label(7,rowCount, String.valueOf(supplierDetails.getPrice()),excelView.getDefaultFormat()));
						i+=1;
					}
				}else{
					rowCount+=8;
					excelView.getWs().mergeCells(0, rowCount,7, rowCount);
					excelView.getWs().addCell(new Label(0,rowCount, "Currently Items are not supplied by suplier.", excelView.getDefaultFormat()));
				}
				showSchoolUrlInExcelSheetFooter(rowCount+1,excelView,7);
				excelView.getWb().write();
				excelView.getWb().close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return null;
	}
	/**
	 * @return the supplierDetailsList
	 */
	public List<SupplierVO> getSupplierDetailsList() {
		return supplierDetailsList;
	}
	/**
	 * @param supplierDetailsList the supplierDetailsList to set
	 */
	public void setSupplierDetailsList(List<SupplierVO> supplierDetailsList) {
		this.supplierDetailsList = supplierDetailsList;
	}
	/**
	 * @return the itemName
	 */
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
	 * This method used to storekeeper login to show the dashboard.
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({
   		@Action(value = "storeKeeperDashboard", results = { @Result(location = "ajaxManageStores.jsp", name = "success" )})
   	})
   		public String clerkStaffDashboard() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'storeKeeperDashboard' method");
   		}
   		try {	
   			getStoreBasicDetails();
   			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showTaskReminder")))
				getTaskReminderToUserLogin();
   		}
   		catch(Exception ex)
   		{
   			log.error("Entering into 'catch block':"+ex.getMessage());
   			ex.printStackTrace();
   		}
   		return SUCCESS;
   	}
    /**
     * This method retuns all store details with respective to user login.
     */
	private void getStoreDetails()
	{
		List<StoreData> storesList = null;
		if(getUser().isStoreKeeper()){
		 storesList = storeManager.getAll(StoreData.class,"custId="+getUser().getCustId() +" and storeKeeperAccountId ="+getUser().getId());
		}else{
			 storesList = storeManager.getAll(StoreData.class,"custId="+getUser().getCustId());
		}
		setStoreDataList(storesList);	
	}
	/**
	 * @return the storeBasicDetailsList
	 */
	public List<ViewStoreBasicDetails> getStoreBasicDetailsList() {
		return storeBasicDetailsList;
	}
	/**
	 * @param storeBasicDetailsList the storeBasicDetailsList to set
	 */
	public void setStoreBasicDetailsList(
			List<ViewStoreBasicDetails> storeBasicDetailsList) {
		this.storeBasicDetailsList = storeBasicDetailsList;
	}
	/**
	 * This method retuns all store basic information with respective to user login.
	 */
	private void getStoreBasicDetails()
	{
		List<ViewStoreBasicDetails> storesList = null;
		if(getUser().isStoreKeeper()){
		 storesList = storeManager.getAll(ViewStoreBasicDetails.class,"custId="+getUser().getCustId() +" and accountId ="+getUser().getId());
		}else{
			 storesList = storeManager.getAll(ViewStoreBasicDetails.class,"custId="+getUser().getCustId());
		}
		setStoreBasicDetailsList(storesList);	
	}
}
