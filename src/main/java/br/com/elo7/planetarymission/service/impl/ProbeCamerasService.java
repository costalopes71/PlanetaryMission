package br.com.elo7.planetarymission.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.service.CamerasService;
import br.com.elo7.planetarymission.service.response.Photos;

public class ProbeCamerasService extends CamerasService {

	private static final String TOKEN = "DEMO_KEY";
	private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/";
	private static final Pattern URL_PATTERN = Pattern.compile("\"img_src\":\".*?\"");
	
	@Override
	public Queue<Photos> getPhotosQueryByMartianRotation(int martianRotation) throws SerializePhotosException {

		resource = client.resource(BASE_URL + "photos?sol=" + martianRotation + "&api_key=" + TOKEN);

		response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			throw new SerializePhotosException();
		}
		
		Queue<Photos> photos = parseUrls(response.getEntityInputStream());

		return photos;
	}
	
	private Queue<Photos> parseUrls(InputStream is) throws SerializePhotosException {

		Queue<Photos> photos = new LinkedBlockingQueue<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
			
			String responseString = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				responseString += line;
			}
			
			Matcher m = URL_PATTERN.matcher(responseString);
			
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
			
		} catch (Exception e) {
			throw new SerializePhotosException(e);
		}
		
		return photos;
	}

}
