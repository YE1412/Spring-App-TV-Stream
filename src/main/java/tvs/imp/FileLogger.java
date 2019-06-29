package tvs.imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tvs.interf.ILogger;
@Service("fileLogger")
@Qualifier("integ")
public class FileLogger implements ILogger {
	private PrintWriter writer;
	private File file;
	private String fileName;
	
	public FileLogger() {
		super();
		this.fileName = "tvs.log";
	}
	public FileLogger(String fileName) {
		super();
		this.fileName = fileName;
    }
	// start service
    @PostConstruct
	public void start() {
        System.err.println("Start " + this);
        try {
        	file = new File(fileName);
        	// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
            this.writer = new PrintWriter(new FileOutputStream(fileName, true));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Nom de fichier incorrect !");
        }catch (IOException e) {
            throw new IllegalArgumentException("Création de fichier impossible !");
        }
    }
    // stop service
    @PreDestroy
    public void stop() {
        writer.close();
        System.err.println("Stop " + this);
    }
	public void log(String msg) {
		// TODO Auto-generated method stub
		writer.printf("%tF %1$tR | %s\n", new Date(), msg);
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
