package com.bigtony.ad.mysql.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

public class BinlogServiceTest {
    
    public static void main(String[] args) throws Exception {
    
        /**
         *
         * Write---------------
         * WriteRowsEventData{tableId=105, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
         *     [10, 10, plan, 1, Mon Dec 31 19:00:00 EST 2018, Mon Dec 31 19:00:00 EST 2018, Mon Dec 31 19:00:00 EST 2018, Mon Dec 31 19:00:00 EST 2018]
         * ]}
         */
    
        BinaryLogClient client = new BinaryLogClient(
                "127.0.0.1",
                3306,
                "root",
                "123456"
        );
    
        client.registerEventListener(event -> {
        
            EventData data = event.getData();
        
            if (data instanceof UpdateRowsEventData) {
                System.out.println("Update--------------");
                System.out.println(data.toString());
            } else if (data instanceof WriteRowsEventData) {
                System.out.println("Write---------------");
                System.out.println(data.toString());
            } else if (data instanceof DeleteRowsEventData) {
                System.out.println("Delete--------------");
                System.out.println(data.toString());
            }
        });
    
        client.connect();
    }
}
