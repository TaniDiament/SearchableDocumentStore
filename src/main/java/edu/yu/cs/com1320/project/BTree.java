<<<<<<< HEAD
package edu.yu.cs.com1320.project;

import edu.yu.cs.com1320.project.stage5.PersistenceManager;

public interface BTree<Key extends Comparable<Key>, Value> {
    Value get(Key k);
    Value put(Key k, Value v);
    void moveToDisk(Key k) throws Exception;
    void setPersistenceManager(PersistenceManager<Key,Value> pm);
=======
package edu.yu.cs.com1320.project;

import edu.yu.cs.com1320.project.stage5.PersistenceManager;

public interface BTree<Key extends Comparable<Key>, Value> {
    Value get(Key k);
    Value put(Key k, Value v);
    void moveToDisk(Key k) throws Exception;
    void setPersistenceManager(PersistenceManager<Key,Value> pm);
>>>>>>> fd77063a3d4afb76b6777a38f3f83134aeed8ddf
}