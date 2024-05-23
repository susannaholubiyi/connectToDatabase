package org.suzieBarbieStore.models;

import java.math.BigDecimal;

public class Wallet {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    private BigDecimal balance;
    private Long id;
}
