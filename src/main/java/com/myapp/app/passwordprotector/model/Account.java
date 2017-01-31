package com.myapp.app.passwordprotector.model;

import com.myapp.app.passwordprotector.controller.Authenticator;

/**
 * Represent an account model (or storeKey).
 */
public class Account {
    private String mAccountName;
    private String mAccountUserName;
    private String mAccountPassword;
    // Reference (key) in the storage
    private String storeKey;

    /**
     * @param accountName mean for e.g.: link to the account or a descriptive name
     * @param accountUserName login data
     * @param accountPassword login data
     */
    public Account(String accountName, String accountUserName, String accountPassword) {
        this.mAccountName = accountName;
        this.mAccountUserName = accountUserName;
        this.mAccountPassword = accountPassword;
    }

    /**
     * @return account reference
     */
    public String getAccountName() {
        return mAccountName;
    }

    /**
     * @param accountName account reference name
     */
    public void setAccountName(String accountName) {
        this.mAccountName = accountName;
    }

    /**
     * @return username login data
     */
    public String getAccountUserName() {
        return mAccountUserName;
    }

    /**
     * @param accountUserName username login data
     */
    public void setAccountUserName(String accountUserName) {
        this.mAccountUserName = accountUserName;
    }

    /**
     * @return password login data
     */
    public String getAccountPassword() {
        return mAccountPassword;
    }

    /**
     * @param accountPassword password login data
     */
    public void setAccountPassword(String accountPassword) {
        this.mAccountPassword = Authenticator.encrypt(accountPassword);
    }

    /**
     * @param s reference in store
     */
    public void setStoreKey(String s) {
        this.storeKey = s;
    }

    /**
     * @return based on store reference
     */
    public String getStoreKey() {
        return storeKey;
    }

    @Override
    public String toString() {
        return "Account{" + "mAccountName='" + mAccountName + '\'' + ", mAccountUserName='"
                + mAccountUserName + '\'' + ", mAccountPassword='"
                + mAccountPassword + '\'' + ", storeKey='" + storeKey + '\'' + '}';
    }
}
