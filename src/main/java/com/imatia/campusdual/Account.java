package com.imatia.campusdual;

import java.math.BigDecimal;

public class Account{
    private Branch branch;
    private String branchID;
    private String entityID;
    private String cDID;
    private String accountNumber;
    private BigDecimal balance;

    public Account(Branch branch) {
        this.branchID = branch.getBranchID();
        this.entityID = branch.getEntityID();
        accountNumber = String.valueOf((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);
        calculateCDID();
      this.balance = new BigDecimal("0.0");
    }

    public Account(Branch branch, String accountNumber) {
        this.branchID = branch.getBranchID();
        this.entityID = branch.getEntityID();
        this.accountNumber = accountNumber;
        calculateCDID();
        this.balance = new BigDecimal("0.0");
    }

    public void calculateCDID() {
        String firstcd = "00".concat(this.entityID).concat(branchID);
        this.cDID = generateCDID(firstcd, accountNumber);

    }

    public String generateCDID(String firstcd, String accountNumber) {
        int firstDC = this.calculateDCUniqueDigit(firstcd);
        int secondDC = this.calculateDCUniqueDigit(accountNumber);
        return String.valueOf(firstDC).concat(String.valueOf(secondDC));
    }

    public int calculateDCUniqueDigit(String numberString) {

        int[] v = new int[] { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
        String[] nums = numberString.split("");
        int[] n = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = Integer.parseInt(nums[i]);
        }

        int sum = 0;

        for (int i = 0; i < numberString.length(); i++) {
            sum = (v[i] * n[i]) + sum;
        }

        int toRetMod = sum % 11;

        if ((toRetMod == 0) || (toRetMod == 1)) {
            return toRetMod;
        }

        return 11 - toRetMod;
    }

    public String getBankAccount(){
        return this.entityID.concat(this.branchID).concat(this.cDID).concat(this.accountNumber);
    }

    public void addBalance(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getBranchID() {
        return branchID;
    }

    public String getEntityID() {
        return entityID;
    }

    public String getcDID() {
        return cDID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void withdrawalBalance(BigDecimal value) throws InsufficientBalanceException{
        BigDecimal newValue = this.balance.subtract(value);
        if ((newValue.compareTo(BigDecimal.ZERO)) > -1){
            this.balance = newValue;
        }else{
            throw new InsufficientBalanceException("M_INSUFFICIENT_BALANCE");
        }
    }
}
