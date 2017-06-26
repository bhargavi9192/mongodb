package com.journaldev.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.journaldev.mongodb.converter.PersonConverter;
import com.journaldev.mongodb.model.Person;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBPersonDAO {

	private DBCollection col;

	public MongoDBPersonDAO(MongoClient mongo) {
		this.col = mongo.getDB("journaldev").getCollection("Persons");
	}

	public Person createPerson(Person p) {
		try {
			DBObject doc = PersonConverter.toDBObject(p);
			this.col.insert(doc);
			ObjectId id = (ObjectId) doc.get("_id");
			p.setId(id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public void updatePerson(Person p) {
		try {
			DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(p.getId())).get();
			this.col.update(query, PersonConverter.toDBObject(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Person> readAllPerson() {
		List<Person> data = new ArrayList<Person>();
		DBCursor cursor = col.find();
		try {
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				Person p = PersonConverter.toPerson(doc);
				data.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void deletePerson(Person p) {
		try {
			DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(p.getId())).get();
			this.col.remove(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Person readPerson(Person p) {
		DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(p.getId())).get();
		DBObject data = this.col.findOne(query);
		return PersonConverter.toPerson(data);
	}

}