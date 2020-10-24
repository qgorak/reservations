package s4.spring.reservations.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.models.User;
import s4.spring.reservations.repositories.LodgementRepository;
import s4.spring.reservations.repositories.UserRepository;
import s4.spring.reservations.services.MyUserDetails;
import s4.spring.reservations.utilities.FileUploadUtil;

@RestController
@RequestMapping("/rest/image")
public class RestImageController {
		
		@Autowired
		private UserRepository repoUs;
		
		@Autowired
		private LodgementRepository repoL;

		
	    @PostMapping("/saveAvatar")
	    public String saveAvatar(
	            @RequestParam("file") MultipartFile multipartFile,@AuthenticationPrincipal MyUserDetails user) throws IOException {
	    	
	    	String fileName = multipartFile.getOriginalFilename();
	        String uploadDir = "user-photos/"+user.getId()+"/avatar/" ;
	        File index = new File("user-photos/"+user.getId()+"/avatar/");
	        Path uploadPath = Paths.get(uploadDir);  
	        String[]entries = index.list();
	        if(entries!=null) {
	        for(String s: entries){
	        	File currentFile = new File(index.getPath(),s);
	        	currentFile.delete();
	        }
	        }
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        return user.getUsername();
	    }
	    
	    @PostMapping("/saveLodgementPhoto/{id}")
	    public String saveLodgementPhoto(
	            @RequestParam("file") MultipartFile multipartFile,@PathVariable int id,@AuthenticationPrincipal MyUserDetails user) throws IOException {
			User creator = new User();
			Lodgement l = new Lodgement();
			creator = repoUs.findById(user.getId());
			l = repoL.findById(id);
			if(l.getRent().getId() == creator.getId()) {
		        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		        String uploadDir = "user-photos/"+user.getId()+"/lodgement/"+id+"/" ;
		        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		        return fileName;
			}
			return null;
	    }
	    
		@DeleteMapping("/deleteLodgementPhoto/{id}/{name}/")
		public String deleteLodgementPhoto(@PathVariable int id,@PathVariable String name,@AuthenticationPrincipal MyUserDetails user) {
			
			User creator = new User();
			Lodgement l = new Lodgement();
			creator = repoUs.findById(user.getId());
			l = repoL.findById(id);
			if(l.getRent().getId() == creator.getId()) {
		        File index = new File("user-photos/"+user.getId()+"/lodgement/"+id+"/"); 
		        String[]entries = index.list();
		        for(String s: entries){
		        	File currentFile = new File(index.getPath(),s);
		        	if(currentFile.getName().equals(name)) {
			        	currentFile.delete();
			        	return name;
		        	}
		        }
			}
			return null;
		}
	    
	    @GetMapping("/lodgement/{id}")
	    public List<String> getLodgementPhotos(@PathVariable int id) {
			Lodgement l = new Lodgement();
			l = repoL.findById(id);
			User creator = l.getRent();
	    	List<String> results = new ArrayList<String>();
	    	File[] files = new File(System.getProperty("user.dir")+"/user-photos/"+creator.getId()+"/lodgement/"+id).listFiles();
	    	if (files != null) {
	    	for (File file : files) {
	    	    if (file.isFile()) {
	    	        results.add(file.getName());
	    	    }
	    	}
	    	}
	    	results.add(Integer.toString(id));
	    	return results;
	    }
	    
	    @GetMapping("/user/{id}")
	    public List<String> getUserAvatar(@PathVariable int id) {
			User u = repoUs.findById(id);
	    	List<String> results = new ArrayList<String>();
	    	File[] files = new File(System.getProperty("user.dir")+"/user-photos/"+u.getId()+"/avatar/").listFiles();
	    	if (files != null) {
	    	for (File file : files) {
	    	    if (file.isFile()) {
	    	        results.add(file.getName());
	    	    }
	    	}
	    	}
	    	results.add(Integer.toString(id));
	    	return results;
	    }
	    
	    


}
