package com.bigtony.ad.mysql.listener;

import com.bigtony.ad.mysql.dto.BinlogRowData;

public interface Ilistener {
    
    void register();
    
    void onEvent(BinlogRowData eventData);
}
