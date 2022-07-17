package image.api.upload;

public class FileUploadResponse {

	
	private String fileName;
	private String donwloadUri;
	private long size;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDonwloadUri() {
		return donwloadUri;
	}
	public void setDonwloadUri(String donwloadUri) {
		this.donwloadUri = donwloadUri;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
}
