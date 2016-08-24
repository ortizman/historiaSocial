package ar.com.historiasocial.dao;

import java.io.Serializable;

public interface Entity extends Serializable {
	public void setId(Long id);
	public Long getId();
}
