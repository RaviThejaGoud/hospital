package com.urt.model.cc;

import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.string.StringFunctions;

/**
 * This class represents a credit card
 */
public class CreditCard implements java.io.Serializable {

	protected final Log log = LogFactory.getLog(CreditCard.class);
	private String cardNo = "";
	private String cardType = "";
	private String nameOnCard="";
	private String firstName="";
	private String lastName="";
	private String cvv2 = "";
	private String expiryMonth;
	private String expiryYear;
	private Calendar expiryDate;

	public static final int INVALID = -1;
	public static final int VISA = 0;
	public static final int MASTERCARD = 1;
	public static final int AMERICAN_EXPRESS = 2;
	public static final int EN_ROUTE = 3;
	public static final int DINERS_CLUB = 4;

	private static final String[] cardNames = { "Visa", "Mastercard",
			"American Express", "En Route", "Diner's CLub/Carte Blanche", };

	/**
	 * default constructor
	 */
	public CreditCard() {
		cardNo = new String();
		cardType = new String();
		expiryDate = Calendar.getInstance();
	}

	/*
	 * @param expiryDateString is mm/dd/yyyy
	 */
	public CreditCard(String cardNo, String cardType, Calendar expiryDate) {
		this.cardNo = cardNo;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
	}

	/*
	 * @param expiryDateString is mm/dd/yyyy
	 */
	public CreditCard(String cardNo, String cardType, String expiryDateString) {
		this.cardNo = cardNo;
		this.cardType = cardType;
		expiryDate = getCreditCardExpiryDate(expiryDateString);
	}

	// get methods for the instance variables

