package com.crio.starter.controller;

import lombok.AllArgsConstructor;
import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.exchange.MemeReqDto;
import com.crio.starter.repositoryService.MemeRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MemeController {
    
    @Autowired
    private MemeRepositoryServiceImpl memeService;

    @GetMapping("/memes")
    public ResponseEntity<List<MemeEntity>> getMemes(){
        List<MemeEntity> memeDto=memeService.getMemesList();
        return ResponseEntity.ok().body(memeDto);
    }

    @PostMapping("/memes")
    public ResponseEntity<MemeReqDto> createNewMeme(@RequestBody MemeEntity meme){
        if(meme == null || meme.getName() == null || meme.getUrl() == null || meme.getCaption() == null){
            return ResponseEntity.status(400).build();
        } else if(memeService.isMemeAvailable(meme)){
            return ResponseEntity.status(409).build();
        } else {
            MemeDto memeDto= memeService.createMeme(meme);

            return ResponseEntity.ok().body(new MemeReqDto(memeDto.getId()));
        }
    }

    @GetMapping("/memes/{id}")
    public ResponseEntity<MemeEntity> getMemeById(@PathVariable String id){
        List<MemeEntity> memesDto=memeService.getMemesList();
        for(MemeEntity memeDto:memesDto){
            if(memeDto.getId().equals(id)){
                return ResponseEntity.ok().body(memeDto);
            }
        }
        return ResponseEntity.status(404).build();
    }
}

// 
// changes
