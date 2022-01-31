package io.bankapp.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Embeddable
//class LoggerPk {
//	@Column(name="acctID")
//	public int acctID;
//	
//	@ManyToOne
//	@JoinColumn(name="Account_ID")
//	private Accounts acct;
//}

@Entity
public class AcctLogger {
//	@EmbeddedId
//	private LoggerPk id; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int trxID;
	private int acctID;
	private String transacType;
	private String transacStatus;
	private int initBal;
	private int finalBal;

	public AcctLogger() {

	}

	public int getTrxID() {
		return trxID;
	}

	public void setTrxID(int trxID) {
		this.trxID=trxID;
	}

	public AcctLogger(int acctID, String transacType, String transacStatus, int initBal, int finalBal) {
		this.acctID=acctID;
		this.transacType=transacType;
		this.transacStatus=transacStatus;
		this.initBal=initBal;
		this.finalBal=finalBal;
	}


	public int getAcctID() {
		return acctID;
	}

	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}

	public String getTransacType() {
		return transacType;
	}

	public void setTransacType(String transacType) {
		this.transacType = transacType;
	}

	public String getTransacStatus() {
		return transacStatus;
	}

	public void setTransacStatus(String transacStatus) {
		this.transacStatus = transacStatus;
	}

	public int getInitBal() {
		return initBal;
	}

	public void setInitBal(int initBal) {
		this.initBal = initBal;
	}

	public int getFinalBal() {
		return finalBal;
	}

	public void setFinalBal(int finalBal) {
		this.finalBal = finalBal;
	}

}
