package tvs.interf;

import org.springframework.stereotype.Service;

@Service
public interface ILogger {
	public void log(String msg);
	public void start();
	public void stop();
}