	public String getCardNo() {
		return cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public String getExpiryMonthString() {
		if (expiryDate == null) {
			return "";
		} else {
			int month = getExpiryDate().get(java.util.Calendar.MONTH) + 1;
			return (month < 10 ? "0" : "") + month + "";
		}
	}

	public String getExpiryYearString() {
		return (expiryDate == null) ? "" : getExpiryDate().get(
				java.util.Calendar.YEAR)
				+ "";
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDateString
	 *            is mm/dd/yyyy
	 */
	private Calendar getCreditCardExpiryDate(String expiryDateString) {

		Calendar expiryDate = Calendar.getInstance();
		expiryDate.clear();
		int month = 0;
		int year = 0;

		try {
			if (expiryDateString != null) {
				StringTokenizer strTok = new StringTokenizer(expiryDateString,
						"/");

				if (strTok.countTokens() == 0) {
					throw new Exception("CreditCard Date Format Error: "
							+ expiryDateString);
				} else if (strTok.countTokens() == 2) {

					month = Integer.parseInt(strTok.nextToken());
					year = Integer.parseInt(strTok.nextToken());
					expiryDate.set(Calendar.MONTH, month - 1);
					expiryDate.set(Calendar.YEAR, year);

				} else {
					month = Integer.parseInt(strTok.nextToken());
					int day = Integer.parseInt(strTok.nextToken());
					year = Integer.parseInt(strTok.nextToken());
					expiryDate.set(year, month - 1, day);
				}

				return expiryDate;
			}

		} catch (Throwable e) {
			log.debug("Credit Card: Error Parsing date: " + e);
			log.debug(e);
		}
		return null;
	}

	public String getExpiryDateString() {
		int year = expiryDate.get(java.util.Calendar.YEAR);
		int month = expiryDate.get(java.util.Calendar.MONTH) + 1;
		return (month < 10 ? "0" : "") + month + "/"
				+ (year < 10 ? "0" : "") + year;
	}

	public String toString() {
		return "[Card Type=" + cardType + ", Card Number=" + cardNo
				+ ", Expiration Date=" + expiryDate + "]";
	}

	/**
	 * Valid a Credit Card number
	 */
	public static boolean validCC(String number) throws Exception {
		int CardID;
		if ((CardID = getCardID(number)) != -1)
			return validCCNumber(number);
		return false;
	}

	/**
	 * Get the Card type returns the credit card type INVALID = -1; VISA = 0;
	 * MASTERCARD = 1; AMERICAN_EXPRESS = 2; EN_ROUTE = 3; DINERS_CLUB = 4;
	 */
	public static int getCardID(String number) {
		int valid = INVALID;

		String digit1 = number.substring(0, 1);
		String digit2 = number.substring(0, 2);
		String digit3 = number.substring(0, 3);
		String digit4 = number.substring(0, 4);

		if (isNumber(number)) {
			/*
			 * ---- * VISA prefix=4 * ---- length=13 or 16 (can be 15 too!?!
			 * maybe)
			 */
			if (digit1.equals("4")) {
				if (number.length() == 13 || number.length() == 16)
					valid = VISA;
			}
			/*
			 * ---------- * MASTERCARD prefix= 51 ... 55 * ---------- length= 16
			 */
			else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
				if (number.length() == 16)
					valid = MASTERCARD;
			}
			/*
			 * ---- * AMEX prefix=34 or 37 * ---- length=15
			 */
			else if (digit2.equals("34") || digit2.equals("37")) {
				if (number.length() == 15)
					valid = AMERICAN_EXPRESS;
			}
			/*
			 * ----- * ENROU prefix=2014 or 2149 * ----- length=15
			 */
			else if (digit4.equals("2014") || digit4.equals("2149")) {
				if (number.length() == 15)
					valid = EN_ROUTE;
			}
			/*
			 * ----- * DCLUB prefix=300 ... 305 or 36 or 38 * ----- length=14
			 */
			else if (digit2.equals("36")
					|| digit2.equals("38")
					|| digit3.compareTo("300") >= 0 && digit3.compareTo("305") <= 0 && number.length() == 14) {
				//if (number.length() == 14)
					valid = DINERS_CLUB;
			}
		}
		return valid;

		/*
		 * ---- * DISCOVER card prefix = 60 * -------- lenght = 16 * left as an
		 * exercise ...
		 */

	}

	public static boolean isNumber(String n) {
		try {
			double d = Double.valueOf(n).doubleValue();
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getCardName(int id) {
		return (id > -1 && id < cardNames.length ? cardNames[id] : "");
	}

	public static boolean validCCNumber(String n) {
		try {
			/*
			 * * known as the LUHN Formula (mod10)
			 */
			int j = n.length();

			String[] s1 = new String[j];
			for (int i = 0; i < n.length(); i++)
				s1[i] = "" + n.charAt(i);

			int checksum = 0;

			for (int i = s1.length - 1; i >= 0; i -= 2) {
				int k = 0;

				if (i > 0) {
					k = Integer.valueOf(s1[i - 1]).intValue() * 2;
					if (k > 9) {
						String s = "" + k;
						k = Integer.valueOf(s.substring(0, 1)).intValue()
								+ Integer.valueOf(s.substring(1)).intValue();
					}
					checksum += Integer.valueOf(s1[i]).intValue() + k;
				} else
					checksum += Integer.valueOf(s1[0]).intValue();
			}
			return checksum % 10 == 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isValidCCNumber() {
		try {
			/*
			 * * known as the LUHN Formula (mod10)
			 */
			if(!StringFunctions.isAlphaNumeric(getCardNo()))
			{
				return false;
			}
			int j = getCardNo().length();
			String[] s1 = new String[j];
			for (int i = 0; i < getCardNo().length(); i++)
				s1[i] = "" + getCardNo().charAt(i);

			int checksum = 0;

			for (int i = s1.length - 1; i >= 0; i -= 2) {
				int k = 0;

				if (i > 0) {
					k = Integer.valueOf(s1[i - 1]).intValue() * 2;
					if (k > 9) {
						String s = "" + k;
						k = Integer.valueOf(s.substring(0, 1)).intValue()
								+ Integer.valueOf(s.substring(1)).intValue();
					}
					checksum += Integer.valueOf(s1[i]).intValue() + k;
				} else
					checksum += Integer.valueOf(s1[0]).intValue();
			}
			return checksum % 10 == 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
