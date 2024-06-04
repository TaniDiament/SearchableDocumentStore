<<<<<<< HEAD
package edu.yu.cs.com1320.project.stage5.impl;
import edu.yu.cs.com1320.project.stage5.Document;
import java.util.*;
import java.net.URI;

public class DocumentImpl implements Document{
    private URI uri;
    private String text;
    private byte[] binaryData;
    private Long lastTime;
    private HashMap<String, Integer> wordCount = new HashMap<>();

    @Override
    public int compareTo(Document o) {
        return lastTime.compareTo(o.getLastUseTime());
    }

    public DocumentImpl(URI uri, String txt, Map<String, Integer> wordCountMap){
        if(uri == null || txt == null){
            throw new IllegalArgumentException("need to input non null values");
        }
        this.uri = uri;
        this.text = txt;
        this.binaryData = null;
        if(wordCountMap == null){
            this.Mapify(txt);
        }else{
            wordCount = (HashMap<String, Integer>) wordCountMap;
        }
    }
    public DocumentImpl(URI uri, byte[] binaryData){
        if(uri == null || binaryData == null){
            throw new IllegalArgumentException("need to input non null values");
        }
        this.binaryData = binaryData;
        this.uri = uri;
        this.text = null;
    }
    /**
     * @return content of text document
     */
    @Override
    public String getDocumentTxt(){
        return text;
    }
    /**
     * @return content of binary data document
     */
    @Override
    public byte[] getDocumentBinaryData(){
        return binaryData;
    }
    /**
     * @return URI which uniquely identifies this document
     */
    @Override
    public URI getKey(){
        return uri;
    }
    /**
     * how many times does the given word appear in the document?
     * @param word
     * @return the number of times the given words appears in the document. If it's a binary document, return 0.
     */
    @Override
    public int wordCount(String word){
        if(this.text == null){return 0;}
        String ourDocEdited = word.replaceAll("[^A-Za-z0-9 ]", "");
        return wordCount.getOrDefault(ourDocEdited, 0);
    }
    /**
     * @return all the words that appear in the document
     */
    @Override
    public Set<String> getWords(){
        HashSet<String> returnSet = new HashSet<>();
        if(this.text == null){return returnSet;}
        String ourDocEdited = this.text.replaceAll("[^A-Za-z0-9 ]", "");
        String[] chopped = ourDocEdited.split(" ");
        for(String s : chopped){
            returnSet.add(s);
        }
        return returnSet;
    }
    /**
     * return the last time this document was used, via put/get or via a search result
     * (for stage 4 of project)
     */
    @Override
    public long getLastUseTime(){
        return lastTime;
    }
    @Override
    public void setLastUseTime(long timeInNanoseconds){
        this.lastTime = timeInNanoseconds;
    }
    @Override
    public int hashCode(){
        int result = uri.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(binaryData);
        return Math.abs(result);
    }
    @Override
    public boolean equals(Object doc){
        if(doc.hashCode() == this.hashCode()){
            return true;
        }else{
            return false;
        }
    }
    private void Mapify(String text){
        String newText = text.replaceAll("[^A-Za-z0-9 ]", "");
        String[] chopped = newText.split(" ");
        for(String s : chopped){
            if(wordCount.containsKey(s)){
                wordCount.put(s, wordCount.get(s)+1);
            }else{
                wordCount.put(s, 1);
            }
        }
    }
    /**
     * @return a copy of the word to count map so it can be serialized
     */
    public Map<String,Integer> getWordMap(){
        return Collections.unmodifiableMap(wordCount);
    }

    /**
     * This must set the word to count map during deserialization
     * @param wordMap
     */
    public void setWordMap(Map<String,Integer> wordMap){
        this.wordCount = (HashMap<String, Integer>) wordMap;
    }
=======
package edu.yu.cs.com1320.project.stage5.impl;
import edu.yu.cs.com1320.project.stage5.Document;
import java.util.*;
import java.net.URI;

public class DocumentImpl implements Document{
    private URI uri;
    private String text;
    private byte[] binaryData;
    private Long lastTime;
    private HashMap<String, Integer> wordCount = new HashMap<>();

    @Override
    public int compareTo(Document o) {
        return lastTime.compareTo(o.getLastUseTime());
    }

    public DocumentImpl(URI uri, String txt, Map<String, Integer> wordCountMap){
        if(uri == null || txt == null){
            throw new IllegalArgumentException("need to input non null values");
        }
        this.uri = uri;
        this.text = txt;
        this.binaryData = null;
        if(wordCountMap == null){
            this.Mapify(txt);
        }else{
            wordCount = (HashMap<String, Integer>) wordCountMap;
        }
    }
    public DocumentImpl(URI uri, byte[] binaryData){
        if(uri == null || binaryData == null){
            throw new IllegalArgumentException("need to input non null values");
        }
        this.binaryData = binaryData;
        this.uri = uri;
        this.text = null;
    }
    /**
     * @return content of text document
     */
    @Override
    public String getDocumentTxt(){
        return text;
    }
    /**
     * @return content of binary data document
     */
    @Override
    public byte[] getDocumentBinaryData(){
        return binaryData;
    }
    /**
     * @return URI which uniquely identifies this document
     */
    @Override
    public URI getKey(){
        return uri;
    }
    /**
     * how many times does the given word appear in the document?
     * @param word
     * @return the number of times the given words appears in the document. If it's a binary document, return 0.
     */
    @Override
    public int wordCount(String word){
        if(this.text == null){return 0;}
        String ourDocEdited = word.replaceAll("[^A-Za-z0-9 ]", "");
        return wordCount.getOrDefault(ourDocEdited, 0);
    }
    /**
     * @return all the words that appear in the document
     */
    @Override
    public Set<String> getWords(){
        HashSet<String> returnSet = new HashSet<>();
        if(this.text == null){return returnSet;}
        String ourDocEdited = this.text.replaceAll("[^A-Za-z0-9 ]", "");
        String[] chopped = ourDocEdited.split(" ");
        for(String s : chopped){
            returnSet.add(s);
        }
        return returnSet;
    }
    /**
     * return the last time this document was used, via put/get or via a search result
     * (for stage 4 of project)
     */
    @Override
    public long getLastUseTime(){
        return lastTime;
    }
    @Override
    public void setLastUseTime(long timeInNanoseconds){
        this.lastTime = timeInNanoseconds;
    }
    @Override
    public int hashCode(){
        int result = uri.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(binaryData);
        return Math.abs(result);
    }
    @Override
    public boolean equals(Object doc){
        if(doc.hashCode() == this.hashCode()){
            return true;
        }else{
            return false;
        }
    }
    private void Mapify(String text){
        String newText = text.replaceAll("[^A-Za-z0-9 ]", "");
        String[] chopped = newText.split(" ");
        for(String s : chopped){
            if(wordCount.containsKey(s)){
                wordCount.put(s, wordCount.get(s)+1);
            }else{
                wordCount.put(s, 1);
            }
        }
    }
    /**
     * @return a copy of the word to count map so it can be serialized
     */
    public Map<String,Integer> getWordMap(){
        return Collections.unmodifiableMap(wordCount);
    }

    /**
     * This must set the word to count map during deserialization
     * @param wordMap
     */
    public void setWordMap(Map<String,Integer> wordMap){
        this.wordCount = (HashMap<String, Integer>) wordMap;
    }
>>>>>>> fd77063a3d4afb76b6777a38f3f83134aeed8ddf
}