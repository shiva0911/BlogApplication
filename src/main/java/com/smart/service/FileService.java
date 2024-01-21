package com.smart.service;
import java.io.*;

import org.springframework.web.multipart.MultipartFile;

public interface FileService 
{
	String uplodeImage(String path,MultipartFile file)throws IOException;
	InputStream getResource(String path,String fileName)throws FileNotFoundException;

}
