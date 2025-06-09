//package in.rahul.bgremove.service.impl;
//
//import in.rahul.bgremove.client.ClipdropClient;
//import in.rahul.bgremove.service.RemoveBackgroundService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class RemoveBackgroundServiceImpl implements RemoveBackgroundService {
//
//    @Value("${clipdrop.apikey}")
//    private String apiKey;
//
//    private final ClipdropClient clipdropClient;
//
//    @Override
//    public byte[] removeBackground(MultipartFile file) {
//        log.info("Removing background from file: {}", file.getOriginalFilename());
//        return clipdropClient.removeBackGround(file, apiKey);
//    }
//}
package in.rahul.bgremove.service.impl;

import in.rahul.bgremove.client.ClipdropClient;
import in.rahul.bgremove.service.RemoveBackgroundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public  class RemoveBackgroundServiceImpl implements RemoveBackgroundService {

    @Value("${clipdrop.apikey}")
    private String apiKey;

    private final ClipdropClient clipdropClient;

    @Override
    public byte[] removeBackground(MultipartFile file) {
        log.info("Removing background from file: {}", file.getOriginalFilename());
        return clipdropClient.removeBackGround(file, apiKey);
    }
}
