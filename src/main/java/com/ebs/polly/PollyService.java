package com.ebs.polly;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Region;
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

@Service
public class PollyService {
	
    public InputStream request(PollyText pollyText) {
         
            AmazonPolly polly = AmazonPollyClientBuilder.standard().withClientConfiguration(new ClientConfiguration()).withRegion(Regions.US_EAST_2).build();
			
    		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
    		
    		String laguageCode = pollyText.getLanguageCode();
    		try{
    			describeVoicesRequest.setLanguageCode( LanguageCode.fromValue(laguageCode) );
    		}catch(IllegalArgumentException ex){
    			describeVoicesRequest.setLanguageCode(LanguageCode.EnUS);
    		}

    		DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
    		Voice voice = describeVoicesResult.getVoices().get(0);
    		
    		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(pollyText.getText()).withVoiceId(voice.getId())
    						.withOutputFormat(OutputFormat.Mp3);
    		SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);
    		
           return synthRes.getAudioStream();
    }


}