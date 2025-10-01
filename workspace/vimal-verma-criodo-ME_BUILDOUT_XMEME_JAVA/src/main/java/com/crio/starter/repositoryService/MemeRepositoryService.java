package com.crio.starter.repositoryService;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeDto;

public interface MemeRepositoryService {
    
    List<MemeEntity> getMemesList();
    MemeDto getMemeWithId(String MemeId);
    MemeDto createMeme(MemeEntity meme);
}
