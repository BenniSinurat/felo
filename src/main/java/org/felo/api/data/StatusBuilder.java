package org.felo.api.data;

public abstract class StatusBuilder {

	public static ResponseStatus getStatus(String arg) {
		try {
			ResponseStatus rs = StatusBuilder.getStatus(Status.valueOf(arg));
			return rs;
		} catch (IllegalArgumentException e) {
			return new ResponseStatus("E99", "UNDEFINED_ERROR", "Undefined Error");
		}
	}

	public static String getHttpStatus(String arg) {
		String rc = Status.valueOf(arg).httpStatusCode();
		return rc;
	}

	public static ResponseStatus getStatus(Status arg) {
		switch (arg) {
		case PROCESSED:
			return new ResponseStatus("A00", "PROCESSED", "Transaction completed");
		case UNAUTHORIZED_ACCESS:
			return new ResponseStatus("A90", "UNAUTHORIZED_ACCESS",
					"We cannot authorized you, please check your authorization");
		case SERVICE_NOT_ALLOWED:
			return new ResponseStatus("A16", "SERVICE_NOT_ALLOWED", "Group not allowed to access Webservices");
		case INVALID:
			return new ResponseStatus("A14", "INVALID", "Invalid credential");
		case VALID:
			return new ResponseStatus("A01", "VALID", "Credential Valid");
		case BLOCKED:
			return new ResponseStatus("A16", "BLOCKED", "Credential blocked");
		case UNAUTHORIZED_MEMBER_ACCESS:
			return new ResponseStatus("M16", "UNAUTHORIZED_MEMBER_ACCESS", "You don't have access to specified member");
		case MEMBER_NOT_FOUND:
			return new ResponseStatus("M14", "MEMBER_NOT_FOUND", "Member not found on system");
		case MEMBER_ALREADY_REGISTERED:
			return new ResponseStatus("M14", "MEMBER_ALREADY_REGISTERED", "Member already registered on system");
		case DESTINATION_MEMBER_NOT_FOUND:
			return new ResponseStatus("M15", "DESTINATION_MEMBER_NOT_FOUND", "Destination member not found on system");
		case INVALID_ACCOUNT:
			return new ResponseStatus("S14", "INVALID_ACCOUNT", "Invalid source account/permission not allowed");
		case INVALID_FEE_ACCOUNT:
			return new ResponseStatus("S16", "INVALID_FEE_ACCOUNT",
					"Invalid fee source account/permission not allowed");
		case INVALID_DESTINATION_ACCOUNT:
			return new ResponseStatus("S15", "INVALID_DESTINATION_ACCOUNT",
					"Invalid destination account/permission not allowed");
		case INVALID_FEE_DESTINATION_ACCOUNT:
			return new ResponseStatus("S17", "INVALID_FEE_DESTINATION_ACCOUNT",
					"Invalid fee destination account/permission not allowed");
		case INSUFFICIENT_BALANCE:
			return new ResponseStatus("S22", "INSUFFICIENT_BALANCE",
					"You dont have enough balance to process this transaction");
		case CREDIT_LIMIT_REACHED:
			return new ResponseStatus("S40", "CREDIT_LIMIT_REACHED", "Your monthly account limit has reached");
		case DESTINATION_CREDIT_LIMIT_REACHED:
			return new ResponseStatus("S41", "DESTINATION_CREDIT_LIMIT_REACHED",
					"The destination monthly limit has reached");
		case INVALID_TRANSFER_TYPE:
			return new ResponseStatus("T14", "INVALID_TRANSFER_TYPE", "Invalid transfer type ID");
		case TRANSACTION_AMOUNT_BELOW_LIMIT:
			return new ResponseStatus("T16", "TRANSACTION_AMOUNT_BELOW_LIMIT",
					"Transaction amount is below the threshold limit");
		case TRANSACTION_AMOUNT_ABOVE_LIMIT:
			return new ResponseStatus("T18", "TRANSACTION_AMOUNT_ABOVE_LIMIT",
					"Transaction amount is above the threshold limit");
		case DUPLICATE_TRANSACTION:
			return new ResponseStatus("P16", "DUPLICATE_TRANSACTION", "Duplicate transaction entry");
		case INVALID_PARAMETER:
			return new ResponseStatus("P14", "INVALID_PARAMETER", "Invalid request parameter");
		case SESSION_EXPIRED:
			return new ResponseStatus("L17", "SESSION_EXPIRED", "Session token is already expired");
		case INVALID_SIGNATURE:
			return new ResponseStatus("L21", "INVALID_SIGNATURE", "Invalid message signature");
		case INVALID_URL:
			return new ResponseStatus("B21", "INVALID_URL", "Invalid URL");
		case PAYMENT_CODE_NOT_FOUND:
			return new ResponseStatus("V14", "PAYMENT_CODE_NOT_FOUND",
					"The specified Payment Code already expired or not found");
		case SUCCESS:
			return new ResponseStatus("TO00", "SUCCESS", "Success");
		case TOKEN_EXPIRED:
			return new ResponseStatus("TO01", "TOKEN_EXPIRED", "Token Expired");
		case ACCESS_VIOLATION:
			return new ResponseStatus("TO02", "ACCESS_VIOLATION", "Access Violation");
		case NOT_FOUND:
			return new ResponseStatus("TO03", "NOT_FOUND", "Data not found");
		case NO_TRANSACTION:
			return new ResponseStatus("S84", "NO_TRANSACTION", "No transaction found for the specified account");
		case INVALID_OTP:
			return new ResponseStatus("T004", "INVALID_OTP", "Invalid OTP");
		case DUPLICATE_COMMUNITY:
			return new ResponseStatus("T004", "DUPLICATE_COMMUNITY", "Community name has been registered");
		case UNKNOWN_ERROR:
			return new ResponseStatus("E99", "UNKNOWN_ERROR", "Unknown Error");
		case PIN_ERROR:
			return new ResponseStatus("A14", "PIN_ERROR", "PIN Error");
		case FAILED:
			return new ResponseStatus("E99", "FAILED", "Failed");
		case OTP_VALIDATION_FAILED:
			return new ResponseStatus("T005", "OTP_VALIDATION_FAILED", "OTP Validation Failed");
		case INVALID_TO_MEMBER:
			return new ResponseStatus("T006", "INVALID_TO_MEMBER", "Invalid destination account, you can not transfer to your own account");
		case ACCOUNT_NOT_FOUND:
			return new ResponseStatus("S14", "ACCOUNT_NOT_FOUND", "Specified account not found");
		case BANK_NOT_FOUND:
			return new ResponseStatus("I14", "BANK_NOT_FOUND", "The specified Bank not found");
		default:
			return new ResponseStatus("E99", "GENERAL_ERROR", "General Error");
		}
	}
}
