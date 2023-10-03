import edu.yu.cs.com1320.project.BTree;
import edu.yu.cs.com1320.project.MinHeap;
import edu.yu.cs.com1320.project.Trie;
import edu.yu.cs.com1320.project.impl.BTreeImpl;
import edu.yu.cs.com1320.project.impl.MinHeapImpl;
import edu.yu.cs.com1320.project.impl.StackImpl;
import edu.yu.cs.com1320.project.impl.TrieImpl;
import edu.yu.cs.com1320.project.stage5.Document;
import edu.yu.cs.com1320.project.stage5.DocumentStore;
import edu.yu.cs.com1320.project.stage5.impl.DocumentStoreImpl;
import edu.yu.cs.com1320.project.stage5.impl.DocumentPersistenceManager;
import edu.yu.cs.com1320.project.stage5.impl.DocumentImpl;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.URI;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestStage5 {
    private DocumentPersistenceManager ourDPMUSER;
    private DocumentPersistenceManager ourBase;
    private URI ourURI1;
    private URI ourURI2;
    private URI ourURI3;
    private URI ourURI4;
    private URI ourURI5;
    private URI ourURI6;
    private URI ourURI7;
    private URI ourURI8;
    private URI ourURI9;
    private URI ourURI10;
    private URI ourURI11;
    private URI ourURI12;
    private URI ourURI13;
    private URI ourURI14;
    private URI ourURI15;
    private URI ourURI16;
    private URI ourURI17;
    private URI ourURI18;
    private URI ourURI19;
    private URI ourURI20;
    private URI ourURI21;
    private URI ourURI22;
    private URI ourURI23;
    private URI ourURI24;
    private URI ourURI25;
    private Document doc1;
    private Document doc2;
    private Document doc3;
    private Document doc4;
    private Document doc5;
    private Document doc6;
    private Document doc7;
    private Document doc8;
    private Document doc9;
    private Document doc10;
    private Document doc11;
    private Document doc12;
    private Document doc13;
    private Document doc14;
    private Document doc15;
    private Document doc16;
    private Document doc17;
    private Document doc18;
    private Document doc19;
    private Document doc20;
    private Document doc21;
    private Document doc22;
    private Document doc23;
    private Document doc24;
    private Document doc25;
    private URI uri1;
    private String txt1;

    //variables to hold possible values for doc2
    private URI uri2;
    private String txt2;

    //variables to hold possible values for doc3
    private URI uri3;
    private String txt3;

    //variables to hold possible values for doc4
    private URI uri4;
    private String txt4;

    private int bytes1;
    private int bytes2;
    private int bytes3;
    private int bytes4;
    private BTree<URI, Document> ourBTree;
    @BeforeEach
    public void init() throws Exception{
        //init possible values for doc1
        this.uri1 = new URI("http://edu.yu.cs/com1320/project/doc1");
        this.txt1 = "This doc1 plain text string Computer Headphones";

        //init possible values for doc2
        this.uri2 = new URI("http://edu.yu.cs/com1320/project/doc2");
        this.txt2 = "Text doc2 plain String";

        //init possible values for doc3
        this.uri3 = new URI("http://edu.yu.cs/com1320/project/doc3");
        this.txt3 = "This is the text of doc3";

        //init possible values for doc4
        this.uri4 = new URI("http://edu.yu.cs/com1320/project/doc4");
        this.txt4 = "This is the text of doc4";

        this.bytes1 = this.txt1.getBytes().length;
        this.bytes2 = this.txt2.getBytes().length;
        this.bytes3 = this.txt3.getBytes().length;
        this.bytes4 = this.txt4.getBytes().length;
        ourBTree = new BTreeImpl<URI, Document>();
        ourDPMUSER = new DocumentPersistenceManager(new File(System.getProperty("user.dir")));
        ourBase = new DocumentPersistenceManager(null);
        ourURI1 = URI.create("https://www.microsoft.com");
        ourURI2 = URI.create("https://www.google.com");
        ourURI3 = URI.create("https://www.meta.com");
        ourURI4 = URI.create("https://www.lego.com");
        ourURI5 = URI.create("https://www.amazon.com");
        ourURI6 = URI.create("https://www.apple.com");
        ourURI7 = URI.create("https://www.orange.com");
        ourURI8 = URI.create("https://www.banana.com");
        ourURI9 = URI.create("https://www.pear.com");
        ourURI10 = URI.create("https://www.pineapple.com");
        ourURI11 = URI.create("https://www.persimon.com");
        ourURI12 = URI.create("https://www.qwe.com");
        ourURI13 = URI.create("https://www.inven.com");
        ourURI14 = URI.create("https://www.bread.com");
        ourURI15 = URI.create("https://www.computer.com");
        ourURI16 = URI.create("https://www.qwertyui.com");
        ourURI17 = URI.create("https://www.fruitsalad.com");
        ourURI18 = URI.create("https://www.vegetable.com");
        ourURI19 = URI.create("https://www.grapes1.com");
        ourURI20 = URI.create("https://www.grapes2.com");
        ourURI21 = URI.create("https://www.grapes3.com");
        ourURI22 = URI.create("https://www.grapes4.com");
        ourURI23 = URI.create("https://www.grapes5.com");
        ourURI24 = URI.create("https://www.grapes6.com");
        ourURI25 = URI.create("https://www.grapes7.com");
        doc1 = new DocumentImpl(ourURI1, "microsoft is is onis is the only apple company that on makes", null);
        doc2 = new DocumentImpl(ourURI2, "mocrosoft is the only company that is able to make pcos", null);
        doc3 = new DocumentImpl(ourURI3, "mocrosoft: is the onicnwincwi onfiwu only only only", null);
        doc4 = new DocumentImpl(ourURI4, "oiuytiuf iufvu oiwn iun ixnf oiwu frew", null);
        doc5 = new DocumentImpl(ourURI5, "oiuyt sd cccccc cwdsxxd x c dcdsad rew", null);
        doc6 = new DocumentImpl(ourURI6, "We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights", null);
        doc7 = new DocumentImpl(ourURI7, "we love all the flavors of orages", null);
        doc8 = new DocumentImpl(ourURI8, "microsoft is is onis is the only apple company that on makes bananas", null);
        doc9 = new DocumentImpl(ourURI9, "microsoft is is onis is the only apple company that on makes pears", null);
        doc10 = new DocumentImpl(ourURI10, "microsoft is is onis is the only apple company that on makes grapes", null);
        doc11 = new DocumentImpl(ourURI11, "document 11 text tcivubinj", null);
        doc12 = new DocumentImpl(ourURI12, "inv evneivnd oijdn coijfdninjv", null);
        doc13 = new DocumentImpl(ourURI13, "ncieneincieninoinjv iencis mccneoini uncs", null);
        doc14 = new DocumentImpl(ourURI14, "qwertyuiopasdfghjklzxcvbnm", null);
        doc15 = new DocumentImpl(ourURI15, "mnbvcxzlkjhgfdsapoiuytrewq", null);
        doc16 = new DocumentImpl(ourURI16, "noisnoicnus iunciu ncins dini sajnpacn pned", null);
        doc17 = new DocumentImpl(ourURI17, "jn oinin isn in inqnciqnc inqoindc qincnc noindc jn", null);
        doc18 = new DocumentImpl(ourURI18, "nin oidinj ocinj n isvnoinw ijn cisnoin ncnijd nc", null);
        doc19 = new DocumentImpl(ourURI19, "ojen vdnvoinv oidwn oivne ionvivn ewoiv oiv nein in", null);
        doc20 = new DocumentImpl(ourURI20, "kjd d oid iv neoivneoif voie veoiv eoie", null);
        doc21 = new DocumentImpl(ourURI21, "e oi voinoievn xiev ieobv oiebv iebv eoibv", null);
        doc22 = new DocumentImpl(ourURI22, "wertsdfgjkvbyui jcneoijncec", null);
        doc23 = new DocumentImpl(ourURI23, "ceb wb xev onvoiew ndwoi noi ne2oiv neoiv e", null);
        doc24 = new DocumentImpl(ourURI24, "ev bo vv eouvevoue oie veoiv beoi vdw k", null);
        doc25 = new DocumentImpl(ourURI25, "ub oieboe beoivbeoiv eivb eoi veov be iv", null);
    }
    @AfterEach
    public void deleteall() throws IOException {
        this.deleteFiles();
    }
    @Test
    public void deleteFiles() throws IOException {
        ourDPMUSER.delete(ourURI1);
        ourDPMUSER.delete(uri1);
        ourDPMUSER.delete(uri2);
        ourDPMUSER.delete(uri3);
        ourDPMUSER.delete(uri4);
        ourDPMUSER.delete(ourURI2);
        ourDPMUSER.delete(ourURI3);
        ourDPMUSER.delete(ourURI4);
        ourDPMUSER.delete(ourURI5);
        ourDPMUSER.delete(ourURI6);
        ourDPMUSER.delete(ourURI7);
        ourDPMUSER.delete(ourURI8);
        ourDPMUSER.delete(ourURI9);
        ourDPMUSER.delete(ourURI10);
        ourDPMUSER.delete(ourURI11);
        ourDPMUSER.delete(ourURI12);
        ourDPMUSER.delete(ourURI13);
        ourDPMUSER.delete(ourURI14);
        ourDPMUSER.delete(ourURI15);
        ourDPMUSER.delete(ourURI16);
        ourDPMUSER.delete(ourURI17);
        ourDPMUSER.delete(ourURI18);
        ourDPMUSER.delete(ourURI19);
        ourDPMUSER.delete(ourURI20);
        ourDPMUSER.delete(ourURI21);
        ourDPMUSER.delete(ourURI22);
        ourDPMUSER.delete(ourURI23);
        ourDPMUSER.delete(ourURI24);
        ourDPMUSER.delete(ourURI25);
        ourBase.delete(ourURI1);
        ourBase.delete(ourURI2);
        ourBase.delete(ourURI3);
        ourBase.delete(ourURI4);
        ourBase.delete(ourURI5);
        ourBase.delete(ourURI6);
        ourBase.delete(ourURI7);
        ourBase.delete(ourURI8);
        ourBase.delete(ourURI9);
        ourBase.delete(ourURI10);
        ourBase.delete(ourURI11);
        ourBase.delete(ourURI12);
        ourBase.delete(ourURI13);
        ourBase.delete(ourURI14);
        ourBase.delete(ourURI15);
        ourBase.delete(ourURI16);
        ourBase.delete(ourURI17);
        ourBase.delete(ourURI18);
        ourBase.delete(ourURI19);
        ourBase.delete(ourURI20);
        ourBase.delete(ourURI21);
        ourBase.delete(ourURI22);
        ourBase.delete(ourURI23);
        ourBase.delete(ourURI24);
        ourBase.delete(ourURI25);
    }
    @Test
    public void DPMTEST() throws IOException {
        ourDPMUSER.serialize(ourURI1, doc1);
        DocumentImpl returnDoc = ourDPMUSER.deserialize(ourURI1);
        assertEquals(returnDoc.getDocumentTxt(), doc1.getDocumentTxt());
    }
    @Test
    public void BTreeTEst1() throws Exception{
        ourBTree.setPersistenceManager(ourBase);
        doc1.setLastUseTime(4);
        ourBTree.put(ourURI1, doc1);
        ourBTree.put(ourURI2, doc2);
        ourBTree.put(ourURI3, doc3);
        ourBTree.put(ourURI4, doc4);
        ourBTree.put(ourURI5, doc5);
        ourBTree.put(ourURI6, doc6);
        Document texts = ourBTree.get(ourURI1);
        assertEquals(doc1.getDocumentTxt(), texts.getDocumentTxt());
        assertEquals(4, texts.getLastUseTime());
        ourBTree.moveToDisk(ourURI2);
        Document texts2 = ourBTree.get(ourURI2);
        assertEquals(doc2.getDocumentTxt(), texts2.getDocumentTxt());
        ourBTree.put(ourURI2, doc3);
        Document texts3 = ourBTree.get(ourURI2);
        assertEquals(doc3.getDocumentTxt(), texts3.getDocumentTxt());
        ourBTree.moveToDisk(ourURI1);
        ourBTree.moveToDisk(ourURI2);
        ourBTree.moveToDisk(ourURI3);
        ourBTree.moveToDisk(ourURI4);
        ourBTree.moveToDisk(ourURI5);
        ourBTree.moveToDisk(ourURI6);
        ourBTree.put(ourURI7, doc7);
        ourBTree.put(ourURI8, doc8);
        ourBTree.put(ourURI9, doc9);
        ourBTree.put(ourURI10, doc10);
        ourBTree.moveToDisk(ourURI7);
        ourBTree.moveToDisk(ourURI10);
        Document text3 = ourBTree.get(ourURI10);
        assertEquals(text3.getDocumentTxt(), "microsoft is is onis is the only apple company that on makes grapes");
        ourBTree.put(ourURI10, doc10);
        ourBTree.put(ourURI11, doc11);
        ourBTree.put(ourURI12, doc12);
        ourBTree.put(ourURI13, doc13);
        ourBTree.put(ourURI14, doc14);
        ourBTree.put(ourURI15, doc15);
        ourBTree.put(ourURI16, doc16);
        ourBTree.put(ourURI17, doc17);
        ourBTree.put(ourURI18, doc18);
        ourBTree.put(ourURI19, doc19);
        ourBTree.put(ourURI20, doc20);
        ourBTree.put(ourURI21, doc21);
        ourBTree.put(ourURI22, doc22);
        ourBTree.put(ourURI23, doc23);
        ourBTree.put(ourURI24, doc24);
        ourBTree.put(ourURI25, doc25);
        ourBTree.moveToDisk(ourURI22);
        ourBTree.moveToDisk(ourURI21);
        ourBTree.moveToDisk(ourURI25);
        ourBTree.moveToDisk(ourURI19);
        ourBTree.moveToDisk(ourURI16);
        Document text4 = ourBTree.get(ourURI21);
        assertEquals(text4.getDocumentTxt(), "e oi voinoievn xiev ieobv oiebv iebv eoibv");
        Document text5 = ourBTree.get(ourURI17);
        assertEquals(text5.getDocumentTxt(), "jn oinin isn in inqnciqnc inqoindc qincnc noindc jn");
    }
    @Test
    public void firstDStest1() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string1 = "microsoft is is onis is the only apple company that on makes";
        String string2 = "mocrosoft is the only company that is able to make pcos";
        String string3 = "mocrosoft: is the onicnwincwi onfiwu only only only";
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream(string1);
        InputStream input2 = new StringBufferInputStream(string2);
        InputStream input3 = new StringBufferInputStream(string3);
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.BINARY);
        ourImpl.setMaxDocumentCount(0);
        assertEquals("microsoft is is onis is the only apple company that on makes", ourImpl.get(ourURI1).getDocumentTxt());
        boolean hi = false;
    }
    @Test
    public void stage4TestMaxDocBytesViaSearch() throws IOException {
        DocumentStore store = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        store.setMaxDocumentBytes(this.bytes1 + this.bytes2 + this.bytes3 + 10);
        store.put(new ByteArrayInputStream(this.txt1.getBytes()), this.uri1, DocumentStore.DocumentFormat.TXT);
        store.put(new ByteArrayInputStream(this.txt2.getBytes()), this.uri2, DocumentStore.DocumentFormat.TXT);
        store.put(new ByteArrayInputStream(this.txt3.getBytes()), this.uri3, DocumentStore.DocumentFormat.TXT);
        //all 3 should still be in memory
        assertNotNull(store.get(this.uri1),"uri1 should still be in memory");
        assertNotNull(store.get(this.uri2),"uri2 should still be in memory");
        assertNotNull(store.get(this.uri3),"uri3 should still be in memory");
        //"touch" uri1 via a search
        store.search("doc1");
        //add doc4, doc2 should be pushed out, not doc1
        store.put(new ByteArrayInputStream(this.txt4.getBytes()), this.uri4, DocumentStore.DocumentFormat.TXT);
        assertNotNull(store.get(this.uri1),"uri1 should still be in memory");
        assertNotNull(store.get(this.uri3),"uri3 should still be in memory");
        assertNotNull(store.get(this.uri4),"uri4 should still be in memory");
        //uri2 should've been pushed out of memory
        assertNotNull(store.get(this.uri2),"uri2 should've been pushed out memory");
    }
    @Test
    void stage4TestUndoAfterMaxBytes() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string1 = "microsoft is is onis is the only apple company that on makes";
        String string2 = "mocrosoft is the only company that is able to make pcos";
        String string3 = "mocrosoft: is the onicnwincwi onfiwu only only only";
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream(string1);
        InputStream input2 = new StringBufferInputStream(string2);
        InputStream input3 = new StringBufferInputStream(string3);
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.BINARY);
        assertNotNull(ourImpl.get(ourURI1));
        assertNotNull(ourImpl.get(ourURI2));
        assertNotNull(ourImpl.get(ourURI3));
        assertNotNull(ourImpl.get(ourURI4));
        assertNotNull(ourImpl.get(ourURI5));
        ourImpl.setMaxDocumentBytes(string4.length()+string5.length()+string1.length()+string2.length()+string3.length());
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.BINARY);
        assertNotNull(ourImpl.get(ourURI1));
        assertNotNull(ourImpl.get(ourURI2));
        assertNotNull(ourImpl.get(ourURI3));
        assertNotNull(ourImpl.get(ourURI4));
        assertNotNull(ourImpl.get(ourURI5));
        assertNotNull(ourImpl.get(ourURI6));
    }
    @Test
    void searcherrorprifix() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream("microsoft is is onis is the only company that on makes");
        InputStream input2 = new StringBufferInputStream("mocrosoft is the only company that is able to make pcos");
        InputStream input3 = new StringBufferInputStream("mocrosoft: is the onicnwincwi onfiwu only only only");
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        List<Document> returnList = ourImpl.searchByPrefix("ab");
        assertEquals(1, returnList.size());
    }
    @Test
    void stage3DeleteAllExists() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream("microsoft is is onis is the only company that on makes");
        InputStream input2 = new StringBufferInputStream("mocrosoft is the only company that is able to make pcos");
        InputStream input3 = new StringBufferInputStream("mocrosoft: is the onicnwincwi onfiwu only only only");
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.setMaxDocumentCount(0);
        ourImpl.deleteAll("is");
    }
    @Test
    void putNewVersion() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.micrdosoft.com");
        URI ourURI2 = URI.create("https://www.goodcgle.com");
        URI ourURI3 = URI.create("https://www.meedta.com");
        URI ourURI4 = URI.create("https://www.legooo.com");
        URI ourURI5 = URI.create("https://www.ama_cdzon.com");
        URI ourURI6 = URI.create("https://www.appldee.com");
        String string1 = "ouencun cec neo ncowcn wocn";
        String string2 = "owncoeneoveno o oeoimco wc";
        String string3 = "njceonoicin woicn inciownic ownc";
        String string4 = "lego is the ihf best";
        String string5 = "lego amazon onj is is ccw camazing";
        String string6 = "lego apple was a dc cwcdmazing";
        byte[] byte1 = string1.getBytes();
        byte[] byte2 = string2.getBytes();
        byte[] byte3 = string3.getBytes();
        InputStream input1 = new ByteArrayInputStream(byte1);
        InputStream input2 = new ByteArrayInputStream(byte2);
        InputStream input3 = new ByteArrayInputStream(byte3);
        InputStream input4 = new StringBufferInputStream(string4);
        InputStream input5 = new StringBufferInputStream(string5);
        InputStream input6 = new StringBufferInputStream(string6);
        DocumentImpl newDoc = new DocumentImpl(ourURI4, "lego is the ihf best", null);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.TXT);
        assertEquals(newDoc.hashCode(), ourImpl.put(input5, ourURI4, DocumentStore.DocumentFormat.TXT));
        assertEquals("lego amazon onj is is ccw camazing", ourImpl.get(ourURI4).getDocumentTxt());
    }
    @Test
    void heapTst5_undo() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.micrdosoft.com");
        URI ourURI2 = URI.create("https://www.goodcgle.com");
        URI ourURI3 = URI.create("https://www.meedta.com");
        URI ourURI4 = URI.create("https://www.legooo.com");
        URI ourURI5 = URI.create("https://www.ama_cdzon.com");
        URI ourURI6 = URI.create("https://www.appldee.com");
        String string1 = "ouencun cec neo ncowcn wocn";
        String string2 = "owncoeneoveno o oeoimco wc";
        String string3 = "njceonoicin woicn inciownic ownc";
        String string4 = "lego is the ihf best";
        String string5 = "lego amazon onj is is ccw camazing";
        String string6 = "lego apple was a dc cwcdmazing";
        byte[] byte1 = string1.getBytes();
        byte[] byte2 = string2.getBytes();
        byte[] byte3 = string3.getBytes();
        InputStream input1 = new ByteArrayInputStream(byte1);
        InputStream input2 = new ByteArrayInputStream(byte2);
        InputStream input3 = new ByteArrayInputStream(byte3);
        InputStream input4 = new StringBufferInputStream(string4);
        InputStream input5 = new StringBufferInputStream(string5);
        InputStream input6 = new StringBufferInputStream(string6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.TXT);
        ourImpl.deleteAll("lego");
        ourImpl.setMaxDocumentCount(2);
        ourImpl.undo();
        ourImpl.delete(ourURI1);
        ourImpl.undo();
    }
    @Test
    void stage5PushToDiskViaMaxDocCountBringBackInViaDeleteAndSearch() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.micrdosoft.com");
        URI ourURI2 = URI.create("https://www.goodcgle.com");
        URI ourURI3 = URI.create("https://www.meedta.com");
        URI ourURI4 = URI.create("https://www.legooo.com");
        URI ourURI5 = URI.create("https://www.ama_cdzon.com");
        URI ourURI6 = URI.create("https://www.appldee.com");
        String string1 = "ouencun cec neo ncowcn wocn";
        String string2 = "owncoeneoveno o oeoimco wc";
        String string3 = "njceonoicin woicn inciownic ownc";
        String string4 = "lego is the ihf best";
        String string5 = "lego amazon onj is is ccw camazing";
        String string6 = "lego apple was a dc cwcdmazing";
        byte[] byte1 = string1.getBytes();
        byte[] byte2 = string2.getBytes();
        byte[] byte3 = string3.getBytes();
        InputStream input1 = new ByteArrayInputStream(byte1);
        InputStream input2 = new ByteArrayInputStream(byte2);
        InputStream input3 = new ByteArrayInputStream(byte3);
        InputStream input4 = new StringBufferInputStream(string4);
        InputStream input5 = new StringBufferInputStream(string5);
        InputStream input6 = new StringBufferInputStream(string6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.TXT);
        ourImpl.setMaxDocumentCount(1);
        ourImpl.delete(ourURI6);
        ArrayList<Document> ourDocs = (ArrayList<Document>) ourImpl.search("ihf");
        assertEquals("lego is the ihf best", ourDocs.get(0).getDocumentTxt());
        boolean exist = new File(System.getProperty("user.dir") + File.separator + "www.legooo.com.json").exists();
        assertFalse(exist);
    }
    @Test
    void stage5PushToDiskViaMaxDocCountViaUndoDelete() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.micrdosoft.com");
        URI ourURI2 = URI.create("https://www.goodcgle.com");
        URI ourURI3 = URI.create("https://www.meedta.com");
        URI ourURI4 = URI.create("https://www.legooo.com");
        URI ourURI5 = URI.create("https://www.ama_cdzon.com");
        URI ourURI6 = URI.create("https://www.appldee.com");
        String string1 = "ouencun cec neo ncowcn wocn";
        String string2 = "owncoeneoveno o oeoimco wc";
        String string3 = "njceonoicin woicn inciownic ownc";
        String string4 = "lego is the ihf best";
        String string5 = "lego amazon onj is is ccw camazing";
        String string6 = "lego apple was a dc cwcdmazing";
        byte[] byte1 = string1.getBytes();
        byte[] byte2 = string2.getBytes();
        byte[] byte3 = string3.getBytes();
        InputStream input1 = new ByteArrayInputStream(byte1);
        InputStream input2 = new ByteArrayInputStream(byte2);
        InputStream input3 = new ByteArrayInputStream(byte3);
        InputStream input4 = new StringBufferInputStream(string4);
        InputStream input5 = new StringBufferInputStream(string5);
        InputStream input6 = new StringBufferInputStream(string6);
        ourImpl.setMaxDocumentCount(1);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.TXT);
        ourImpl.delete(ourURI4);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.TXT);
        ourImpl.undo(ourURI4);
    }
    @Test
    void stage5PushToDiskViaMaxDocCount() throws IOException {
        DocumentStoreImpl store = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        store.setMaxDocumentCount(2);
        pushAboveMaxViaPutNew(store);
    }
    private void pushAboveMaxViaPutNew(DocumentStoreImpl store) throws IOException{
        try{
            store.put(new ByteArrayInputStream(this.txt1.getBytes()), this.uri1, DocumentStore.DocumentFormat.TXT);
            store.put(new ByteArrayInputStream(this.txt2.getBytes()), this.uri2, DocumentStore.DocumentFormat.TXT);
            Document doc1 = store.get(this.uri1);
            Document doc2 = store.get(this.uri2);
            store.put(new ByteArrayInputStream(this.txt3.getBytes()), this.uri3, DocumentStore.DocumentFormat.TXT);
            //At this point 2 and 3 should be in memory and 1 should be on disk pushed out when three was inputted.
            store.search("doc1");
            //doc 1 should be in memory and 2 should be serialized
            Document doc1v2 = store.get(this.uri1);
            boolean exist = new File(System.getProperty("user.dir") + File.separator + "edu.yu.cs\\com1320\\project\\doc2.json").exists();
            assertTrue(exist);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void stage3UndoByURIThatImpactsEarlierThanLast() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.micrdosoft.com");
        URI ourURI2 = URI.create("https://www.goodcgle.com");
        URI ourURI3 = URI.create("https://www.meedta.com");
        URI ourURI4 = URI.create("https://www.legooo.com");
        URI ourURI5 = URI.create("https://www.ama_cdzon.com");
        URI ourURI6 = URI.create("https://www.appldee.com");
        String string1 = "ouencun cec neo ncowcn wocn";
        String string2 = "owncoeneoveno o oeoimco wc";
        String string3 = "njceonoicin woicn inciownic ownc";
        String string4 = "lego is the ihf best";
        String string5 = "lego amazon onj is is ccw camazing";
        String string6 = "lego apple was a dc cwcdmazing";
        byte[] byte1 = string1.getBytes();
        byte[] byte2 = string2.getBytes();
        byte[] byte3 = string3.getBytes();
        InputStream input1 = new ByteArrayInputStream(byte1);
        InputStream input2 = new ByteArrayInputStream(byte2);
        InputStream input3 = new ByteArrayInputStream(byte3);
        InputStream input4 = new StringBufferInputStream(string4);
        InputStream input5 = new StringBufferInputStream(string5);
        InputStream input6 = new StringBufferInputStream(string6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.TXT);
        ourImpl.delete(ourURI1);
        ourImpl.delete(ourURI2);
        ourImpl.delete(ourURI5);
        ourImpl.delete(ourURI4);
        ourImpl.undo(ourURI1);
    }

    @Test
    void undoAfterMultiplePuts() throws IOException {
        DocumentStore ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.micrdosoft.com");
        URI ourURI2 = URI.create("https://www.goodcgle.com");
        URI ourURI3 = URI.create("https://www.meedta.com");
        URI ourURI4 = URI.create("https://www.legooo.com");
        URI ourURI5 = URI.create("https://www.ama_cdzon.com");
        URI ourURI6 = URI.create("https://www.appldee.com");
        String string1 = "ouencun cec neo ncowcn wocn";
        String string2 = "owncoeneoveno o oeoimco wc";
        String string3 = "njceonoicin woicn inciownic ownc";
        String string4 = "lego is the ihf best";
        String string5 = "lego amazon onj is is ccw camazing";
        String string6 = "lego apple was a dc cwcdmazing";
        byte[] byte1 = string1.getBytes();
        byte[] byte2 = string2.getBytes();
        byte[] byte3 = string3.getBytes();
        InputStream input1 = new ByteArrayInputStream(byte1);
        InputStream input2 = new ByteArrayInputStream(byte2);
        InputStream input3 = new ByteArrayInputStream(byte3);
        InputStream input4 = new StringBufferInputStream(string4);
        InputStream input5 = new StringBufferInputStream(string5);
        InputStream input6 = new StringBufferInputStream(string6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.TXT);
        ourImpl.undo();
        ourImpl.undo();
        ourImpl.undo();
        ourImpl.undo();
        boolean exist = new File(System.getProperty("user.dir") + File.separator + "www.micrdosoft.com.json").exists();
        assertFalse(exist);
        boolean exist2 = new File(System.getProperty("user.dir") + File.separator + "www.goodcgle.com.json").exists();
        assertFalse(exist2);
        boolean exist3 = new File(System.getProperty("user.dir") + File.separator + "www.legooo.com.json").exists();
        assertFalse(exist3);
        boolean exist4 = new File(System.getProperty("user.dir") + File.separator + "www.ama_cdzon.com.json").exists();
        assertFalse(exist4);
    }
    @Test
    void heapTst4_reheapify_method(){
        MinHeap<Document> ourheap = new MinHeapImpl<Document>();
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        doc1.setLastUseTime(1);
        doc2.setLastUseTime(2);
        doc3.setLastUseTime(3);
        doc4.setLastUseTime(4);
        doc5.setLastUseTime(5);
        doc6.setLastUseTime(6);
        ourheap.insert(doc2);
        ourheap.insert(doc3);
        ourheap.insert(doc4);
        ourheap.insert(doc5);
        ourheap.insert(doc6);
        boolean hi = false;
        try{
            ourheap.reHeapify(doc1);
        }catch(NoSuchElementException e){
            hi = true;
        }
        assertTrue(hi);
    }

    @Test
    void heapTst3_reheapify_method(){
        MinHeap<Document> ourheap = new MinHeapImpl<Document>();
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        doc1.setLastUseTime(1);
        doc2.setLastUseTime(2);
        doc3.setLastUseTime(3);
        doc4.setLastUseTime(4);
        doc5.setLastUseTime(5);
        doc6.setLastUseTime(6);
        ourheap.insert(doc1);
        ourheap.insert(doc2);
        ourheap.insert(doc3);
        ourheap.insert(doc4);
        ourheap.insert(doc5);
        ourheap.insert(doc6);
        assertEquals(doc1, ourheap.remove());
        assertEquals(doc2, ourheap.remove());
        doc5.setLastUseTime(1);
        ourheap.reHeapify(doc5);
        assertEquals(doc5, ourheap.remove());
    }

    @Test
    void commandSetStorageDeletion() throws IOException {
        DocumentStoreImpl ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream("microsoft is is onis is the only company that on makes");
        InputStream input2 = new StringBufferInputStream("microsoft is the only company that is able to make pcos");
        InputStream input3 = new StringBufferInputStream("microsoft: is the onicnwincwi onfiwu only only only");
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.setMaxDocumentCount(1);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.BINARY);
        assertEquals("microsoft is is onis is the only company that on makes", ourImpl.get(ourURI1).getDocumentTxt());
    }
    @Test
    void minHeaptest_1(){
        MinHeap<Integer> ourHeap = new MinHeapImpl<>();
        for(int i = 1; i<21; i++ ){
            ourHeap.insert(i);
        }
        assertEquals(1, ourHeap.remove());
        assertEquals(2, ourHeap.remove());

    }
    @Test
    void test1(){
        Trie<Integer> ourTree = new TrieImpl<>();
        ourTree.put("Hello", 5);
        assertEquals(1, 1);
    }
    @Test
    void test2BasicTree(){
        Trie<Integer> ourTree = new TrieImpl<>();
        ourTree.put("Hellaovervcvewc", 5);
        ourTree.put("Hellboexcex", 6);
        ourTree.put("Hellcoxwex", 7);
        ourTree.put("Helldorcccerc", 8);
        ourTree.put("Helleowxxw", 9);
        ourTree.put("Hellfoxwfx", 10);
        ourTree.put("Hellgowrbv", 11);
        ourTree.put("Hellgowrbv", 12);
        ourTree.put("Hellgowrbv", 13);
        class Compare implements Comparator<Integer>{
            public int compare(Integer a, Integer b){
                return a.compareTo(b);
            }
        }
        Comparator<Integer> Compare = new Compare();
        ArrayList<Integer> ourList = (ArrayList<Integer>) ourTree.getAllWithPrefixSorted("Hell", Compare);
        assertTrue(ourList.contains(10));
        assertTrue(ourList.contains(9));
        assertTrue(ourList.contains(11));
        assertTrue(ourList.contains(8));
        assertTrue(ourList.contains(7));
        assertTrue(ourList.contains(13));
        assertEquals(5, ourList.get(0));
        ArrayList<Integer> ourList2 = (ArrayList<Integer>) ourTree.getAllSorted("Hellgowrbv", Compare);
        assertTrue(ourList2.contains(13));
        assertEquals(11, ourList2.get(0));
    }
    @Test
    void test3Tree(){
        Trie<Integer> ourTree = new TrieImpl<>();
        ourTree.put("Hellaovervcvewc", 5);
        ourTree.put("Helleoexcex", 6);
        ourTree.put("Hellcoxwex", 7);
        ourTree.put("Helldorcccerc", 8);
        ourTree.put("Helleowxxw", 9);
        ourTree.put("Hellfoxwfx", 10);
        ourTree.put("Hellgowrbv", 11);
        ourTree.put("Hellgowrbv", 12);
        ourTree.put("Hellgowrbv", 13);
        Set<Integer> ourset = ourTree.deleteAllWithPrefix("Helle");
        assertTrue(ourset.contains(6));
        assertFalse(ourset.contains(10));
    }
    @Test
    void test4MoreTree(){
        Trie<Integer> ourTree = new TrieImpl<>();
        ourTree.put("Hellaovervcvewc", 5);
        ourTree.put("Helleoexcex", 6);
        ourTree.put("Hellcoxwex", 7);
        ourTree.put("Helldorcccerc", 8);
        ourTree.put("Helleowxxw", 9);
        ourTree.put("Hellfoxwfx", 10);
        ourTree.put("Hellgowrbv", 11);
        ourTree.put("Hellgowrbv", 12);
        ourTree.put("Hellgowrbv", 13);
        ourTree.put("Hellaovervcvewc", 14);
        ourTree.put("Helleoexcex", 15);
        ourTree.put("Hellcoxwex", 16);
        ourTree.put("Helldorcccerc", 17);
        ourTree.put("Helleowxxw", 18);
        ourTree.put("Hellfoxwfx", 19);
        ourTree.put("Hellgowrbv", 20);
        ourTree.put("Hellgowrbv", 21);
        ourTree.put("Hellgowrbv", 22);
        HashSet<Integer> returnSet = (HashSet<Integer>) ourTree.deleteAll("Hellgowrbv");
        assertTrue(returnSet.contains(11));
        assertTrue(returnSet.contains(12));
        assertTrue(returnSet.contains(13));
        assertTrue(returnSet.contains(20));
        assertTrue(returnSet.contains(21));
        assertTrue(returnSet.contains(22));
        Integer returnValue = ourTree.delete("Hellfoxwfx", 19);
        assertEquals(19, returnValue);
        class Compare implements Comparator<Integer>{
            @Override
            public int compare(Integer a, Integer b){
                return a.compareTo(b);
            }
        }
        Comparator<Integer> Compare = new Compare();
        ArrayList<Integer> returnList = (ArrayList<Integer>) ourTree.getAllSorted("Hellfoxwfx", Compare);
        assertTrue(returnList.contains(10));
    }
    @Test
    void test5(){
        URI ourURI = URI.create("https://www.Elmo.com");
        String textDoc = "We the People of the United States, in Order to form a more perfect Union," +
                " establish Justice, insure domestic Tranquility, provide for the common defense, promote" +
                " the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity," +
                " do ordain and establish this Constitution for the United States";
        DocumentImpl ourDoc = new DocumentImpl(ourURI, textDoc, null);
        assertEquals(3, ourDoc.wordCount("and"));
        HashSet<String> ourSet = (HashSet<String>) ourDoc.getWords();
        assertEquals(true, ourSet.contains("domestic"));
    }
    @Test
    void test6WordSearching() throws IOException {
        DocumentStoreImpl ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream("microsoft is is onis is the only company that on makes");
        InputStream input2 = new StringBufferInputStream("microsoft is the only company that is able to make pcos");
        InputStream input3 = new StringBufferInputStream("microsoft: is the onicnwincwi onfiwu only only only");
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.BINARY);
        List<Document> ourList = ourImpl.search("is");
        assertEquals("microsoft is is onis is the only company that on makes", ourList.get(0).getDocumentTxt());
        assertEquals(3, ourList.size());
        List<Document> lest2 = ourImpl.searchByPrefix("on");
        assertEquals(3, lest2.size());
        assertEquals("microsoft: is the onicnwincwi onfiwu only only only", lest2.get(0).getDocumentTxt());
        assertEquals("microsoft is is onis is the only company that on makes", lest2.get(1).getDocumentTxt());
    }
    @Test
    void test7SearchAndDelete() throws IOException {
        DocumentStoreImpl ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream("microsoft is is onis is the only company that on makes");
        InputStream input2 = new StringBufferInputStream("microsoft is the only company that is able to make pcos");
        InputStream input3 = new StringBufferInputStream("microsoft: is the onicnwincwi onfiwu only only only");
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.BINARY);
        ourImpl.deleteAll("pcos");
        List<Document> returnList = ourImpl.search("microsoft");
        assertEquals(2, returnList.size());
        ourImpl.undo();
        List<Document> returnList2 = ourImpl.search("is");
        assertEquals(3, returnList2.size());
        Document ourDoc = ourImpl.get(ourURI2);
        assertEquals(ourDoc.getDocumentTxt(), ("microsoft is the only company that is able to make pcos"));
        Set<URI> retrunUri = ourImpl.deleteAllWithPrefix("ab");
        assertTrue(retrunUri.contains(ourURI2));
        ourImpl.setMaxDocumentCount(2);
    }
    @Test
    void test77CommandSetUndoing() throws IOException {
        DocumentStoreImpl ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft is", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google is is", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego is the best";
        String string5 = "amazon is is amazing";
        String string6 = "apple was amazing";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        InputStream input1 = new StringBufferInputStream("microsoft is is onis is the only company that on makes");
        InputStream input2 = new StringBufferInputStream("microsoft is the only company that is able to make pcos");
        InputStream input3 = new StringBufferInputStream("microsoft: is the onicnwincwi onfiwu only only only");
        InputStream input4 = new ByteArrayInputStream(byte4);
        InputStream input5 = new ByteArrayInputStream(byte5);
        InputStream input6 = new ByteArrayInputStream(byte6);
        ourImpl.put(input1, ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input2, ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input3, ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(input4, ourURI4, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input5, ourURI5, DocumentStore.DocumentFormat.BINARY);
        ourImpl.put(input6, ourURI6, DocumentStore.DocumentFormat.BINARY);
        ourImpl.setMaxDocumentCount(3);
        ourImpl.deleteAll("is");
        boolean exist2 = new File(System.getProperty("user.dir") + File.separator + "www.google.com.json").exists();
        assertFalse(exist2);
        boolean exist3 = new File(System.getProperty("user.dir") + File.separator + "www.microsoft.com.json").exists();
        assertFalse(exist3);
        boolean exist4 = new File(System.getProperty("user.dir") + File.separator + "www.meta.com.json").exists();
        assertFalse(exist4);
        ourImpl.undo(ourURI1);
        ourImpl.undo(ourURI2);
        ourImpl.undo(ourURI3);
    }
    @Test
    void test8_Stack_test(){
        StackImpl<String> ourStack = new StackImpl<>();
        ourStack.push("first element");
        ourStack.push("second element");
        ourStack.push("third element");
        ourStack.push("fourth element");
        ourStack.push("fifth element");
        ourStack.push("sixth element");
        Assertions.assertEquals("sixth element", ourStack.pop());
        Assertions.assertEquals("fifth element", ourStack.peek());
        Assertions.assertEquals(5, ourStack.size());
        ourStack.push(null);
        Assertions.assertEquals(5, ourStack.size());
        Assertions.assertEquals("fifth element", ourStack.pop());
        Assertions.assertEquals("fourth element", ourStack.peek());
        Assertions.assertEquals(4, ourStack.size());
        Assertions.assertEquals("fourth element", ourStack.pop());
        Assertions.assertEquals("third element", ourStack.peek());
        Assertions.assertEquals(3, ourStack.size());
        Assertions.assertEquals("third element", ourStack.pop());
        Assertions.assertEquals("second element", ourStack.peek());
        Assertions.assertEquals(2, ourStack.size());
        Assertions.assertEquals("second element", ourStack.pop());
        Assertions.assertEquals("first element", ourStack.peek());
        Assertions.assertEquals(1, ourStack.size());
        Assertions.assertEquals("first element", ourStack.pop());
        Assertions.assertNull(null, ourStack.peek());
        Assertions.assertEquals(0, ourStack.size());
    }
    @Test
    void test55_Simple_DocumentStoreTest() throws IOException {
        DocumentStoreImpl ourStore = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        String ourDocText = "la la la la la la la Elmos world";
        URI ourURI = URI.create("https://www.Elmo.com");
        URI ur2 = URI.create("https:pizza.com");
        byte[] ourBytes = ourDocText.getBytes();
        DocumentImpl ourDoc = new DocumentImpl(ourURI, ourBytes);
        InputStream stream = new ByteArrayInputStream(ourBytes);
        int return1 = ourStore.put(stream, ourURI, DocumentStore.DocumentFormat.BINARY);
        Assertions.assertEquals(ourDoc.getDocumentTxt(), ourStore.get(ourURI).getDocumentTxt());
        Assertions.assertEquals(ourDoc.getKey(), ourStore.get(ourURI).getKey());
        Assertions.assertEquals(false, ourStore.delete(ur2));
        Assertions.assertEquals(true, ourStore.delete(ourURI));
    }

    @Test
    void test33() throws IllegalArgumentException{
        String ourDocText = "la la la la la la la Elmos world";
        URI ourURI = URI.create("https://www.Elmo.com");
        byte[] ourBytes = ourDocText.getBytes();
        boolean thrown = false;
        try{
            DocumentImpl ourDoc = new DocumentImpl(null, "hi", null);
        }catch(IllegalArgumentException e){
            thrown = true;
        }
        Assertions.assertEquals(true, thrown);
    }

    @Test
    void test333(){
        String ourDocText = "la la la la la la la Elmos world";
        URI ourURI = URI.create("https://www.Elmo.com");
        byte[] ourBytes = ourDocText.getBytes();
        DocumentImpl ourDoc = new DocumentImpl(ourURI, ourBytes);
        Assertions.assertEquals(ourURI, ourDoc.getKey());
        Assertions.assertEquals(null, ourDoc.getDocumentTxt());
        Assertions.assertEquals(ourBytes, ourDoc.getDocumentBinaryData());
    }


    @Test
    void test23() throws IOException{
        DocumentStoreImpl ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        DocumentImpl doc1 = new DocumentImpl(ourURI1, "microsoft", null);
        DocumentImpl doc2 = new DocumentImpl(ourURI2, "google", null);
        DocumentImpl doc3 = new DocumentImpl(ourURI3, "meta", null);
        String string4 = "lego";
        String string5 = "amazon";
        String string6 = "apple";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        DocumentImpl doc4 = new DocumentImpl(ourURI4, byte4);
        DocumentImpl doc5 = new DocumentImpl(ourURI5, byte5);
        DocumentImpl doc6 = new DocumentImpl(ourURI6, byte6);
        int return1 = ourImpl.put(new ByteArrayInputStream(byte4), ourURI4, DocumentStore.DocumentFormat.BINARY);
        Assertions.assertEquals(0, return1);
        Assertions.assertEquals(doc4.hashCode(), ourImpl.get(ourURI4).hashCode());
        Assertions.assertEquals(doc4.hashCode(), ourImpl.put(new ByteArrayInputStream(byte5), ourURI4, DocumentStore.DocumentFormat.BINARY));
    }

    @Test
    void test9() throws IOException, IllegalStateException{
        DocumentStoreImpl ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        URI ourURI3 = URI.create("https://www.meta.com");
        URI ourURI4 = URI.create("https://www.lego.com");
        URI ourURI5 = URI.create("https://www.amazon.com");
        URI ourURI6 = URI.create("https://www.apple.com");
        String string4 = "lego";
        String string5 = "amazon";
        String string6 = "apple";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        ourImpl.put(new StringBufferInputStream("microsoft"), ourURI1, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(new StringBufferInputStream("google"), ourURI2, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(new StringBufferInputStream("meta"), ourURI3, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(new ByteArrayInputStream(byte4), ourURI4, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(new ByteArrayInputStream(byte5), ourURI5, DocumentStore.DocumentFormat.TXT);
        ourImpl.put(new ByteArrayInputStream(byte6), ourURI6, DocumentStore.DocumentFormat.TXT);
        ourImpl.undo();
        Assertions.assertEquals(null, ourImpl.get(ourURI6));
        Assertions.assertEquals("google", ourImpl.get(ourURI2).getDocumentTxt());
        ourImpl.undo(ourURI2);
        Assertions.assertEquals(ourImpl.get(ourURI2), null);
        boolean thrown = false;
        try{
            ourImpl.undo(ourURI2);
        }catch(IllegalStateException e){
            thrown = true;
        }
        Assertions.assertEquals(true, thrown);
    }
    @Test
    void test10() throws IllegalStateException{
        DocumentStoreImpl ourImpl = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        boolean thrown = false;
        try{
            ourImpl.undo();
        }catch(IllegalStateException e){
            thrown = true;
        }
        Assertions.assertEquals(true, thrown);
    }
    @Test
    void test12() throws IllegalArgumentException, IOException{
        DocumentStoreImpl ourStore = new DocumentStoreImpl(new File(System.getProperty("user.dir")));
        URI ourURI1 = URI.create("https://www.microsoft.com");
        URI ourURI2 = URI.create("https://www.google.com");
        String string4 = "hohohoho";
        String string5 = "amazon";
        String string6 = "apple";
        byte[] byte4 = string4.getBytes();
        byte[] byte5 = string5.getBytes();
        byte[] byte6 = string6.getBytes();
        ourStore.put(new ByteArrayInputStream(byte4), ourURI1, DocumentStore.DocumentFormat.BINARY);
        ourStore.put(new ByteArrayInputStream(byte5), ourURI2, DocumentStore.DocumentFormat.BINARY);
        ourStore.put(new ByteArrayInputStream(byte6), ourURI1, DocumentStore.DocumentFormat.BINARY);
        ourStore.undo();
        Assertions.assertEquals("hohohoho", new String(ourStore.get(ourURI1).getDocumentBinaryData()));
    }

}
