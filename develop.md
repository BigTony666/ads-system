# Develop Note

- Creating Index should be fully considerable. Not all tables, not all fields in tables are need indexed.

- Considered that the index object will be updated in high concurrency scenarios, so the data structure to store the index should be thread-safe.
Thus, for Map structure, we use **ConcurrentHashMap**. For Set structure, we use **ConcurrentSkipListSet**.

- In **match** method of class **UnitKeywordIndex**, we use Apache Common API **CollectionsUtils.isSubCollection()** to check the subset. 

- We need to give the **DataTable** the highest priority order in **ad-search**, set it as the **PriorityOrdered.HIGHEST_PRECEDENCE**.
