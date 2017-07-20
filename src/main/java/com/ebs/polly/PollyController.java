package com.ebs.polly;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/polly")
public class PollyController {
	
	@Autowired
	private PollyService pollyService;
	
	
	@PostMapping("/speech")
    public @ResponseBody String pollyURL(@RequestBody PollyText pollyText, HttpServletResponse response) throws IOException {
        
		return pollyService.requestURL(pollyText).toString();
		
    }
	
	@PostMapping("/speech/stream")
    public void pollyStream(@RequestBody PollyText pollyText, HttpServletResponse response) throws IOException {
        
		InputStream mp3Audio = pollyService.requestStream(pollyText);
		
		IOUtils.copy(mp3Audio, response.getOutputStream());
		response.getOutputStream().flush();
        		
    }

}