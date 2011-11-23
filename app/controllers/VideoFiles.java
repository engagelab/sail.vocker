package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import models.Video;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.Controller;

public class VideoFiles extends Controller {

	public static void uploadForm() {
		render();
	}

	public static void doUpload(File file, String comment)
			throws FileNotFoundException {
		final Video doc = new Video();
		doc.fileName = file.getName();
		doc.comment = comment;
		doc.file = new Blob();
		doc.file.set(new FileInputStream(file),
				MimeTypes.getContentType(file.getName()));

		doc.save();
		listUploads();
	}

	public static void listUploads() {
		List<Video> docs = Video.findAll();
		render(docs);
	}

	public static void downloadFile(long id) {
		final Video doc = Video.findById(id);
		notFoundIfNull(doc);
		response.setContentTypeIfNotSet(doc.file.type());
		renderBinary(doc.file.get(), doc.fileName);
	}

}
