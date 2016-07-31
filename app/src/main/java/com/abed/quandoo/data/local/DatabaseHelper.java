package com.abed.quandoo.data.local;


import com.abed.quandoo.data.model.Customer;
import com.abed.quandoo.data.model.Customer_Table;
import com.abed.quandoo.data.model.Table;
import com.abed.quandoo.data.model.Table_Table;
import com.abed.quandoo.data.remote.API_Customer;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DatabaseHelper {

    @Inject
    public DatabaseHelper() {

    }


    public List<Table> loadTables() {
        return SQLite.select()
                .from(Table.class)
                .orderBy(Table_Table.id, true)
                .queryList();
    }

    public List<Customer> loadAllCustomers() {
        return SQLite.select()
                .from(Customer.class)
                .queryList();
    }

    public Customer loadCustomerById(long challengeId) {
        return SQLite.select()
                .from(Customer.class)
                .where(Customer_Table.id.eq(challengeId))
                .querySingle();
    }

    public void processCustomer(API_Customer api_customer) {
        Customer customer = loadCustomerById(api_customer.id);
        if (customer == null)
            customer = new Customer();
        customer.copyValues(api_customer.id, api_customer.customerFirstName, api_customer.customerLastName);
        customer.save();
    }

    public void clearReservation() {
        List<Table> tables = SQLite.select()
                .from(Table.class)
                .where(Table_Table.reserved.eq(true))
                .queryList();
        for (Table table : tables) {
            table.setReserved(false);
            table.save();
        }
    }

    public void processTablesStates(List<Boolean> states) {
        List<Table> tables = loadTables();
        int count = 0;
        for (Boolean state : states) {
            Table currentTable;
            try {
                currentTable = tables.get(count);
            } catch (IndexOutOfBoundsException e) {
                currentTable = new Table();
            }
            if (state) {
                currentTable.setReserved(state);
            }
            currentTable.save();
            count++;
        }
    }

    public void markTableAsReserved(Table table) {
        table.setReserved(true);
        table.save();
    }

}
