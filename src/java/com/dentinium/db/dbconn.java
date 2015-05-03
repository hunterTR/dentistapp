/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.db;

import java.io.IOException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

/**
 *
 * @author ahmetcem
 */
public class dbconn {

    protected DB db;
    protected DBCollection collection;
    protected Mongo mongo;

    public void openDB() throws MongoException, IOException {
        // connect to mongoDB, ip and port number
        mongo = new Mongo("localhost", 27017);

		// get database from MongoDB,
        // if database doesn't exists, mongoDB will create it automatically
        db = mongo.getDB("dedikodu");
    }

    public boolean connectDBCollection(String collectionName) {
        if (null == collectionName || collectionName.isEmpty()) {
            return false;
        }
        collection = db.getCollection(collectionName);

        return true;
    }

    public BasicDBObject getBasicDBObject() {
        return new BasicDBObject();
    }

    public WriteResult insertData(BasicDBObject document) {
        if (null == collection || null == document) {
            return null;
        }
        return collection.insert(document);
    }
    
    public WriteResult removeData(BasicDBObject document) {
        if (null == collection || null == document) {
            return null;
        }
        return collection.remove(document);
    }
    
    public WriteResult updateData(DBObject query,BasicDBObject document) {
        if (null == collection || null == document) {
            return null;
        }
        
        return collection.update(query, document);
    }

    public DBCursor searchData(BasicDBObject searchQuery) {
        if (null == searchQuery) {
            return null;
        }
        return collection.find(searchQuery);
    }

    public void closeDB() {
        mongo.close();
    }

}
