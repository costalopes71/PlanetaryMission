package br.com.elo7.planetarymission.service.impl;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.service.CamerasService;
import br.com.elo7.planetarymission.service.response.Photos;

public class ProbeCamerasService extends CamerasService {

	private static final String TOKEN = "ZqtDrNYm38GIUcsuRByAMp3lUQ0rZw5HILtysboI";
	private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/";
	private static final Pattern URL_PATTERN = Pattern.compile("\"img_src\":\".*?\"");

	@Override
	public Queue<Photos> getPhotosQueryByMartianRotation(int martianRotation) throws SerializePhotosException {

		webTarget = client.target(BASE_URL + "photos?sol=" + martianRotation + "&api_key=" + TOKEN);

		response = webTarget.request().accept(MediaType.TEXT_PLAIN).get();

		if (response.getStatus() != 200) {
			throw new SerializePhotosException();
		}

		String responseString = response.readEntity(String.class);

		Queue<Photos> photos = parseUrls(responseString);
		return photos;
	}

	private Queue<Photos> parseUrls(String plainResponse) throws SerializePhotosException {

		Queue<Photos> photos = new LinkedBlockingQueue<>();

		Matcher m = URL_PATTERN.matcher(plainResponse);

		while (m.find()) {
			String url = m.group();
			url = url.replaceAll("\"", "").replace("img_src:", "");

			Photos photo = new Photos(url);

			if (!photos.contains(photo)) {
				photos.add(photo);
			}

			if (photos.size() == 100)
				break;

		}

		return photos;
	}

}
