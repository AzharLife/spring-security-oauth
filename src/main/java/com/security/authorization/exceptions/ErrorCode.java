package com.security.authorization.exceptions;

public enum ErrorCode implements ErrorHandle {
	SCF_VD_0001(1, "Validation Error"), 
	SCF_VD_0002(2, "Famer Not Found"),
	SCF_VD_0003(3, "Internal Server Error"), 
	SCF_VD_004(4,"Mobile Number is already registered"), 
	SCF_VD_005(5,"National ID is already registered"), 
	SCF_VD_006(6,"Postal code and country code did not match"),
	SCF_VD_007(7, "Country code is not valid"), 
	SCF_VD_008(8,"Database exception occured while saving the farmer details"), 
	SCF_VD_009(9, "State Not found"), 
	SCF_VD_010(10, "Country Not found"),
	SCF_VD_011(11,"State Id is not valid"),
	SCF_VD_012(12,"City Id is not valid"),
	SCF_VD_013(13,"Postal code is not valid"),
	SCF_VD_014(14,"Farm Country code is not valid"),
	SCF_VD_015(15,"Farm State Id is not valid"),
	SCF_VD_016(16,"Farm City Id is not valid"),
	SCF_VD_017(17,"Farm Postal code is not valid"),
	SCF_VD_100(100,"Access denied"),
	SCF_VD_018(18,"Invalid farmer Id"), SCF_VD_019(19, "No Authority Name Found");

	private final int errorCode;
	private final String message;

	ErrorCode(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	@Override
	public int getErrorCode() {
		// TODO Auto-generated method stub
		return this.errorCode;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

}
