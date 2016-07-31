package com.abed.quandoo.data.model;

import com.abed.quandoo.data.local.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

@ModelContainer
@com.raizlabs.android.dbflow.annotation.Table(database = AppDatabase.class)
public class Table extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public boolean reserved;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
