package telran.imagga.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.imagga.dto.ResponseDto;

public class ImaggaTagAppl {
	final static String TOKEN = "Basic <token>";
	static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		String httpUrl = "https://api.imagga.com/v2/tags";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
								.queryParam("image_url", "https://24smi.org/public/media/235x307/person/2017/12/22/4sqqykgn04bo-cheburashka.jpg")
								.queryParam("language", "ru")
								.queryParam("limit", 3);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", TOKEN);
		RequestEntity<String> requestEntity = 
				new RequestEntity<>(headers , HttpMethod.GET, builder.build().toUri());
		ResponseEntity<ResponseDto> responseEntity = restTemplate.exchange(requestEntity, ResponseDto.class);
		responseEntity.getBody().getResult().getTags().forEach(System.out::println);
	}

}
