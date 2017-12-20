### Major

1. Json processing
1. Batch (jdbc batch) and Bulk (native) write technique's for ETL style writes.
1. Demonstrate Transactions


### Minor 

### Issue with #{} based mybatis parameter handling in json constructs
  
  Need for ${} instead of #{}
  Need to explicitly define @Param for single param Mapper function.

#### Date/Time handling (app, db, json)

Bottom-line: Ensure uniform precision (say seconds/millis/micros), timezone and format across all layers.

1. **Precision/Resolution**: 
   1. Maintain common level of precision at java, db, json layer.
      1. Java (App) Layer: Time is represented to nanosecond precision. For example, the value "13:45.30.123456789" can be stored in a `LocalTime,LocalDateTime`.
      1. PostGreSQL (Database) Layer: `timestamp` has precision upto microseconds. 
      1. JSON: This stores information as string and thus it's resolution is dependent on how you create/parse it.
      1. Prefer the lowest offered precision (assuming it suffices business needs). If source has higher precision, then
      round it upfront.  
1. **Timezone**: Use a common time-zone in all layers or store timezone in timestamp.
   1. I prefer the former, it's cleaner and simpler.
   1. Set timezone in app layer as `-Duser.timezone=GMT`
   1. Make similar changes for db layer.  
1. **Format/Representation**: With json processing we can have two modes to (de)serialize Date present in json. 
   One being at app layer (using lib like jackson) other being at db layer (Using `CAST(json->>date_attr as timestamp)`)
   1. Either always use one of the two.
   1. Else establish a common (de)serialization format.
      1. Jackson deserializes to epoch time by default. Change it to a standard format like 'yyMMdd hh:mm:ss.SSS'    
1. **Adopt modern date classes in app layer**: In flat object move from `java.util.Date` to `java.util.LocaleDate` and `java.time.LocalTime`
   1. Document the advantage of this move
   1. See how transformations to/from string are done differently.
   1. Special handling in parsing libraries like jackson (https://stackoverflow.com/a/35031046)
   
#### Numeric/BigDecimal handling (app, db, json)

1. **Precision**
1. **Rounding**