# SearchableDocumentStore

## What is it?

Searchable Document store is a system to store and querey documents. 

## Search 

The Document Store uses a trie data structure to search the documents for a specific word. The same search methods are used to delete specific documents.  

## Undo 

The Document utalizes a stack data structure to store all the previous commands, which allows for an undo method. 

## Write to disk 

The class DocumentPersistanceManager uses the Google [gson](https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/package-summary.html) library to de/serialize a document to and from a json object. 