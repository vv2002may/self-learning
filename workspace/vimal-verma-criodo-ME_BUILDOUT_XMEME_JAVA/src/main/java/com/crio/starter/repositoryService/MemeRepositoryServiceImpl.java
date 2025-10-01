package com.crio.starter.repositoryService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeRepositoryServiceImpl implements MemeRepositoryService {

    @Autowired
    private MemeRepository memeRepository;

    @Override
    public List<MemeEntity> getMemesList() {

        return memeRepository.findTop100ByOrderByIdDesc();

        // List<MemeEntity> memes =
        //         memeRepository.findAll()
        //         .stream()
        //         .sorted(Comparator
        //         .comparing(MemeEntity::getId)
        //         .reversed())
        //         .limit(100)
        //         .collect(Collectors.toList());

        // return memes;

        

        // List<MemeDto> memesList = new ArrayList<>();

        // for (MemeEntity meme : memes) {
        //     memesList.add(new MemeDto(meme.getId(), meme.getName(), meme.getUrl(),
        //             meme.getCaption()));
        // }
        // return memesList;
    }

    @Override
    public MemeDto getMemeWithId(String id) {
        MemeEntity meme = memeRepository.findById(id).orElse(null);
        return new MemeDto(meme.getId(), meme.getName(), meme.getUrl(), meme.getCaption());
    }

    @Override
    public MemeDto createMeme(MemeEntity meme) {
        // LocalTime now = LocalTime.now();
        // meme.setCreatedAt(now);
        MemeEntity savedMeme = memeRepository.save(meme);
        MemeDto memeDto = new MemeDto(savedMeme.getId(), savedMeme.getName(), savedMeme.getUrl(),
                savedMeme.getCaption());
        return memeDto;
    }

    public boolean isMemeAvailable(MemeEntity meme) {
        if (!memeRepository
                .findByNameAndUrlAndCaption(meme.getName(), meme.getUrl(), meme.getCaption())
                .isEmpty()) {
            return true;
        }
        return false;
    }


}
