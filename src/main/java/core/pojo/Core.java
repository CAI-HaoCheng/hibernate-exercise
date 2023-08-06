package core.pojo;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class Core implements Serializable {
	private static final long serialVersionUID = 1457755989409740329L;
	private boolean successful;
	private String message;
	
}
