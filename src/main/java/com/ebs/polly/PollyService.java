package com.ebs.polly;

import java.io.InputStream;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.LanguageCode;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import com.amazonaws.services.polly.presign.SynthesizeSpeechPresignRequest;

@Service
public class PollyService {

	public InputStream requestStream(PollyText pollyText) {

		AmazonPolly polly = AmazonPollyClientBuilder.standard().withClientConfiguration(new ClientConfiguration()).withRegion(Regions.US_EAST_2).build();

		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

		Voice voice = findVoice(pollyText, polly, describeVoicesRequest);

		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(pollyText.getText()).withVoiceId(voice.getId())
				.withOutputFormat(OutputFormat.Mp3);
		SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

		return synthRes.getAudioStream();
	}
	
	public URL requestURL(PollyText pollyText) {

		AmazonPolly polly = AmazonPollyClientBuilder.standard().withClientConfiguration(new ClientConfiguration()).withRegion(Regions.US_EAST_2).build();

		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

		Voice voice = findVoice(pollyText, polly, describeVoicesRequest);

		// Create speech synthesis request.
		SynthesizeSpeechPresignRequest synthesizeSpeechPresignRequest =
		new SynthesizeSpeechPresignRequest()
		// Set text to synthesize.
		.withText(pollyText.getText())
		// Set voice selected by the user.
		.withVoiceId(voice.getId())
		// Set format to MP3.
		.withOutputFormat(OutputFormat.Mp3);

		return polly.presigners().getPresignedSynthesizeSpeechUrl(synthesizeSpeechPresignRequest);

	}

	private Voice findVoice(PollyText pollyText, AmazonPolly polly, DescribeVoicesRequest describeVoicesRequest) {
		String laguageCode = pollyText.getLanguageCode();
		try{
			describeVoicesRequest.setLanguageCode( LanguageCode.fromValue(laguageCode) );
		}catch(IllegalArgumentException ex){
			describeVoicesRequest.setLanguageCode(LanguageCode.EnUS);
		}

		DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
		Voice voice = describeVoicesResult.getVoices().get(0);
		return voice;
	}


}