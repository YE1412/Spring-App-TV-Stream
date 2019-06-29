package tvs.imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tvs.interf.ILogger;
@Service("beanfileLogger")
@Qualifier("dev")
public class BeanFileLogger implements ILogger {
	
	// parameter: writer name
    private String fileName;
    private File file;
    
    // property: writer
    private PrintWriter writer;

    // start service
    @PostConstruct
    public void start() {
        if (fileName == null) {
        	fileName = "tvsbean.log";
//            throw new IllegalStateException("Aucun fichier de ce nom trouvé !");
        }
        try {
        	file = new File(fileName);
        	// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
            OutputStream os = new FileOutputStream(fileName, true);
            this.writer = new PrintWriter(os);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Mauvais fichier !");
        }catch (IOException e) {
            //throw new IllegalStateException("Ecriture impossible dans le fichier !");
        }
        System.err.println("Start " + this);
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
