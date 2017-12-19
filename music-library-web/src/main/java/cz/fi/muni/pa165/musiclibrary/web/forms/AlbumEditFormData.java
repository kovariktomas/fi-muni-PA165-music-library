package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @author Jan-Sebastian Fabik
 */
public class AlbumEditFormData {

	private Date releaseDate;

	private String title;

	private String commentary;

	private byte[] albumArt;

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public byte[] getAlbumArt() {
		return this.albumArt;
	}

	public void setAlbumArt(MultipartFile albumArt) throws IOException {
		this.albumArt = albumArt.isEmpty() ? null : albumArt.getBytes();
	}

	public void updateAlbumDTO(AlbumDTO albumDTO) {
		albumDTO.setReleaseDate(this.releaseDate);
		albumDTO.setTitle(this.title);
		albumDTO.setCommentary(this.commentary);

		if (this.albumArt != null && this.albumArt.length != 0) {
			albumDTO.setAlbumArt(this.albumArt);
		}
	}
}
