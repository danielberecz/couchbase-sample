# couchbase-sample

## Views
As a rule of thumb, all repository access methods which are not "by a specific key" require a backing view to find the one or more matching entities. 

The views for this sample to work:

1. For query by first name
```
function (doc, meta) {
  if (doc._class == "com.couchbase.sample.dto.User" && doc.lastName) {
    emit(doc.lastName, null);
  }
}
```
2. For query all documents
```
function (doc, meta) {
  if (doc._class == "com.couchbase.sample.dto.User") {
    emit(null, null);
  }
}
```

[More info on Spring Data Couchbase(]http://docs.spring.io/autorepo/docs/spring-data-couchbase/1.3.1.RELEASE/reference/html)
