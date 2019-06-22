# Develop Note

- Creating Index should be fully considerable. Not all tables, not all fields in tables are need indexed.

- Considered that the index object will be updated in high concurrency scenarios, so the data structure to store the index should be thread-safe.
Thus, for Map structure, we use **ConcurrentHashMap**. For Set structure, we use **ConcurrentSkipListSet**.

- In **match** method of class **UnitKeywordIndex**, we use Apache Common API **CollectionsUtils.isSubCollection()** to check the subset. 

- We need to give the **DataTable** the highest priority order in **ad-search**, set it as the **PriorityOrdered.HIGHEST_PRECEDENCE**.

- Important Event_Type in Binlog:
   1. QUERY_EVENT: like begin, drop table, truncate table, etc.
   2. TABLE_MAP_EVENT 
   3. 
   
- The reason why we monitor Binlog is that we want to decouple the relation between ad-sponsor and ad-search modules.

- **information_schema** is a database that MySQL has by itself. It contains the information of other databases and tables.

