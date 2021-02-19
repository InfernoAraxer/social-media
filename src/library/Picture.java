package library;

public class Picture {
	private int id;
	private String base64Image;


	public Picture (int id, String base64Image) {
		this.id = id;
		this.base64Image = base64Image;
	}
	
	public int getId() {
		return id;
	}
	
	public String getBase64Image() {
        return base64Image;
    }
 
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
