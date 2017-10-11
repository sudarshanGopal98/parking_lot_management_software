package structures;
import java.io.Serializable;


public class PaintField implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -8676471474818906489L;
	private String fieldData;

	public PaintField(String pf){
		if(pf == null || pf.equalsIgnoreCase("Data Not Filled"))
			fieldData = new String();
		else
			fieldData = pf;
	}


	public String getFieldData() {
		return fieldData;
	}
	public boolean setFieldData(String fieldData) {
		if(fieldData.length() <= 8){
			this.fieldData = fieldData;
			return true;
		}
		return false;
	}
	public boolean isAppropriate() {
		return true;
	}

	public boolean isValid() {
		if(isAppropriate()	&&	fieldData.length() == 8){
			return true;
		}
		return false;
	}
	@Override
	public String toString(){
		if(fieldData.equals(null) || fieldData.length() == 0 || fieldData.equals("null"))
			return "";
		return fieldData;
	}


	public int length() {
		return fieldData.length();
	}

	

}
