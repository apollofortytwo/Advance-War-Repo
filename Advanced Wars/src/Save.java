import java.io.Serializable;
import java.util.List;

import layout.SaveObject;

public class Save implements Serializable {
	List<SaveObject> list;
	String map;
	public double[][] size;

	public void setList(List<SaveObject> list) {
		this.list = list;
	}

	public List<SaveObject> getList() {
		return list;
	}
}
