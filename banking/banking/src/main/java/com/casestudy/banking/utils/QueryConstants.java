package com.casestudy.banking.utils;

public class QueryConstants {

    private QueryConstants() {
        throw new IllegalStateException("Utility Class");
    }

    public static final String FETCH_USERS_BY_FULLNAME = "select * from user_account where name=:fullName";

    public static final String FETCH_USERS_BY_EMAIL = "select * from user_account where email =:email limit 1";

    public static final String FETCH_USERS_BY_MOBILE = "select * from user_account where mobile =:mobile limit 1";

    public static final String FETCH_BANK_ACCOUNT_BY_ACCNO_BANK_NAME_AND_CARDNO = "select * from bank_account where bank_name =:bankName and credit_card_number =:creditCardNumber limit 1";

    public static final String FETCH_BANK_BALANCE_BY_ACCOUNT_NUMBER = "select b.balance from bank_account b where b.bank_account_number =:bankAccountNumber";

    public static final String UPDATE_BALACE_BANK_ACCOUNT_NUMBER = "update bank_account set balance =:bankBalance where cvv =:cvv and credit_card_number =:creditCardNumber and bank_account_number =:bankAccountNumber";

    public static final String FETCH_BANK_TRANSACTION_HISTORY_BY_ACCOUNT_NUMBER = "select * from bank_transaction b where b.account_number =:bankAccountNumber";
}
