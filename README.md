# SearchableDocumentStore

## Table of Contents
1. [What is it?](#what-is-it)
2. [Features](#features)
    1. [Search](#search)
    2. [Undo](#undo)
    3. [Write to disk](#write-to-disk)
3. [Implemented Data Structures](#implemented-data-structures)
    1. [BTree](#btree)
    2. [MinHeap](#minheap)
    3. [Stack](#stack)
    4. [Trie](#trie)

## What is it?

Searchable Document store is a system to store and query documents. 

## Features

### Search 

Implemented a trie data structure to search the documents for a specific word. The same search methods are used to delete specific documents.  

### Undo 

Implemented stack data structure to store all the previous commands, which allows for an undo method. 

### Write to disk 

The class DocumentPersistanceManager uses the Google [Gson](https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/package-summary.html) library to de/serialize a document to and from a json object. A user of the document store can manualy send a document to disk, and the last used document will automaticly be sent to disk if there is no more availble memory. If the document only stores bytes as supposed to text the DocumentPersistanceManager uses the jakarta.xml.bind [DatatypeConverterInterface](https://javadoc.io/doc/jakarta.xml.bind/jakarta.xml.bind-api/latest/jakarta.xml.bind/jakarta/xml/bind/DatatypeConverterInterface.html) to convert the bytes to and from a json. The implmented Btree data structure stores all the documents and provides the interface in wich to move them to and from disk. The document store keeps track of which document was used last with the implemented minheap data structure. 

## Implemented Data Structures 

### BTree

### MinHeap

### Stack

### Trie