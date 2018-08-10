package org.felo.api.data;

public enum Status {
	PROCESSED("200"), 
	UNAUTHORIZED_ACCESS("401"), 
	SERVICE_NOT_ALLOWED("403"), 
	VALID("200"), 
	INVALID("400"), 
	BLOCKED("403"), 
	MEMBER_NOT_FOUND("404"),
	MEMBER_ALREADY_REGISTERED("444"),
	UNAUTHORIZED_MEMBER_ACCESS("403"), 
	DESTINATION_MEMBER_NOT_FOUND("404"), 
	INVALID_ACCOUNT("404"), 
	INVALID_TRANSFER_TYPE("404"), 
	TRANSACTION_AMOUNT_BELOW_LIMIT("504"), 
	TRANSACTION_AMOUNT_ABOVE_LIMIT("504"), 
	DUPLICATE_TRANSACTION("409"), 
	INVALID_PARAMETER("400"), 
	INVALID_FEE_ACCOUNT("404"), 
	INVALID_DESTINATION_ACCOUNT("404"), 
	INVALID_FEE_DESTINATION_ACCOUNT("404"), 
	INSUFFICIENT_BALANCE("503"), 
	CREDIT_LIMIT_REACHED("504"), 
	DESTINATION_CREDIT_LIMIT_REACHED("504"), 
	SESSION_EXPIRED("440"), 
	INVALID_SIGNATURE("498"), 
	INVALID_URL("404"),
	UNDEFINED_ERROR("404"),
	HOST_CONNECTION_TIMEOUT("599"),
	HOST_CONNECTION_FAILED("598"),
	PAYMENT_CODE_NOT_FOUND("404"),
	SUCCESS("200"),
	TOKEN_EXPIRED("440"),
	ACCESS_VIOLATION("401"),
	NOT_FOUND("404"),
	NO_TRANSACTION("404"),
	UNKNOWN_ERROR("400"),
	INVALID_OTP("400"),
	DUPLICATE_COMMUNITY("409"),
	PIN_ERROR("400"),
	FAILED("400"),
	OTP_VALIDATION_FAILED("400");
	
	private String httpStatusCode;
	
	Status(String httpStatusCode){
		this.httpStatusCode = httpStatusCode;
	}
	
	public String httpStatusCode(){
		return httpStatusCode;
	}
}
