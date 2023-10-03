package edu.yu.cs.com1320.project.stage5.impl;
import com.google.gson.*;
import edu.yu.cs.com1320.project.stage5.Document;
import edu.yu.cs.com1320.project.stage5.*;
import edu.yu.cs.com1320.project.stage5.PersistenceManager;
import jakarta.xml.bind.DatatypeConverter;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * created by the document store and given to the BTree via a call to BTree.setPersistenceManager
 */
public class DocumentPersistenceManager implements PersistenceManager<URI, Document> {
    private File baseDir;
    private final String separator = File.separator;

    public DocumentPersistenceManager(File baseDir){
        this.baseDir = baseDir;
    }
    private String getFilePath(URI uri){
        String urijson = uri.toString().replace(uri.getScheme(), "").replace("//", "").replaceAll("[^a-zA-Z0-9._\\/]", "").replace("/", separator).replace("\\", separator);
        String FilePath;
        if(baseDir == null){
            FilePath = System.getProperty("user.dir")+separator+urijson+".json";
        }else{
            FilePath = baseDir+separator+urijson+".json";
        }
        return FilePath;
    }
    @Override
    public void serialize(URI uri, Document val) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        JsonSerializer<Document> ourSerializer = new Serialize<Document>();
        Gson ourGson = builder.registerTypeAdapter(DocumentImpl.class, ourSerializer).create();
        if(uri == null || val == null){
            throw new IllegalArgumentException();
        }
        File newFile = new File(this.getFilePath(uri));
        newFile.getParentFile().mkdirs();
        FileWriter ourFileWriter = new FileWriter(newFile);
        String ourJson = ourGson.toJson(val);
        ourFileWriter.write(ourJson);
        ourFileWriter.close();
    }
    @Override
    public DocumentImpl deserialize(URI uri) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        JsonDeserializer<Document> ourSerializer = new Deserialize<Document>();
        Gson ourGson = builder.registerTypeAdapter(DocumentImpl.class, ourSerializer).create();
        if(uri == null){
            throw new IllegalArgumentException();
        }
        File ourFile = new File(this.getFilePath(uri));
        FileReader ourReader = new FileReader(ourFile);
        DocumentImpl ourDoc = ourGson.fromJson(ourReader, DocumentImpl.class);
        ourReader.close();
        ourFile.delete();
        return ourDoc;
    }


    private class Serialize<Document> implements JsonSerializer<edu.yu.cs.com1320.project.stage5.Document>{
        @Override
        public JsonElement serialize(edu.yu.cs.com1320.project.stage5.Document document, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject ourJason = new JsonObject();
            DocumentImpl ourDoc = (DocumentImpl) document;
            GsonBuilder builder = new GsonBuilder();
            Gson ourGson = builder.create();
            ourJason.add("uri", ourGson.toJsonTree(document.getKey(), URI.class));
            if(ourDoc.getDocumentTxt() != null){
                JsonElement StringJson = ourGson.toJsonTree(ourDoc.getDocumentTxt(), String.class);
                ourJason.add("text", StringJson);
                JsonElement MapJson = ourGson.toJsonTree(ourDoc.getWordMap(), HashMap.class);
                ourJason.add("map", MapJson);
            }else{
                String byteString = DatatypeConverter.printBase64Binary(ourDoc.getDocumentBinaryData());
                JsonElement ByteJson = ourGson.toJsonTree(byteString, String.class);
                ourJason.add("bytes", ByteJson);
            }
            return ourJason;
        }

    }

    private class Deserialize<Document> implements JsonDeserializer<edu.yu.cs.com1320.project.stage5.Document>{
        @Override
        public edu.yu.cs.com1320.project.stage5.Document deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            GsonBuilder builder = new GsonBuilder();
            Gson ourGson = builder.create();
            URI uri;
            String documentText;
            HashMap<String, Integer> ourMap;
            byte[] ourBytes;
            DocumentImpl ourDoc;
            JsonObject ourJson = jsonElement.getAsJsonObject();
            JsonElement uriElement = ourJson.get("uri");
            try {
                uri = new URI(uriElement.getAsString());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            if(ourJson.has("text")){
                JsonElement textElement = ourJson.get("text");
                documentText = ourGson.fromJson(textElement, String.class);
                JsonElement mapElement = ourJson.get("map");
                ourMap = ourGson.fromJson(mapElement, HashMap.class);
                ourDoc =  new DocumentImpl(uri, documentText, ourMap);
            }else{
                JsonElement byteElement = ourJson.get("bytes");
                String byteString = ourGson.fromJson(byteElement, String.class);
                ourBytes = DatatypeConverter.parseBase64Binary(byteString);
                ourDoc =  new DocumentImpl(uri, ourBytes);
            }
            return ourDoc;
        }
    }
    @Override
    public boolean delete(URI uri) throws IOException {
        File newFile = new File(this.getFilePath(uri));
        boolean d = newFile.delete();
        return d;
    }
}