package edu.yu.cs.com1320.project.stage5.impl;
import edu.yu.cs.com1320.project.CommandSet;
import edu.yu.cs.com1320.project.GenericCommand;
import edu.yu.cs.com1320.project.Undoable;
import edu.yu.cs.com1320.project.impl.*;
import edu.yu.cs.com1320.project.stage5.Document;
import edu.yu.cs.com1320.project.stage5.DocumentStore;
import java.io.File;
import java.lang.System;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.function.Function;

public class DocumentStoreImpl implements DocumentStore{
    private StackImpl<Undoable> commandStack = new StackImpl<>();
    private DocumentPersistenceManager ourDPM;
    private TrieImpl<URINode> ourTrie = new TrieImpl<>();
    private BTreeImpl<URI, Document> ourBTree = new BTreeImpl<>();
    private MinHeapImpl<URINode> ourHeap = new MinHeapImpl<>();
    private Hashtable<URI, URINode> UriTable = new Hashtable<>();
    private int maxDocCount = Integer.MAX_VALUE;
    private int DocCount = 0;
    private int byteCount = 0;
    private int maxByteCount = Integer.MAX_VALUE;
    public DocumentStoreImpl(File base){
        this.ourDPM = new DocumentPersistenceManager(base);
        ourBTree.setPersistenceManager(ourDPM);
    }
    public DocumentStoreImpl(){
        this.ourDPM = new DocumentPersistenceManager(new File(System.getProperty("user.dir")));
        ourBTree.setPersistenceManager(ourDPM);
    }
    /**
     * @param input the document being put
     * @param uri unique identifier for the document
     * @param format indicates which type of document format is being passed
     * @return if there is no previous doc at the given URI, return 0. If there is a previous doc, return the hashCode of the previous doc. If InputStream is null, this is to delete it, and thus return either the hashCode of the deleted doc or 0 if there is no doc to delete.
     * @throws IOException if there is an issue reading input
     * @throws IllegalArgumentException if uri or format are null
     */
    public int put(InputStream input, URI uri, DocumentStore.DocumentFormat format)  throws IOException{
        byte[] ourInput;
        if (uri == null || format == null) {throw new IllegalArgumentException("URI or document format were inputted as null");}
        try{
            ourInput = input.readAllBytes();
        }catch(IOException e){
            throw new IOException("could not read input");
        }
        if(ourInput == null){return this.deletePut(uri);}
        DocumentImpl ourDoc;
        if(format == DocumentStore.DocumentFormat.BINARY){
            ourDoc = new DocumentImpl(uri, ourInput);
            ourDoc.setLastUseTime(System.nanoTime());
        }else{
            ourDoc = new DocumentImpl(uri, new String(ourInput), null);
            ourDoc.setLastUseTime(System.nanoTime());
        }
        if(this.areWeOver(ourDoc)){return this.overPut(ourDoc);}
        ourDoc.setLastUseTime(System.nanoTime());
        DocumentImpl returnDoc = (DocumentImpl) ourBTree.put(uri, ourDoc);
        int returnInt;
        if(returnDoc == null){
            returnInt =  0;
        }else{
            returnInt =  finalStep(returnDoc);
        }
        URINode newNode = new URINode(uri);
        newNode.setSerialized(false);
        UriTable.put(uri, newNode);
        if(format == DocumentFormat.TXT){inputToTrie(newNode);}
        this.putIntoHeap(newNode);
        Function<URI, Boolean> undoPutFunction = (URI) -> {
            if(returnDoc == null){
                ourDoc.setLastUseTime(Long.MIN_VALUE);
                ourHeap.reHeapify(UriTable.get(uri));
                ourHeap.remove();
                this.clearFromTree(UriTable.get(uri));
                this.removeBytes(UriTable.get(uri));
                UriTable.remove(uri);
                ourBTree.put(uri, returnDoc);
                DocCount--;
            }else{
                this.removeBytes(UriTable.get(uri));
                this.clearFromTree(UriTable.get(uri));
                ourBTree.put(uri, returnDoc);
                returnDoc.setLastUseTime(System.nanoTime());
                URINode ourNode = UriTable.get(returnDoc.getKey());
                UriTable.put(uri, ourNode);
                this.putIntoHeap(ourNode);
                this.inputToTrie(ourNode);
            }
            return true;
        };
        commandStack.push(new GenericCommand<URI>(uri, undoPutFunction));
        return returnInt;
    }
    private boolean areWeOver(Document doc){
        if(doc.getDocumentTxt() != null){
            if(doc.getDocumentTxt().getBytes().length > maxByteCount){
                return true;
            }
        }
        if(doc.getDocumentTxt() == null){
            return doc.getDocumentBinaryData().length > maxByteCount;
        }
        return false;
    }
    private int overPut(DocumentImpl doc){
        DocumentImpl stored = (DocumentImpl) ourBTree.put(doc.getKey(), doc);
        try {
            ourBTree.moveToDisk(doc.getKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        URINode newNode = new URINode(doc.getKey());
        this.inputToTrie(newNode);
        if(stored == null){
            return 0;
        }else{
            return stored.hashCode();
        }
    }
    private int finalStep(Document returnDoc){
        URINode ourNode = UriTable.get(returnDoc.getKey());
        this.removeBytes(UriTable.get(returnDoc.getKey()));
        UriTable.remove(returnDoc.getKey());
        this.clearFromTree(ourNode);
        returnDoc.setLastUseTime(Long.MIN_VALUE);
        ourHeap.reHeapify(ourNode);
        ourHeap.remove();
        return returnDoc.hashCode();
    }

    private void putIntoHeap(URINode ourDoc){
        if(DocCount >= maxDocCount){
            this.makeSpaceForNewDoc();
        }
        int byteCountLocal;
        if((ourBTree.get(ourDoc.nodeURI)).getDocumentTxt() != null){
            byteCountLocal = ourBTree.get(ourDoc.nodeURI).getDocumentTxt().getBytes().length;
        }else{
            byteCountLocal = ourBTree.get(ourDoc.nodeURI).getDocumentBinaryData().length;
        }
        if(byteCount+byteCountLocal > maxByteCount){
            this.clearUpMemory(byteCountLocal);
        }
        ourHeap.insert(ourDoc);
        ourHeap.reHeapify(ourDoc);
        byteCount = byteCount + byteCountLocal;
        DocCount++;
        ourDoc.setSerialized(false);
    }
    private void removeBytes(URINode doc){//done
        if(doc == null){
            return;
        }
        if(ourBTree.get(doc.getURI()).getDocumentTxt() != null){
            byteCount = byteCount - ourBTree.get(doc.getURI()).getDocumentTxt().getBytes().length;
        } else{
            byteCount = byteCount - ourBTree.get(doc.getURI()).getDocumentBinaryData().length;
        }
    }
    private void clearUpMemory(int requiredBytes){
        while(requiredBytes > (maxByteCount - byteCount)){
            URINode removedDoc = ourHeap.remove();
            this.removeBytes(removedDoc);
            try {
                ourBTree.moveToDisk(removedDoc.getURI());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            DocCount--;
            removedDoc.setSerialized(true);
        }
    }
    private void removeCommands(URINode removedDoc){
        Undoable ourCommand = commandStack.pop();
        StackImpl<Undoable> holderStack = new StackImpl<>();
        while(ourCommand != null && !weGotIt(ourCommand, removedDoc.getURI())){
            holderStack.push(ourCommand);
            ourCommand = commandStack.pop();
        }
        Undoable holderCommand = holderStack.pop();
        while(holderCommand != null){
            commandStack.push(holderCommand);
            holderCommand = holderStack.pop();
        }
        if(ourCommand instanceof GenericCommand<?>){
            return;
        }
        if(ourCommand instanceof CommandSet<?>){
            CommandSet<GenericCommand> newSet = new CommandSet<>();
            Iterator<? extends GenericCommand<?>> ourIt = ((CommandSet<?>) ourCommand).iterator();
            for (Iterator<? extends GenericCommand<?>> it = ourIt; it.hasNext(); ) {
                GenericCommand gc = it.next();
                if(!gc.getTarget().equals(removedDoc.getURI())){
                    newSet.add(gc);
                }
            }
            ourCommand = newSet;
        }
    }
    private void makeSpaceForNewDoc(){
        while(DocCount >= (maxDocCount) && DocCount != 0){
            URINode removedDoc = ourHeap.remove();
            this.removeBytes(removedDoc);
            try {
                ourBTree.moveToDisk(removedDoc.getURI());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            DocCount--;
            removedDoc.setSerialized(true);
        }
    }
    private int deletePut(URI uri){
        this.clearFromTree(UriTable.get(uri));
        if(ourBTree.get(uri) == null){return 0;}
        Function<URI, Boolean> undoPutFunction = (URI) -> {
            ourBTree.put(uri, (ourBTree.get(uri)));
            ourBTree.get(uri).setLastUseTime(System.nanoTime());
            URINode newNode = new URINode(uri);
            UriTable.put(uri, newNode);
            this.putIntoHeap(newNode);
            this.inputToTrie(newNode);
            return true;
        };
        commandStack.push(new GenericCommand<URI>(uri, undoPutFunction));
        ourBTree.get(uri).setLastUseTime(Long.MIN_VALUE);
        ourHeap.reHeapify(UriTable.get(uri));
        ourHeap.remove();
        DocCount--;
        int hash = ourBTree.get(uri).hashCode();
        this.removeBytes(UriTable.get(uri));
        UriTable.remove(uri);
        ourBTree.put(uri, null);
        return hash;
    }
    private void inputToTrie(URINode doc){
        if(ourBTree.get(doc.getURI()).getDocumentTxt() == null){return;}
        String ourText = ourBTree.get(doc.getURI()).getDocumentTxt();
        ourText = ourText.replaceAll("[^A-Za-z0-9 ]", "");
        String[] chopped = ourText.split(" ");
        for(String s : chopped){
            ourTrie.put(s, doc);
        }
        if(UriTable.get(doc.getURI()).serialized){
            try {
                ourBTree.moveToDisk(doc.getURI());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @param uri the unique identifier of the document to get
     * @return the given document
     */
    public Document get(URI uri){
        if(uri == null){return null;}
        URINode ourNode = UriTable.get(uri);
        if(ourNode == null){return null;}
        int need = 0;
        if(ourNode.serialized){
            if(ourBTree.get(uri) == null){return null;}
            DocumentImpl ourDoc = (DocumentImpl) ourBTree.get(uri);
            ourDoc.setLastUseTime(System.nanoTime());
            ourNode.setSerialized(false);
            ourHeap.insert(ourNode);
            ourHeap.reHeapify(ourNode);
            this.makeSpaceForNewDoc();
            Document dc = ourBTree.get(uri);
            if(dc.getDocumentTxt() != null){
                need = ourBTree.get(uri).getDocumentTxt().getBytes().length;
            }else {
                need = ourBTree.get(uri).getDocumentBinaryData().length;
            }
            this.clearUpMemory(need);
            DocCount++;
            byteCount += need;
        }
        DocumentImpl ourDoc = (DocumentImpl) ourBTree.get(uri);
        if(ourDoc != null){
            ourDoc.setLastUseTime(System.nanoTime());
        }
        if(maxDocCount < 1 || maxByteCount< need){
            try {
                ourBTree.moveToDisk(uri);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ourDoc.setLastUseTime(Long.MIN_VALUE);
            ourHeap.reHeapify(ourNode);
            ourHeap.remove();
            DocCount--;
            byteCount = byteCount - need;
            ourNode.setSerialized(true);
        }
        if(ourNode.serialized == false){
            ourHeap.reHeapify(UriTable.get(uri));
        }
        return ourDoc;
    }

    /**
     * @param uri the unique identifier of the document to delete
     * @return true if the document is deleted, false if no document exists with that URI
     */
    public boolean delete(URI uri){
        if(ourBTree.get(uri) == null){return false;}
        ourBTree.get(uri).setLastUseTime(Long.MIN_VALUE);
        URINode ourNode = UriTable.get(uri);
        if(ourNode.serialized == false){
            ourHeap.reHeapify(ourNode);
            ourHeap.remove();
        }
        if(ourBTree.get(uri).getKey() == null){
            return false;
        }else{
            this.clearFromTree(UriTable.get(uri));
            Document putDoc = ourBTree.get(uri);
            Function<URI, Boolean> undoDeleteFunction = (URI) -> {
                ourBTree.put(URI, putDoc);
                ourBTree.get(uri).setLastUseTime(System.nanoTime());
                URINode newNode = new URINode(uri);
                UriTable.put(uri, newNode);
                newNode.setSerialized(false);
                putIntoHeap(newNode);
                inputToTrie(newNode);
                return true;
            };
            GenericCommand<URI> ourCommand = new GenericCommand<>(uri,undoDeleteFunction);
            commandStack.push(ourCommand);
            DocCount--;
            this.removeBytes(UriTable.get(uri));
            ourBTree.put(uri, null);
            UriTable.remove(uri);
            return true;
        }
    }


    /**
     * undo the last put or delete command
     * @throws IllegalStateException if there are no actions to be undone, i.e. the command stack is empty
     */
    public void undo() throws IllegalStateException{
        if(commandStack.peek() == null){
            throw new IllegalStateException("no commands to be undone");
        }
        Undoable lastCommand = commandStack.pop();
        lastCommand.undo();
    }

    /**
     * undo the last put or delete that was done with the given URI as its key
     * @param uri ourUri to undo
     * @throws IllegalStateException if there are no actions on the command stack for the given URI
     */
    public void undo(URI uri) throws IllegalStateException{
        if(commandStack.peek() == null){
            throw new IllegalStateException("no commands to be undone");
        }
        Undoable ourCommand = commandStack.pop();
        StackImpl<Undoable> holderStack = new StackImpl<>();
        while(ourCommand != null && !weGotIt(ourCommand, uri)){
            holderStack.push(ourCommand);
            ourCommand = commandStack.pop();
        }
        if(ourCommand instanceof CommandSet<?>){
            if(((CommandSet<URI>) ourCommand).size() > 1){
                commandStack.push(ourCommand);
            }
        }
        Undoable holderCommand = holderStack.pop();
        while(holderCommand!=null){
            commandStack.push(holderCommand);
            holderCommand = holderStack.pop();
        }

        if(ourCommand == null){
            throw new IllegalStateException("no action has been done to the given URI");
        }
        undoGeneral(ourCommand, uri);
    }
    private void undoGeneral(Undoable command, URI uri){
        if(command instanceof GenericCommand<?>){
            command.undo();
        }
        if(command instanceof CommandSet<?>){
            ((CommandSet<URI>) command).undo(uri);
        }
    }
    private boolean weGotIt(Undoable command, URI uri){
        if(command instanceof GenericCommand<?>){
            if(((GenericCommand<URI>) command).getTarget().equals(uri)){
                return true;
            }
        }else{
            return ((CommandSet<URI>) command).containsTarget(uri);
        }
        return false;
    }
    /**
     * Retrieve all documents whose text contains the given keyword.
     * Documents are returned in sorted, descending order, sorted by the number of times the keyword appears in the document.
     * Search is CASE SENSITIVE.
     * @param keyword keyword
     * @return a List of the matches. If there are no matches, return an empty list.
     */
    public List<Document> search(String keyword){
        String newkeyword = keyword.replaceAll("[^a-zA-Z0-9]", "");
        class Compare implements Comparator<URINode> {
            @Override
            public int compare(URINode o1, URINode o2) {
                Integer a = ourBTree.get(o1.getURI()).wordCount(newkeyword);
                Integer b = ourBTree.get(o2.getURI()).wordCount(newkeyword);

                return b.compareTo(a);
            }
        }
        Comparator<URINode> Compare = new Compare();
        List<URINode> ourList = ourTrie.getAllSorted(newkeyword, Compare);
        return this.searchList(ourList);
    }

    /**
     * Retrieve all documents that contain text which starts with the given prefix
     * Documents are returned in sorted, descending order, sorted by the number of times the prefix appears in the document.
     * Search is CASE SENSITIVE.
     * @param keywordPrefix prefix
     * @return a List of the matches. If there are no matches, return an empty list.
     */
    public List<Document> searchByPrefix(String keywordPrefix){
        String newKey = keywordPrefix.replaceAll("[^A-Za-z0-9]", "");
        class Compare implements Comparator<URINode>{
            @Override
            public int compare(URINode o1, URINode o2) {
                String doco1 =  ourBTree.get(o1.getURI()).getDocumentTxt();
                String doco2 = ourBTree.get(o2.getURI()).getDocumentTxt();
                doco1.replaceAll("[^A-Za-z0-9 ]", "");
                doco2.replaceAll("[^A-Za-z0-9 ]", "");
                String[] oneArray = doco1.split(" ");
                String[] twoArray = doco2.split(" ");
                Integer a = 0;
                Integer b = 0;
                for(String s : oneArray){
                    if(s.startsWith(newKey)){
                        a++;
                    }
                }
                for(String s : twoArray){
                    if(s.startsWith(newKey)){
                        b++;
                    }
                }
                return b.compareTo(a);
            }
        }
        Comparator<URINode> Compare = new Compare();
        List<URINode> ourList = ourTrie.getAllWithPrefixSorted(newKey, Compare);
        return this.searchList(ourList);
    }
    private List<Document> searchList(List<URINode> ourList){
        long timeStamp = System.nanoTime();
        List<Document> returnList = new ArrayList<>();
        for(URINode D : ourList){
            ourBTree.get(D.getURI()).setLastUseTime(timeStamp);
            if(D.serialized){
                D.setSerialized(false);
                ourHeap.insert(D);
                DocCount++;
            }
            ourHeap.reHeapify(D);
            returnList.add(ourBTree.get(D.getURI()));
            D.setSerialized(false);
            this.makeSpaceForNewDoc2();
            if(ourBTree.get(D.getURI()).getDocumentTxt() != null){
                this.clearUpMemory(ourBTree.get(D.getURI()).getDocumentTxt().getBytes().length);
            }else{
                this.clearUpMemory(ourBTree.get(D.getURI()).getDocumentBinaryData().length);
            }
        }
        return returnList;
    }

    /**
     * Completely remove any trace of any document which contains the given keyword
     * Search is CASE SENSITIVE.
     * @param keyword the keyword
     * @return a Set of URIs of the documents that were deleted.
     */
    public Set<URI> deleteAll(String keyword){
        keyword = keyword.replaceAll("[^A-Za-z0-9]", "");
        Set<URINode> ourDocs = ourTrie.deleteAll(keyword);
        CommandSet<URI> ourCommandSet = new CommandSet<>();
        for(URINode doc : ourDocs){
            DocumentImpl held = (DocumentImpl) ourBTree.get(doc.getURI());
            URI ourUri = doc.getURI();
            Function<URI, Boolean> undoDeleteFunction = (URI) -> {
                ourBTree.put(ourUri, held);
                URINode newNode = new URINode(ourUri);
                newNode.setSerialized(false);
                UriTable.put(ourUri, newNode);
                this.inputToTrie(newNode);
                ourBTree.get(doc.getURI()).setLastUseTime(System.nanoTime());
                putIntoHeap(newNode);
                return true;
            };
            GenericCommand<URI> ourCommand = new GenericCommand<>(ourUri, undoDeleteFunction);
            ourCommandSet.addCommand(ourCommand);
        }
        commandStack.push(ourCommandSet);
        Set<URI> returnSet = this.getReturnSet(ourDocs);
        return returnSet;
    }
    private void clearFromTree(URINode doc){
        if(ourBTree.get(doc.getURI()).getDocumentTxt() == null){return;}
        String ourText = ourBTree.get(doc.getURI()).getDocumentTxt();
        ourText = ourText.replaceAll("[^A-Za-z0-9 ]", "");
        String[] chopped = ourText.split(" ");
        for(String s : chopped){
            ourTrie.delete(s, doc);
        }
    }
    private Set<URI> getReturnSet(Set<URINode> ourDocs){
        Set<URI> returnSet = new HashSet<>();
        for(URINode doc : ourDocs){
            this.clearFromTree(doc);
            returnSet.add(doc.getURI());
            ourBTree.get(doc.getURI()).setLastUseTime(Long.MIN_VALUE);
            if(doc.serialized == false){
                ourHeap.reHeapify(doc);
                ourHeap.remove();
                DocCount--;
                this.removeBytes(doc);
            }
            ourBTree.put(doc.getURI(), null);
            UriTable.remove(doc.getURI());
        }
        return returnSet;
    }
    /**
     * Completely remove any trace of any document which contains a word that has the given prefix
     * Search is CASE SENSITIVE.
     * @param keywordPrefix the prefix to delete
     * @return a Set of URIs of the documents that were deleted.
     */
    public Set<URI> deleteAllWithPrefix(String keywordPrefix){
        keywordPrefix = keywordPrefix.replaceAll("[^A-Za-z0-9]", "");
        Set<URINode> ourDocs = ourTrie.deleteAllWithPrefix(keywordPrefix);
        CommandSet<URI> ourCommandSet = new CommandSet<>();
        for(URINode doc : ourDocs){
            DocumentImpl held = (DocumentImpl) ourBTree.get(doc.getURI());
            URI ourUri = doc.getURI();
            Function<URI, Boolean> undoDeleteFunction = (URI) -> {
                URINode newNode = new URINode(ourUri);
                newNode.setSerialized(false);
                UriTable.put(ourUri, newNode);
                ourBTree.put(ourUri, held);
                ourBTree.get(ourUri).setLastUseTime(System.nanoTime());
                this.inputToTrie(newNode);
                putIntoHeap(newNode);
                return true;
            };
            GenericCommand<URI> ourCommand = new GenericCommand<>(doc.getURI(), undoDeleteFunction);
            ourCommandSet.addCommand(ourCommand);
        }
        commandStack.push(ourCommandSet);
        Set<URI> returnSet = this.getReturnSet(ourDocs);
        this.removeURI(returnSet);
        return returnSet;
    }
    private void removeURI(Set<URI> returnSet){
        for(URI uri : returnSet){
            UriTable.remove(uri);
            try {
                ourBTree.put(uri, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * set maximum number of documents that may be stored
     * @param limit the max amount of docs
     */
    public void setMaxDocumentCount(int limit){
        if(limit < 0){
            throw new IllegalArgumentException("cant have negative limits");
        }
        this.maxDocCount = limit;
        this.makeSpaceForNewDoc2();
    }
    private void makeSpaceForNewDoc2(){
        while(DocCount > (maxDocCount)){
            URINode removedDoc = ourHeap.remove();
            this.removeBytes(removedDoc);
            try {
                ourBTree.moveToDisk(removedDoc.getURI());
            } catch (Exception e) {
                return;
            }
            DocCount--;
            removedDoc.setSerialized(true);
        }
    }
    /**
     * set maximum number of bytes of memory that may be used by all the documents in memory combined
     * @param limit the Byte limit
     */
    public void setMaxDocumentBytes(int limit){
        if(limit < 0){
            throw new IllegalArgumentException("cant have negative limits");
        }
        this.maxByteCount = limit;
        try {
            this.clearUpMemory(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private class URINode implements Comparable<URINode> {
        private URI nodeURI;
        private boolean serialized = false;
        @Override
        public int compareTo(URINode o) {
            Long l1 = (ourBTree.get(nodeURI)).getLastUseTime();
            Long l2 = (ourBTree.get(o.getURI())).getLastUseTime();
            return l1.compareTo(l2);
        }
        private URINode(URI uri){
            this.nodeURI = uri;
        }
        private URI getURI(){
            return this.nodeURI;
        }
        private void setSerialized(boolean b){
            this.serialized = b;
        }
        private boolean getSeialized(){
            return this.serialized;
        }
    }
}