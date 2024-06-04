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

The class DocumentPersistanceManager uses the Google [Gson](https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/package-summary.html) library to de/serialize a document to and from a json object. A user of the document store can manually send a document to disk, and the last used document will automatically be sent to disk if there is no more available memory. If the document only stores bytes as supposed to text the DocumentPersistanceManager uses the jakarta.xml.bind [DatatypeConverterInterface](https://javadoc.io/doc/jakarta.xml.bind/jakarta.xml.bind-api/latest/jakarta.xml.bind/jakarta/xml/bind/DatatypeConverterInterface.html) to convert the bytes to and from a json. The implemented Btree data structure stores all the documents and provides the interface in which to move them to and from disk. The document store keeps track of which document was used last with the implemented minheap data structure. 

## Implemented Data Structures 

### BTree

Implemented a BTree data structure to store the documents using the URI as their key. The BTree interface also supports moving a given document to disk. 

### MinHeap

Implemented a MinHeap data structure that keeps the lowest valued object, in thsi case last used time of a document, at the top of the heap. Allows for the removal of a document from memory to make space for more recently used documents. 

### Stack

Implemented a classic stack to store all commands to suport an undo finction. Undo takes the first item of the stack and reverses the action. 

### Trie

Implemented a Trie to have fast word search. Mapped the words to the URI(s) of the document(s) that they apear in.