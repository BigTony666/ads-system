package com.bigtony.ad.sender;

import com.bigtony.ad.mysql.dto.MysqlRowData;

public interface ISender {
    
    void sender(MysqlRowData rowData);
}
