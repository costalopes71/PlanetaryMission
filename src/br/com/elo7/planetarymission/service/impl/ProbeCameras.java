package br.com.elo7.planetarymission.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.service.EquipmentCameras;
import br.com.elo7.planetarymission.service.response.ProbePhotos;

public class ProbeCameras extends EquipmentCameras {

	private static final String TOKEN = "DEMO_KEY";
	private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/";
	//"img_src":"http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FRB_486265257EDR_F0481570FHAZ00323M_.JPG"
	// padrao: "img_src":" + qqr coisa em qqr qtd + "
	
	@Override
	public List<ProbePhotos> getPhotosQueryByMartianRotation(int martianRotation) throws SerializePhotosException {

		resource = client.resource(BASE_URL + "photos?sol=" + martianRotation + "&camera=fhaz&api_key=" + TOKEN);

		response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			throw new SerializePhotosException();
		}
		
		List<ProbePhotos> photos = parseUrls(response.getEntityInputStream());

		return photos;
	}
	
	private List<ProbePhotos> parseUrls(InputStream is) throws SerializePhotosException {

		List<ProbePhotos> photos = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
			
			String responseString = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				responseString += line;
			}
			
			Pattern p = Pattern.compile("\"img_src\":\".*?\"");
			Matcher m = p.matcher(responseString);
			
			while (m.find()) {
				String url = m.group();
				url = url.replaceAll("\"", "").replace("img_src:", "");
				photos.add(new ProbePhotos(url));
			}
			
		} catch (Exception e) {
			throw new SerializePhotosException(e);
		}
		
		return photos;
	}

}
