package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @author Jan-Sebastian Fabik
 */
public class AlbumCreateFormData {

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

	public AlbumCreateDTO toAlbumCreateDTO() {
		AlbumCreateDTO albumCreateDTO = new AlbumCreateDTO();
		albumCreateDTO.setReleaseDate(this.releaseDate);
		albumCreateDTO.setTitle(this.title);
		albumCreateDTO.setCommentary(this.commentary);
		albumCreateDTO.setAlbumArt(this.albumArt);
		return albumCreateDTO;
	}
}
