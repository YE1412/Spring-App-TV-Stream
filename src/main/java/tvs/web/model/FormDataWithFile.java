package tvs.web.model;

import java.io.Serializable;

import javax.persistence.Basic;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
@ToString(of= {"dest_name"})
public class FormDataWithFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@NonNull
	@Getter @Setter
	private String dest_name;
	@Getter @Setter
	private MultipartFile file;
//	@Basic(optional = false)
//	@NonNull
//	@Getter @Setter
//	private String filePath;
}
