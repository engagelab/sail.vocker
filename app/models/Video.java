package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Video extends Model {
	public String fileName;
	public Blob file;
	public String comment;
}
