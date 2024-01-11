package com.project.zipkok.model;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TransactionType")
@NoArgsConstructor
@Getter
public class TransactionType {

    @Id
    @Column(name = "transaction_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionTypeId;

    @Column(name = "mprice_min")
    private int mPriceMin;

    @Column(name = "mprice_max")
    private int mPriceMax;

    @Column(name = "mdeposit_min")
    private long mDepositMin;

    @Column(name = "mdeposit_max")
    private long mDepositMax;

    @Column(name = "ydeposit_min")
    private long yDepositMin;

    @Column(name = "ydeposit_max")
    private long yDepositMax;

    @Column(name = "purchase_min")
    private long purchaseMin;

    @Column(name = "purchase_max")
    private long puchaseMax;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
