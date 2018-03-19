package com.hyniva.sms.ws.vo.transport;


/*
 * @create new table customer.
 */
public class StudentTransportDetailsVo{
	
    
    private long id;
    private long studentId;
    private long pickupBoardingPointId;
    private long dropBoardingPointId;
    private long pickupVehicleId;//VehiclesAcademicDetails
    private long dropVehicleId;//VehiclesAcademicDetails
    private long schoolTermId;
    private String boardingType;
    private long feeTypeId;
	
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getPickupBoardingPointId() {
		return pickupBoardingPointId;
	}
	public void setPickupBoardingPointId(long pickupBoardingPointId) {
		this.pickupBoardingPointId = pickupBoardingPointId;
	}
	public long getDropBoardingPointId() {
		return dropBoardingPointId;
	}
	public void setDropBoardingPointId(long dropBoardingPointId) {
		this.dropBoardingPointId = dropBoardingPointId;
	}
	public long getPickupVehicleId() {
		return pickupVehicleId;
	}
	public void setPickupVehicleId(long pickupVehicleId) {
		this.pickupVehicleId = pickupVehicleId;
	}
	public long getDropVehicleId() {
		return dropVehicleId;
	}
	public void setDropVehicleId(long dropVehicleId) {
		this.dropVehicleId = dropVehicleId;
	}
	public long getSchoolTermId() {
		return schoolTermId;
	}
	public void setSchoolTermId(long schoolTermId) {
		this.schoolTermId = schoolTermId;
	}
	public String getBoardingType() {
		return boardingType;
	}
	public void setBoardingType(String boardingType) {
		this.boardingType = boardingType;
	}
	public long getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
}