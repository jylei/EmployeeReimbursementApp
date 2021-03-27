package com.jun.model;

public class Reimbursement {
	private int rId;
	private double amount;
	private boolean pending;
	private boolean approved;
	private String reason;
	
	public Reimbursement(int rId, double amount, boolean pending, boolean approved, String reason) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.pending = pending;
		this.approved = approved;
		this.reason = reason;
	}
	
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + (pending ? 1231 : 1237);
		result = prime * result + rId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (approved != other.approved)
			return false;
		if (pending != other.pending)
			return false;
		if (rId != other.rId)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", amount=" + amount + ", pending=" + pending + ", approved=" + approved
				+ ", reason=" + reason + "]";
	}
	

}