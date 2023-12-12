package com.example.demo;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;

@DgsComponent
@RequiredArgsConstructor
public class DsgComponent {

    private final ShowRepository showRepository;

    @DgsQuery
    public Flux<Show> findAll(@InputArgument String titleFilter){
        if(ObjectUtils.isEmpty(titleFilter)) {
            return showRepository.findAll();
        }

        return showRepository.findAll().filter(s -> s.getTitle().contains(titleFilter));
    }

    @DgsQuery
    public MutationResponse addShow(String title, Integer releaseYear) {
        showRepository.addShow(new Show(title, releaseYear));
        return new MutationResponse(true, "Added show " + title + " with success");
    }

}
